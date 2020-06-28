package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public final class XboxControllerObservable {

    private static XboxControllerObservable INSTANCE;
    private final PropertyChangeSupport support;

    private XboxControllerObservable() {
        support = new PropertyChangeSupport(this);
    }

    public static XboxControllerObservable getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XboxControllerObservable();
        }
        return INSTANCE;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void setControllerItemValue(int controllerIndex, ControllerItem item, Float oldValue, Float newValue) {
        Gdx.app.log(controllerIndex  + ":" + item.name(), oldValue.toString() + " -> " + newValue.toString() );
        support.fireIndexedPropertyChange(item.name(), controllerIndex, 0,newValue);
    }
}
