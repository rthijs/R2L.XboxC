package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.controllers.mappings.Xbox;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Button {
    A(Xbox.A),
    B(Xbox.B),
    X(Xbox.X),
    Y(Xbox.Y),
    UNKNOWN(-1);

    private static final Map<Integer, Button> BY_CODE = new HashMap<>(); //for caching

    static {
        Stream.of(Button.values()).forEach(button -> BY_CODE.put(button.code, button)); //fill cache
    }

    public final int code;

    private Button(int code) {
        this.code = code;
    }

    public static Button valueOfCode(int code) {
        return BY_CODE.get(code) == null ? UNKNOWN : BY_CODE.get(code);
    }
}

