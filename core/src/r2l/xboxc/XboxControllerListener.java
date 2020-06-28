package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
import r2l.xboxc.hardwareAbstraction.ControllerItem;
import r2l.xboxc.view.XboxControllerObserver;

public class XboxControllerListener extends ControllerAdapter {

    private static final XboxControllerObserver OBSERVER = XboxControllerObserver.getInstance();
    private static final XboxControllerObservable OBSERVABLE = XboxControllerObservable.getInstance();
    private static final float BUTTON_PRESSED = 1F;
    private static final float BUTTON_RELEASED = 0F;

    private final int controllerIndex;

    public XboxControllerListener(int controllerIndex) {
        this.controllerIndex = controllerIndex;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Gdx.app.log("buttonDown", String.format("Controller [%s] - button [%s]", controllerIndex, ControllerItem.valueOfCode(buttonCode)));
        ControllerItem item = ControllerItem.valueOfCode(buttonCode);
        float oldValue = OBSERVER.getCurrentValue(controllerIndex, item);
        OBSERVABLE.setControllerItemValue(controllerIndex,item, oldValue,BUTTON_PRESSED);
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        Gdx.app.log("buttonUp  ", String.format("Controller [%s] - button [%s]", controllerIndex, ControllerItem.valueOfCode(buttonCode)));
        ControllerItem item = ControllerItem.valueOfCode(buttonCode);
        float oldValue = OBSERVER.getCurrentValue(controllerIndex, item);
        OBSERVABLE.setControllerItemValue(controllerIndex,item, oldValue,BUTTON_RELEASED);
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        Gdx.app.log("axisMoved ", String.format("Controller [%s] - axis [%s] : %s", controllerIndex, ControllerItem.valueOfCode(axisCode), value));
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        Gdx.app.log("povMoved  ", String.format("Controller [%s] - povCode [%s] (%s)", controllerIndex, ControllerItem.valueOfCode(value.ordinal()), value));
        return false;
    }
}
