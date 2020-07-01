package r2l.xboxc.view;

import r2l.xboxc.XboxControllerObservable;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.awt.*;

public class MarkerOverlayHelper {

    private static final XboxControllerObservable XBOX_CONTROLLER_OBSERVABLE = XboxControllerObservable.getInstance();
    private static final float THRESHOLD_NO_VALUE = 0.005f; //for decent xbox 360 controllers set this to .00005, for cheap chinese SNES controllers use .005
    private static final float TRIGGER_UNPRESSED_VALUE = -1f;

    public static boolean markerShouldBeDrawn(int controllerIndex, ControllerItem item) {
        return itemIsKnown(item)
                && conditionsAreMetIfButton(item, controllerIndex)
                && conditionsAreMetIfDPad(item, controllerIndex)
                && conditionsAreMetIfJoystick(item, controllerIndex)
                && conditionsAreMetIfTrigger(item, controllerIndex);
    }

    private static boolean conditionsAreMetIfTrigger(ControllerItem item, int controllerIndex) {
        return !isTrigger(item) || isPressed(item, controllerIndex);
    }

    private static boolean conditionsAreMetIfJoystick(ControllerItem item, int controllerIndex) {
        return !isJoyStickAxis(item) || axisMarkerMustBeDrawn(item, controllerIndex);
    }

    private static boolean conditionsAreMetIfDPad(ControllerItem item, int controllerIndex) {
        return !isDPad(item) || (!isDPadCenter(item) && isPressed(item, controllerIndex));
    }

    private static boolean conditionsAreMetIfButton(ControllerItem item, int controllerIndex) {
        return !isButton(item) || isPressed(item, controllerIndex);
    }

    private static boolean isJoyStickAxis(ControllerItem item) {
        return item.name().contains("STICK");
    }

    private static boolean axisMarkerMustBeDrawn(ControllerItem controllerItem, int controllerIndex) {
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

    private static boolean isPressed(ControllerItem item, int controllerIndex) {
        if (isTrigger(item)) {
            return XBOX_CONTROLLER_OBSERVABLE.getControllerItemValue(controllerIndex, item) > TRIGGER_UNPRESSED_VALUE;
        }
        return isControllerItemValueGreaterThanThreshold(item, controllerIndex);
    }

    private static boolean isDPadCenter(ControllerItem item) {
        return item.equals(ControllerItem.DPAD_CENTER);
    }

    private static boolean hasAValue(ControllerItem item, int controllerIndex) {
        return isControllerItemValueGreaterThanThreshold(item, controllerIndex);
    }

    private static boolean isControllerItemValueGreaterThanThreshold(ControllerItem item, int controllerIndex) {
        return Math.abs(XBOX_CONTROLLER_OBSERVABLE.getControllerItemValue(controllerIndex, item)) > THRESHOLD_NO_VALUE;
    }

    public static Point getCalculatedCoordinates(int controllerIndex, ControllerItem item) {
        return MarkerCoordinateCalculator.getCalculatedCoordinates(item, controllerIndex);
    }

}

