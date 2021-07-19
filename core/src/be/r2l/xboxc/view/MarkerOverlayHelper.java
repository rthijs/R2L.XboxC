package be.r2l.xboxc.view;


import java.awt.Point;

import be.r2l.xboxc.XboxControllerObservable;
import be.r2l.xboxc.hardwareAbstraction.ControllerItem;

public class MarkerOverlayHelper {

    private static final float THRESHOLD_NO_VALUE = 0.005f; //for decent xbox 360 controllers set this to .00005, for cheap chinese SNES controllers use .005
    private static final float TRIGGER_UNPRESSED_VALUE = 0f;

    private final XboxControllerObservable xboxControllerObservable;
    private final MarkerCoordinateCalculator calculator;

    public MarkerOverlayHelper(XboxControllerObservable observable) {
        xboxControllerObservable = observable;
        calculator = getCalculator();
    }

    protected MarkerCoordinateCalculator getCalculator() {
        return new MarkerCoordinateCalculator(xboxControllerObservable);
    }

    public boolean markerShouldBeDrawn(int controllerIndex, ControllerItem item) {
        return itemIsKnown(item)
                && conditionsAreMetIfButton(item, controllerIndex)
                && conditionsAreMetIfDPad(item, controllerIndex)
                && conditionsAreMetIfJoystick(item, controllerIndex)
                && conditionsAreMetIfTrigger(item, controllerIndex);
    }

    private boolean conditionsAreMetIfTrigger(ControllerItem item, int controllerIndex) {
        return !isTrigger(item) || isPressed(item, controllerIndex);
    }

    private boolean conditionsAreMetIfJoystick(ControllerItem item, int controllerIndex) {
        return !isJoyStickAxis(item) || axisMarkerMustBeDrawn(item, controllerIndex);
    }

    private boolean conditionsAreMetIfDPad(ControllerItem item, int controllerIndex) {
        return !isDPad(item) || (!isDPadCenter(item) && isPressed(item, controllerIndex));
    }

    private boolean conditionsAreMetIfButton(ControllerItem item, int controllerIndex) {
        return !isButton(item) || isPressed(item, controllerIndex);
    }

    private static boolean isJoyStickAxis(ControllerItem item) {
        return item.name().contains("STICK");
    }

    private boolean axisMarkerMustBeDrawn(ControllerItem controllerItem, int controllerIndex) {
        if (isHorizontalAxis(controllerItem)) {
            return hasAValue(controllerItem, controllerIndex) && !hasAValue(getVerticalAxisForHorizontal(controllerItem), controllerIndex);
        } else {
            return hasAValue(controllerItem, controllerIndex);
        }
    }

    private static ControllerItem getVerticalAxisForHorizontal(ControllerItem item) {
        return ControllerItem.valueOfLabel(getVerticalAxisName(item));
    }

    private static String getVerticalAxisName(ControllerItem axis) {
        return axis.name().replace("HORIZONTAL", "VERTICAL");
    }

    private static boolean isHorizontalAxis(ControllerItem controllerItem) {
        return controllerItem.name().contains("HORIZONTAL");
    }

    private static boolean itemIsKnown(ControllerItem item) {
        return !item.equals(ControllerItem.UNKNOWN);
    }

    private static boolean isButton(ControllerItem item) {
        return ControllerItem.getButtons().stream().anyMatch(i -> i.equals(item));
    }

    private static boolean isDPad(ControllerItem item) {
        return ControllerItem.getDPadControllerItems().stream().anyMatch(i -> i.equals(item));
    }

    private static boolean isTrigger(ControllerItem item) {
        return item.name().contains("TRIGGER");
    }

    private boolean isPressed(ControllerItem item, int controllerIndex) {
        if (isTrigger(item)) {
        	System.out.println("is trigger: " + xboxControllerObservable.getControllerItemValue(controllerIndex, item));
            return xboxControllerObservable.getControllerItemValue(controllerIndex, item) > TRIGGER_UNPRESSED_VALUE;
        }
        return isControllerItemValueGreaterThanThreshold(item, controllerIndex);
    }

    private static boolean isDPadCenter(ControllerItem item) {
    	//TODO: fix
    	return true;
        //return item.equals(ControllerItem.DPAD_CENTER);
    }

    private boolean hasAValue(ControllerItem item, int controllerIndex) {
        return isControllerItemValueGreaterThanThreshold(item, controllerIndex);
    }

    private boolean isControllerItemValueGreaterThanThreshold(ControllerItem item, int controllerIndex) {
        return Math.abs(xboxControllerObservable.getControllerItemValue(controllerIndex, item)) > THRESHOLD_NO_VALUE;
    }

    public Point getCalculatedCoordinates(int controllerIndex, ControllerItem item) {
        return calculator.getCalculatedCoordinates(item, controllerIndex);
    }

}

