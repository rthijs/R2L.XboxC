package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.controllers.mappings.Xbox;

import java.util.stream.Stream;

public enum Button {
    A(Xbox.A),
    B(Xbox.B),
    X(Xbox.X),
    Y(Xbox.Y),
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

