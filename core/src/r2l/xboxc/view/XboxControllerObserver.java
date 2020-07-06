package r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import r2l.xboxc.XboxControllerObservable;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class XboxControllerObserver implements PropertyChangeListener {

    public XboxControllerObserver(XboxControllerObservable observable) {
        observable.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        int controllerIndex = ((IndexedPropertyChangeEvent) propertyChangeEvent).getIndex();
        float newValue = (float) propertyChangeEvent.getNewValue();
        float oldValue = (float) propertyChangeEvent.getOldValue();
        String item = propertyChangeEvent.getPropertyName();
        Gdx.app.log(controllerIndex + ":" + item , oldValue + " -> " + newValue );
        //TODO heb ik dit wel nodig?
    }


}
