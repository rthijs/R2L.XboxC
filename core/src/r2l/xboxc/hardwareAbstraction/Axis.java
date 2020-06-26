package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.controllers.mappings.Xbox;

import java.util.stream.Stream;

public enum Axis {
    L_TRIGGER(Xbox.L_TRIGGER),
    R_TRIGGER(Xbox.R_TRIGGER),
    L_STICK_VERTICAL_AXIS(Xbox.L_STICK_VERTICAL_AXIS),
    L_STICK_HORIZONTAL_AXIS(Xbox.L_STICK_HORIZONTAL_AXIS),
    R_STICK_VERTICAL_AXIS(Xbox.R_STICK_VERTICAL_AXIS),
    R_STICK_HORIZONTAL_AXIS(Xbox.R_STICK_HORIZONTAL_AXIS),
    UNKNOWN(-1);

    public final int code;

    Axis(int code) {
        this.code = code;
    }

    public static Axis valueOfCode(int code) {
        return Stream.of(Axis.values())
                .filter(axis -> code == axis.code)
                .findAny()
                .orElse(UNKNOWN);
    }
}
