package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.controllers.mappings.Xbox;

import java.util.stream.Stream;

public enum Button {
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
    UNKNOWN(-1);

    public final int code;

    Button(int code) {
        this.code = code;
    }

    public static Button valueOfCode(int code) {
        return Stream.of(Button.values())
                .filter(button -> code == button.code)
                .findAny()
                .orElse(UNKNOWN);
    }
}

