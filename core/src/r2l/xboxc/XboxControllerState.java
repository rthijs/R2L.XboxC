package r2l.xboxc;

import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class XboxControllerState {

    private static final Float INITIAL_VALUE = 0f;
    private static final Float TRIGGER_INITIAL_VALUE = -1f;
    private final int controllerIndex;
    private final Map<ControllerItem, Float> CURRENT_CONTROLLER_STATE = new HashMap<>();

    public XboxControllerState(int controllerIndex) {
        this.controllerIndex = controllerIndex;
        Arrays.stream(ControllerItem.values()).forEach(item -> CURRENT_CONTROLLER_STATE.put(item, INITIAL_VALUE));
        //triggers initialize at 0 but once they have been touched the resting position is at -1
        CURRENT_CONTROLLER_STATE.put(ControllerItem.AXIS_L_TRIGGER, TRIGGER_INITIAL_VALUE);
        CURRENT_CONTROLLER_STATE.put(ControllerItem.AXIS_R_TRIGGER, TRIGGER_INITIAL_VALUE);
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
