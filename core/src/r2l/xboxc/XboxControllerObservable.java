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
                .forEach(i -> XBOX_CONTROLLER_STATES.add(new XboxControllerState()));
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
        Float oldValue = XBOX_CONTROLLER_STATES.get(controllerIndex).getCurrentValue(item);
        XBOX_CONTROLLER_STATES.get(controllerIndex).setValue(item, newValue);
        Gdx.app.log(controllerIndex + ":" + item.name(), oldValue.toString() + " -> " + newValue.toString());
        support.fireIndexedPropertyChange(item.name(), controllerIndex, oldValue, newValue);
    }
}
