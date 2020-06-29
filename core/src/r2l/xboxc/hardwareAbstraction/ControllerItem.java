package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum ControllerItem {
    //buttons
    A(Xbox.A),
    B(Xbox.B),
    X(Xbox.X),
    Y(Xbox.Y),
    GUIDE(Xbox.GUIDE),
    L_BUMPER(Xbox.L_BUMPER),
    R_BUMPER(Xbox.R_BUMPER),
    BACK(Xbox.BACK),
    START(Xbox.START),
    DPAD_UP(Xbox.DPAD_UP),
    DPAD_DOWN(Xbox.DPAD_DOWN),
    DPAD_LEFT(Xbox.DPAD_LEFT),
    DPAD_RIGHT(Xbox.DPAD_RIGHT),
    L_STICK(Xbox.L_STICK),
    R_STICK(Xbox.R_STICK),
    //D-pad
    NORTH(PovDirection.north.ordinal()),
    NORTH_EAST(PovDirection.northEast.ordinal()),
    EAST(PovDirection.east.ordinal()),
    SOUTH_EAST(PovDirection.southEast.ordinal()),
    SOUTH(PovDirection.south.ordinal()),
    SOUTH_WEST(PovDirection.southWest.ordinal()),
    WEST(PovDirection.west.ordinal()),
    NORTH_WEST(PovDirection.northWest.ordinal()),
    CENTER(PovDirection.center.ordinal()),
    //Axis
    L_TRIGGER(Xbox.L_TRIGGER),
    R_TRIGGER(Xbox.R_TRIGGER),
    L_STICK_VERTICAL_AXIS(Xbox.L_STICK_VERTICAL_AXIS),
    L_STICK_HORIZONTAL_AXIS(Xbox.L_STICK_HORIZONTAL_AXIS),
    R_STICK_VERTICAL_AXIS(Xbox.R_STICK_VERTICAL_AXIS),
    R_STICK_HORIZONTAL_AXIS(Xbox.R_STICK_HORIZONTAL_AXIS),
    //just in case
    UNKNOWN(-1);

    private static final List<ControllerItem> axis = new ArrayList<>(); // axis can share code with button

    public final int code;

    static {
        Arrays.stream(ControllerItem.values())
                .filter(controllerItem -> controllerItem.name().contains("AXIS") || controllerItem.name().contains("TRIGGER"))
                .forEach(controllerItem -> axis.add(controllerItem));
        Gdx.app.log("axis", axis.toString());
    }


    ControllerItem(int code) {
        this.code = code;
    }

    public static ControllerItem valueOfCode(int code) {
        return Stream.of(ControllerItem.values())
                .filter(item -> code == item.code)
                .findAny()
                .orElse(UNKNOWN);
    }

    public static ControllerItem getAxisForCode(int code) {
        return axis.stream()
                .filter(controllerItem -> controllerItem.code == code)
                .findFirst()
                .orElse(UNKNOWN);
    }

}
