package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

public class XboxControllerListener extends ControllerAdapter {

    private static final XboxControllerObservable OBSERVABLE = XboxControllerObservable.getInstance();
    private static final float BUTTON_PRESSED = 1F;
    private static final float BUTTON_RELEASED = 0F;

    private final int controllerIndex;

    public XboxControllerListener(int controllerIndex) {
        this.controllerIndex = controllerIndex;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Gdx.app.log("buttonDown", String.format("Controller [%s] - button [%s]", controllerIndex, ControllerItem.getButtonForCode(buttonCode)));
        ControllerItem item = ControllerItem.getButtonForCode(buttonCode);
        OBSERVABLE.setControllerItemValue(controllerIndex, item, BUTTON_PRESSED);
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        Gdx.app.log("buttonUp  ", String.format("Controller [%s] - button [%s]", controllerIndex, ControllerItem.getButtonForCode(buttonCode)));
        ControllerItem item = ControllerItem.getButtonForCode(buttonCode);
        OBSERVABLE.setControllerItemValue(controllerIndex, item, BUTTON_RELEASED);
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        Gdx.app.log("axisMoved ", String.format("Controller [%s] - axis [%s] : %s", controllerIndex, ControllerItem.getAxisForCode(axisCode), value));
        ControllerItem item = ControllerItem.getAxisForCode(axisCode);
        OBSERVABLE.setControllerItemValue(controllerIndex, item, value);
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        Gdx.app.log("povMoved  ", String.format("Controller [%s] - povCode [%s] (%s)", controllerIndex, ControllerItem.getDpadDirectionForCode(value.ordinal()), value));
        ControllerItem item = ControllerItem.getDpadDirectionForCode(value.ordinal());
        //only one direction can be active at the same time
        releaseAllDPadDirections(controllerIndex);
        OBSERVABLE.setControllerItemValue(controllerIndex, item, BUTTON_PRESSED);
        return false;
    }

    private void releaseAllDPadDirections(int controllerIndex) {
        ControllerItem.getDPadControllerItems().stream()
                .forEach(controllerItem -> OBSERVABLE.setControllerItemValue(controllerIndex, controllerItem, BUTTON_RELEASED));
    }
}
