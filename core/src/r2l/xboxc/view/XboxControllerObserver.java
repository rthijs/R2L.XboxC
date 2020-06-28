package r2l.xboxc.view;

import com.badlogic.gdx.controllers.Controllers;
import r2l.xboxc.XboxControllerObservable;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class XboxControllerObserver implements PropertyChangeListener {

    private static final List<Map<ControllerItem, Float>> CURRENT_CONTROLLER_STATE = new ArrayList<>();

    private static XboxControllerObserver instance;

    static {
        for (int i = 0; i < Controllers.getControllers().size; i++) {
            Map<ControllerItem, Float> mapToAdd = new HashMap<>();
            for (ControllerItem item : ControllerItem.values()) {
                mapToAdd.put(item, 0F);
                CURRENT_CONTROLLER_STATE.add(mapToAdd);
            }
        }
    }

    private XboxControllerObserver() {
        XboxControllerObservable.getInstance().addPropertyChangeListener(this);
    }

    public static XboxControllerObserver getInstance() {
        if (instance == null) {
            instance = new XboxControllerObserver();
        }
        return instance;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        int controllerIndex = ((IndexedPropertyChangeEvent) propertyChangeEvent).getIndex();
        float newValue = (Float) propertyChangeEvent.getNewValue();
        Map<ControllerItem, Float> mapToUpdate = CURRENT_CONTROLLER_STATE.get(controllerIndex);
        mapToUpdate.put(ControllerItem.valueOf(propertyChangeEvent.getPropertyName()), newValue);
    }

    public float getCurrentValue(int controllerIndex, ControllerItem item) {
        return CURRENT_CONTROLLER_STATE.get(controllerIndex).get(item);
    }
}
