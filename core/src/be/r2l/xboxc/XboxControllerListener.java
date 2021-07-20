package be.r2l.xboxc;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;

import be.r2l.xboxc.communication.SocketClient;
import be.r2l.xboxc.hardwareAbstraction.ControllerItem;

public class XboxControllerListener extends ControllerAdapter {

    private static final float BUTTON_PRESSED = 1F;
    private static final float BUTTON_RELEASED = 0F;

    private final XboxControllerObservable observable;
    private final SocketClient socketClient = SocketClient.getInstance();
    private final int controllerIndex;

    public XboxControllerListener(int controllerIndex, XboxControllerObservable observable) {
        this.controllerIndex = controllerIndex;
        this.observable = observable;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        ControllerItem item = ControllerItem.getButtonForCode(buttonCode);
        observable.setControllerItemValue(controllerIndex, item, BUTTON_PRESSED);
        socketClient.sendMessage(item.name());
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        ControllerItem item = ControllerItem.getButtonForCode(buttonCode);
        observable.setControllerItemValue(controllerIndex, item, BUTTON_RELEASED);
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        ControllerItem item = ControllerItem.getAxisForCode(axisCode);
        observable.setControllerItemValue(controllerIndex, item, value);
        return false;
    }
}
