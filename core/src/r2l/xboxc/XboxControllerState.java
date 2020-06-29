package r2l.xboxc;

import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class XboxControllerState {

    private static final Float INITIAL_VALUE = 0f;
    private final int controllerIndex;
    private Map<ControllerItem, Float> CURRENT_CONTROLLER_STATE = new HashMap<>();

    public XboxControllerState(int controllerIndex) {
        this.controllerIndex = controllerIndex;
        Arrays.stream(ControllerItem.values()).forEach(item -> CURRENT_CONTROLLER_STATE.put(item, INITIAL_VALUE));
    }

    public int getControllerIndex() {
        return controllerIndex;
    }

    public float getCurrentValue(ControllerItem item) {
        return CURRENT_CONTROLLER_STATE.get(item);
    }

    public void setValue(ControllerItem item, Float newValue) {
        CURRENT_CONTROLLER_STATE.put(item, newValue);
    }
}
