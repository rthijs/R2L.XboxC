package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ControllerItem {
    //buttons
    BUTTON_A(Xbox.A),
    BUTTON_B(Xbox.B),
    BUTTON_X(Xbox.X),
    BUTTON_Y(Xbox.Y),
    BUTTON_GUIDE(Xbox.GUIDE),
    BUTTON_L_BUMPER(Xbox.L_BUMPER),
    BUTTON_R_BUMPER(Xbox.R_BUMPER),
    BUTTON_BACK(Xbox.BACK),
    BUTTON_START(Xbox.START),
    BUTTON_DPAD_UP(Xbox.DPAD_UP),
    BUTTON_DPAD_DOWN(Xbox.DPAD_DOWN),
    BUTTON_DPAD_LEFT(Xbox.DPAD_LEFT),
    BUTTON_DPAD_RIGHT(Xbox.DPAD_RIGHT),
    BUTTON_L_STICK(Xbox.L_STICK),
    BUTTON_R_STICK(Xbox.R_STICK),
    //D-pad
    DPAD_NORTH(PovDirection.north.ordinal()),
    DPAD_NORTH_EAST(PovDirection.northEast.ordinal()),
    DPAD_EAST(PovDirection.east.ordinal()),
    DPAD_SOUTH_EAST(PovDirection.southEast.ordinal()),
    DPAD_SOUTH(PovDirection.south.ordinal()),
    DPAD_SOUTH_WEST(PovDirection.southWest.ordinal()),
    DPAD_WEST(PovDirection.west.ordinal()),
    DPAD_NORTH_WEST(PovDirection.northWest.ordinal()),
    DPAD_CENTER(PovDirection.center.ordinal()),
    //Axis
    AXIS_L_TRIGGER(Xbox.L_TRIGGER),
    AXIS_R_TRIGGER(Xbox.R_TRIGGER),
    AXIS_L_STICK_VERTICAL_AXIS(Xbox.L_STICK_VERTICAL_AXIS),
    AXIS_L_STICK_HORIZONTAL_AXIS(Xbox.L_STICK_HORIZONTAL_AXIS),
    AXIS_R_STICK_VERTICAL_AXIS(Xbox.R_STICK_VERTICAL_AXIS),
    AXIS_R_STICK_HORIZONTAL_AXIS(Xbox.R_STICK_HORIZONTAL_AXIS),
    //just in case
    UNKNOWN(-1);

    private static final Map<String, List<ControllerItem>> ControllerItemsByType;

    public final int code;


    static {
        ControllerItemsByType = Arrays.stream(ControllerItem.values())
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

    private static ControllerItem getControllerItem(Stream<ControllerItem> controllerItems, int code) {
        return controllerItems.filter(controllerItem -> controllerItem.code == code)
                .findAny()
                .orElse(UNKNOWN);
    }

    public static ControllerItem valueOfLabel(String name) {
        return Arrays.stream(ControllerItem.values())
                .filter(item -> item.name().equals(name))
                .findAny()
                .orElse(UNKNOWN);
    }

    public static List<ControllerItem> getButtons() {
        return ControllerItemsByType.get("BUTTON");
    }

    public static List<ControllerItem> getAxes() {
        return ControllerItemsByType.get("AXIS");
    }

    public static List<ControllerItem> getDPadControllerItems() {
        return ControllerItemsByType.get("DPAD");
    }

}
