package r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import r2l.xboxc.XboxControllerObservable;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class XboxControllerObserver implements PropertyChangeListener {

    private static XboxControllerObserver INSTANCE;

    private Map<ControllerItem, Float> currentControllerState;

    private XboxControllerObserver() {
        XboxControllerObservable.getInstance().addPropertyChangeListener(this);
    }

    public static XboxControllerObserver getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XboxControllerObserver();
        }
        return INSTANCE;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Gdx.app.log("Event ontvangen", propertyChangeEvent.toString());
    }

    public float getValue(ControllerItem item) {
        return (float) Math.random(); //TODO
    }
}
