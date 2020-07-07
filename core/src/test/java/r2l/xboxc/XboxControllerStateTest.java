package r2l.xboxc;

import org.junit.Test;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class XboxControllerStateTest {

    private final static int CONTROLLER_INDEX = 123;
    private final static float DEFAULT_VALUE = 0f;
    private final static float DEFAULT_VALUE_TRIGGERS = -1f;
    final XboxControllerState xboxControllerState = new XboxControllerState(CONTROLLER_INDEX);

    @Test
    public void getControllerIndex_shouldReturnValue() {
        assertThat(xboxControllerState.getControllerIndex(), is(CONTROLLER_INDEX));
    }

    @Test
    public void getCurrentValue_shouldReturnDefaultValue_exceptForTriggers() {
        long nonDefaultValueCount = Arrays.stream(ControllerItem.values())
                .filter(item -> !item.name().contains("TRIGGER"))
                .map(xboxControllerState::getCurrentValue)
                .filter(value -> value != DEFAULT_VALUE)
                .count();
        assertThat("Should return default value.",nonDefaultValueCount, is(0L));
    }

    @Test
    public void getCurrentValue_shouldReturnDefaultValue_forTriggers() {
        long nonDefaultValueCount = Arrays.stream(ControllerItem.values())
                .filter(item -> item.name().contains("TRIGGER"))
                .map(xboxControllerState::getCurrentValue)
                .filter(value -> value != DEFAULT_VALUE_TRIGGERS)
                .count();
        assertThat("Should return default value for triggers.", nonDefaultValueCount, is(0L));
    }

    @Test
    public void setValue_shouldSetValue() {
        ControllerItem item = ControllerItem.values()[(new Random().nextInt(ControllerItem.values().length))];
        //ControllerItem item = ControllerItem.values()[0];
        float value = (float) Math.random();
        xboxControllerState.setValue(item, value);
        assertThat("Should set value.", xboxControllerState.getCurrentValue(item), is(value));
    }
}
