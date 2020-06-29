package r2l.xboxc.view;

import r2l.xboxc.XboxControllerObservable;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.awt.*;
import java.util.Optional;

public class MarkerOverlayHelper {

    private static final XboxControllerObservable XBOX_CONTROLLER_OBSERVABLE = XboxControllerObservable.getInstance();
    private static final float THRESHOLD_NO_VALUE = 0.005f; //for decent xbox 360 controllers set this to .00005, for cheap chinese SNES controllers use .005

    public static boolean markerShouldBeDrawn(int controllerIndex, ControllerItem item) {
        return itemIsKnown(item)
                && isPressedIfButton(item, controllerIndex)
                && isNotCenteredIfDPadAndIsPressed(item, controllerIndex)
                && isNotCenteredIfAxis(item, controllerIndex)
                && markerMustBeDrawnForThisJoystickAxis(item, controllerIndex);
    }

    private static boolean markerMustBeDrawnForThisJoystickAxis(ControllerItem item, int controllerIndex) {
        /*
        Both joysticks have two axes, to prevent two markers from being drawn on top of each other
        when both are not zero we must draw a single marker in every case except for when both are
        zero.
        let:
            "draw vertical axis marker" = VM
            "draw horizontal axis marker" = HM
            "vertical axis value not zero" = V
            "horizontal axis value not zero" = H
        then:
            VM = V
            and
            HM = H AND NOT V
        truth table:
                V       H       VM      HM          # of markers drawn
                0       0       0       0               0
                0       1       0       1               1
                1       0       1       0               1
                1       1       1       0               1
         */
        //TODO fix ugly code
        return !isJoyStickAxis(item).isPresent()
                ||
                isJoyStickAxis(item)
                .filter(joystickAxis -> axisMarkerMustBeDrawn(joystickAxis,controllerIndex))
                .isPresent();
    }

    private static Optional<ControllerItem> isJoyStickAxis(ControllerItem item) {
        return ControllerItem.getAxes().stream()
                .filter(controllerItem -> controllerItem.name().contains("STICK"))
                .filter(controllerItem -> controllerItem.equals(item))
                .findAny();
    }

    private static boolean axisMarkerMustBeDrawn(ControllerItem controllerItem, int controllerIndex) {
        if (isHorizontalAxis(controllerItem)) {
            return hasAValue(controllerItem, controllerIndex) && !hasAValue(getVerticalAxisForHorizontal(controllerItem), controllerIndex);
        } else {
            return !isCentered(controllerItem, controllerIndex);
        }

    }

    private static ControllerItem getVerticalAxisForHorizontal(ControllerItem item) {
        return ControllerItem.getAxes().stream()
                .filter(axis -> axis.name().equals(getVerticalAxisName(item)))
                .findAny()
                .orElse(ControllerItem.UNKNOWN);
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

    private static boolean isPressedIfButton(ControllerItem item, int controllerIndex) {
        return !isButton(item) || (isButton(item) && isPressed(item, controllerIndex));
    }

    private static boolean isNotCenteredIfAxis(ControllerItem item, int controllerIndex) {
        return !isAxis(item) || (isAxis(item) && !isCentered(item, controllerIndex));
    }

    private static boolean isNotCenteredIfDPadAndIsPressed(ControllerItem item, int controllerIndex) {
        return !isDPad(item) || (isDPad(item) && !isDPadCenter(item) && isPressed(item, controllerIndex));
    }

    private static boolean isButton(ControllerItem item) {
        return ControllerItem.getButtons().stream().anyMatch(i -> i.equals(item));
    }

    private static boolean isAxis(ControllerItem item) {
        return ControllerItem.getAxes().stream().anyMatch(i -> i.equals(item));
    }

    private static boolean isDPad(ControllerItem item) {
        return ControllerItem.getDPadControllerItems().stream().anyMatch(i -> i.equals(item));
    }

    private static boolean isPressed(ControllerItem item, int controllerIndex) {
        return isControllerItemValueGreaterThanThreshold(item, controllerIndex);
    }

    private static boolean isDPadCenter(ControllerItem item) {
        return item.equals(ControllerItem.DPAD_CENTER);
    }

    private static boolean isCentered(ControllerItem item, int controllerIndex) {
        return !isControllerItemValueGreaterThanThreshold(item, controllerIndex);
    }

    private static boolean hasAValue(ControllerItem item, int controllerIndex) {
        return isControllerItemValueGreaterThanThreshold(item, controllerIndex);
    }

    private static boolean isControllerItemValueGreaterThanThreshold(ControllerItem item, int controllerIndex) {
        return Math.abs(XBOX_CONTROLLER_OBSERVABLE.getControllerItemValue(controllerIndex, item)) > THRESHOLD_NO_VALUE;
    }

    public static Point getCalculatedCoordinates(int controllerIndex, ControllerItem item) {
        //TODO calculate adjusted position for marker
        return HardwareCoordinates.getCoordinates(item);
    }
}
