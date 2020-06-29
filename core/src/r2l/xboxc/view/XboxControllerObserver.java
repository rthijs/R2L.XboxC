package r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import r2l.xboxc.XboxControllerObservable;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class XboxControllerObserver implements PropertyChangeListener {

    private static XboxControllerObserver instance;

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
        Gdx.app.log("XboxControllerObserver.propertyChange", "Wat doe ik hiermee? " + ((IndexedPropertyChangeEvent) propertyChangeEvent).toString());
    }


}
