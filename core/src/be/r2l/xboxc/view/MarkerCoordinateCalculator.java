package be.r2l.xboxc.view;

import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;

import be.r2l.xboxc.XboxControllerObservable;
import be.r2l.xboxc.hardwareAbstraction.ControllerItem;

import static java.lang.StrictMath.*;

public class MarkerCoordinateCalculator {

    private final static int WIGGLE_AMOUNT = 6;
    private final static int MAX_REACH_JOYSTICK = 50;
    private final static int MAX_REACH_TRIGGER = 50;

    private final XboxControllerObservable observable;
    private final Map<String, java.util.List<ControllerItem>> ALL_AXIS;

    public MarkerCoordinateCalculator(XboxControllerObservable observable) {
        this.observable = observable;
        ALL_AXIS = ControllerItem.getAxes().stream().collect(Collectors.groupingBy(controllerItem -> controllerItem.name().split("_")[2]));
    }

    public Point getCalculatedCoordinates(ControllerItem item, int controllerIndex) {

        Point baseCoordinates = HardwareCoordinates.getCoordinates(item);
        float value = observable.getControllerItemValue(controllerIndex, item);
        CoordinateCalculation coordinateCalculation = new ButtonCoordinateCalculation(); //default to Button as it is used for buttons and dpad directions

        if (ALL_AXIS.get("TRIGGER").contains(item)) {
            coordinateCalculation = new TriggerCoordinateCalculation();
        }

        if (ALL_AXIS.get("STICK").contains(item)) {
            coordinateCalculation = new JoyStickCoordinateCalculation(item, controllerIndex);
        }

        return coordinateCalculation.apply(baseCoordinates, value);
    }

    private interface CoordinateCalculation {
        Point apply(Point baseCoordinates, float value);
    }

    private static class ButtonCoordinateCalculation implements CoordinateCalculation {

        private static Point makeItWiggle(Point input) {
            int newX = input.x + (int) Math.round(Math.random() * WIGGLE_AMOUNT) - WIGGLE_AMOUNT / 2;
            int newY = input.y + (int) Math.round(Math.random() * WIGGLE_AMOUNT) - WIGGLE_AMOUNT / 2;
            return new Point(newX, newY);
        }

        @Override
        public Point apply(Point baseCoordinates, float value) {
            return makeItWiggle(baseCoordinates);
        }
    }

    private static class TriggerCoordinateCalculation implements CoordinateCalculation {
        @Override
        public Point apply(Point baseCoordinates, float value) {
            int newX = baseCoordinates.x + calculateDeviation(value, MAX_REACH_TRIGGER);
            return new Point(newX, baseCoordinates.y);
        }
    }

    private class JoyStickCoordinateCalculation implements CoordinateCalculation {

        private final float horizontalValue;
        private final float verticalValue;

        public JoyStickCoordinateCalculation(ControllerItem item, int controllerIndex) {
            String horizontalAxisName = item.name().replace("VERTICAL", "HORIZONTAL");
            String verticalAxisName = item.name().replace("HORIZONTAL", "VERTICAL");
            ControllerItem horizontalAxis = ControllerItem.valueOfLabel(horizontalAxisName);
            ControllerItem verticalAxis = ControllerItem.valueOfLabel(verticalAxisName);
            horizontalValue = observable.getControllerItemValue(controllerIndex, horizontalAxis);
            verticalValue = observable.getControllerItemValue(controllerIndex, verticalAxis);
        }

        @Override
        public Point apply(Point baseCoordinates, float value) {
            double joyStickAngle = atan(verticalValue / horizontalValue);
            double maxXForAlpha = abs(cos(joyStickAngle));
            double maxYForAlpha = abs(sin(joyStickAngle));
            int newX = baseCoordinates.x + calculateDeviation(horizontalValue, (int) (MAX_REACH_JOYSTICK * maxXForAlpha));
            int newY = baseCoordinates.y - calculateDeviation(verticalValue, (int) (MAX_REACH_JOYSTICK * maxYForAlpha));
            return new Point(newX, newY);
        }
    }

    private static int calculateDeviation(float value, int maxDeviation) {
        return (int) (maxDeviation * value);
    }
}
