package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class XboxControllerObservable {

    private static final List<XboxControllerState> XBOX_CONTROLLER_STATES = new ArrayList<>();
    private static XboxControllerObservable instance;
    private final PropertyChangeSupport support;

    static {
        IntStream.range(0, Controllers.getControllers().size)
                .forEach(i -> XBOX_CONTROLLER_STATES.add(new XboxControllerState(i)));
    }

    private XboxControllerObservable() {
        support = new PropertyChangeSupport(this);
    }

    public static XboxControllerObservable getInstance() {
        if (instance == null) {
            instance = new XboxControllerObservable();
        }
        return instance;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void setControllerItemValue(int controllerIndex, ControllerItem item, Float newValue) {
        XBOX_CONTROLLER_STATES.stream()
                .filter(xboxControllerState -> xboxControllerState.getControllerIndex() ==  controllerIndex)
                .findFirst()
                .ifPresent(xboxControllerState -> {
                    support.fireIndexedPropertyChange(item.name(), controllerIndex, getControllerItemValue(controllerIndex, item), newValue);
                    xboxControllerState.setValue(item, newValue);
                });
    }

    public Float getControllerItemValue(int controllerIndex, ControllerItem item) {
        return XBOX_CONTROLLER_STATES.stream()
                .filter(xboxControllerState -> xboxControllerState.getControllerIndex() == controllerIndex)
                .findFirst()
                .map(xboxControllerState -> xboxControllerState.getCurrentValue(item))
                .orElse(0f);
    }
}