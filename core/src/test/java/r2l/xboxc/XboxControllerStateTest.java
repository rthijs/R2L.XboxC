package r2l.xboxc;

import org.junit.Test;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.util.Arrays;
import java.util.Random;

public class XboxControllerStateTest {

    private final static int CONTROLLER_INDEX = 123;
    private final static float DEFAULT_VALUE = 0f;
    private final static float DEFAULT_VALUE_TRIGGERS = -1f;
    XboxControllerState xboxControllerState = new XboxControllerState(CONTROLLER_INDEX);

    @Test
    public void getControllerIndex_shouldReturnValue() {
        assert xboxControllerState.getControllerIndex() == CONTROLLER_INDEX;
    }

    @Test
    public void getCurrentValue_shouldReturnDefaultValue_exceptForTriggers() {
        long nonDefaultValueCount = Arrays.stream(ControllerItem.values())
                .filter(item -> !item.name().contains("TRIGGER"))
                .map(item -> xboxControllerState.getCurrentValue(item))
                .filter(value -> value != DEFAULT_VALUE)
                .count();
        assert nonDefaultValueCount == 0l;
    }

    @Test
    public void getCurrentValue_shouldReturnDefaultValue_forTriggers() {
        long nonDefaultValueCount = Arrays.stream(ControllerItem.values())
                .filter(item -> item.name().contains("TRIGGER"))
                .map(item -> xboxControllerState.getCurrentValue(item))
                .filter(value -> value != DEFAULT_VALUE_TRIGGERS)
                .count();
        assert nonDefaultValueCount == 0l;
    }

    @Test
    public void setValue_shouldSetValue() {
        ControllerItem item = ControllerItem.values()[(new Random().nextInt(ControllerItem.values().length))];
        //ControllerItem item = ControllerItem.values()[0];
        float value = (float) Math.random();
        xboxControllerState.setValue(item, value);
        assert xboxControllerState.getCurrentValue(item) == value;
    }
}
