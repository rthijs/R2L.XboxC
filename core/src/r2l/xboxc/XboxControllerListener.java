package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
import r2l.xboxc.hardwareAbstraction.Button;
import r2l.xboxc.hardwareAbstraction.Axis;
import r2l.xboxc.hardwareAbstraction.DPad;

public class XboxControllerListener extends ControllerAdapter {

    private final int controllerIndex;

    public XboxControllerListener(int controllerIndex) {
        this.controllerIndex = controllerIndex;
    }


    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Gdx.app.log("buttonDown", String.format("Controller [%s] - button [%s]", controllerIndex, Button.valueOfCode(buttonCode)));
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        Gdx.app.log("buttonUp  ", String.format("Controller [%s] - button [%s]", controllerIndex, Button.valueOfCode(buttonCode)));
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        Gdx.app.log("axisMoved ", String.format("Controller [%s] - axis [%s] : %s", controllerIndex, Axis.valueOfCode(axisCode), value));
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        Gdx.app.log("povMoved  ", String.format("Controller [%s] - povCode [%s] (%s)", controllerIndex, DPad.valueOfCode(value), value));
        return false;
    }
}
