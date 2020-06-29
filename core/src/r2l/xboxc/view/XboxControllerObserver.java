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
        float newValue = (float) propertyChangeEvent.getNewValue();
        float oldValue = (float) propertyChangeEvent.getOldValue();
        String item = propertyChangeEvent.getPropertyName();
        Gdx.app.log(String.valueOf(controllerIndex) + ":" + item , oldValue + " -> " + newValue );
        //TODO heb ik dit wel nodig?
    }


}
