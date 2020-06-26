package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.controllers.PovDirection;

import java.util.stream.Stream;

public enum DPad {
    NORTH(PovDirection.north),
    NORTH_EAST(PovDirection.northEast),
    EAST(PovDirection.east),
    SOUTH_EAST(PovDirection.southEast),
    SOUTH(PovDirection.south),
    SOUTH_WEST(PovDirection.southWest),
    WEST(PovDirection.west),
    NORTH_WEST(PovDirection.northWest),
    CENTER(PovDirection.center),
    UNKNOWN(null);

    public final PovDirection code;

    DPad(PovDirection code) {
        this.code = code;
    }

    public static DPad valueOfCode(PovDirection code) {
        return Stream.of(DPad.values())
                .filter(dpad -> code == dpad.code)
                .findAny()
                .orElse(UNKNOWN);
    }
}
