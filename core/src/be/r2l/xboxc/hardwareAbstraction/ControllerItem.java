package be.r2l.xboxc.hardwareAbstraction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum ControllerItem {
    //buttons
    BUTTON_A(ControllerItemCodes.BUTTON_A),
    BUTTON_B(ControllerItemCodes.BUTTON_B),
    BUTTON_X(ControllerItemCodes.BUTTON_X),
    BUTTON_Y(ControllerItemCodes.BUTTON_Y),
    BUTTON_GUIDE(ControllerItemCodes.BUTTON_GUIDE),
    BUTTON_L_BUMPER(ControllerItemCodes.BUTTON_L_BUMPER),
    BUTTON_R_BUMPER(ControllerItemCodes.BUTTON_R_BUMPER),
    BUTTON_BACK(ControllerItemCodes.BUTTON_BACK),
    BUTTON_START(ControllerItemCodes.BUTTON_START),
    BUTTON_DPAD_UP(ControllerItemCodes.BUTTON_DPAD_UP),
    BUTTON_DPAD_DOWN(ControllerItemCodes.BUTTON_DPAD_DOWN),
    BUTTON_DPAD_LEFT(ControllerItemCodes.BUTTON_DPAD_LEFT),
    BUTTON_DPAD_RIGHT(ControllerItemCodes.BUTTON_DPAD_RIGHT),
    BUTTON_L_STICK(ControllerItemCodes.BUTTON_L_STICK),
    BUTTON_R_STICK(ControllerItemCodes.BUTTON_R_STICK),
    //D-pad
    DPAD_NORTH(ControllerItemCodes.DPAD_NORTH),
    DPAD_NORTH_EAST(ControllerItemCodes.DPAD_NORTH_EAST),
    DPAD_EAST(ControllerItemCodes.DPAD_EAST),
    DPAD_SOUTH_EAST(ControllerItemCodes.DPAD_SOUTH_EAST),
    DPAD_SOUTH(ControllerItemCodes.DPAD_SOUTH),
    DPAD_SOUTH_WEST(ControllerItemCodes.DPAD_SOUTH_WEST),
    DPAD_WEST(ControllerItemCodes.DPAD_WEST),
    DPAD_NORTH_WEST(ControllerItemCodes.DPAD_NORTH_WEST),
    DPAD_CENTER(ControllerItemCodes.DPAD_CENTER),
    //Axis
    AXIS_L_TRIGGER(ControllerItemCodes.AXIS_L_TRIGGER),
    AXIS_R_TRIGGER(ControllerItemCodes.AXIS_R_TRIGGER),
    AXIS_L_STICK_VERTICAL_AXIS(ControllerItemCodes.AXIS_L_STICK_VERTICAL_AXIS),
    AXIS_L_STICK_HORIZONTAL_AXIS(ControllerItemCodes.AXIS_L_STICK_HORIZONTAL_AXIS),
    AXIS_R_STICK_VERTICAL_AXIS(ControllerItemCodes.AXIS_R_STICK_VERTICAL_AXIS),
    AXIS_R_STICK_HORIZONTAL_AXIS(ControllerItemCodes.AXIS_R_STICK_HORIZONTAL_AXIS),
    //just in case
    UNKNOWN(-1);

    private static final Map<String, List<ControllerItem>> CONTROLLER_ITEMS_BY_TYPE;

    public final int code;

    static {
        CONTROLLER_ITEMS_BY_TYPE = Arrays.stream(ControllerItem.values())
                .collect(Collectors.groupingBy(controllerItem -> controllerItem.name().split("_")[0]));
    }

    ControllerItem(int code) {
        this.code = code;
    }

    public static ControllerItem getButtonForCode(int code) {
        return getButtons().stream()
                .filter(controllerItem -> controllerItem.code == code)
                .findAny()
                .orElse(UNKNOWN);
    }

    public static ControllerItem getAxisForCode(int code) {
        return getAxes().stream()
                .filter(controllerItem -> controllerItem.code == code)
                .findAny()
                .orElse(UNKNOWN);
    }

    public static ControllerItem getDpadDirectionForCode(int code) {
        return getDPadControllerItems().stream()
                .filter(controllerItem -> controllerItem.code == code)
                .findAny()
                .orElse(UNKNOWN);
    }

    /**
     *
     * Use this instead of the normal enum.getValueOf as valueOfLabel
     * will return the UNKNOWN controllerItem and not null.
     *
     * @param name the label of the ControllerItem
     * @return the controller item with the given name
     */
    public static ControllerItem valueOfLabel(String name) {
        return Arrays.stream(ControllerItem.values())
                .filter(item -> item.name().equals(name))
                .findAny()
                .orElse(UNKNOWN);
    }

    public static List<ControllerItem> getButtons() {
        return CONTROLLER_ITEMS_BY_TYPE.get("BUTTON");
    }

    public static List<ControllerItem> getAxes() {
        return CONTROLLER_ITEMS_BY_TYPE.get("AXIS");
    }

    public static List<ControllerItem> getDPadControllerItems() {
        return CONTROLLER_ITEMS_BY_TYPE.get("DPAD");
    }

}
