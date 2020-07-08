package r2l.xboxc;

import com.badlogic.gdx.controllers.Controllers;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class XboxControllerObservable {

    private final List<XboxControllerState> XBOX_CONTROLLER_STATES = new ArrayList<>();
    private final PropertyChangeSupport support;

    public XboxControllerObservable() {
        support = getSupport();
        IntStream.range(0, numberOfControllers()).forEach(i -> XBOX_CONTROLLER_STATES.add(new XboxControllerState(i)));
    }

    protected PropertyChangeSupport getSupport() {
        return new PropertyChangeSupport(this);
    }

    protected int numberOfControllers() {
        return Controllers.getControllers().size;
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
                .orElse(0F);
    }
}