package r2l.xboxc;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;
import org.junit.Before;
import org.junit.Test;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class XboxControllerListenerTest {

    private static final float BUTTON_PRESSED = 1F;
    private static final float BUTTON_RELEASED = 0F;
    private static final float AXIS_VALUE = .69F;
    private static final int CONTROLLER_INDEX = 0;

    private Controller mockController;
    private XboxControllerObservable mockObservable;
    private XboxControllerListener xboxControllerListener;

    @Before
    public void setup() {
        mockObservable = mock(XboxControllerObservable.class);
        mockController = mock(Controller.class);
        xboxControllerListener = new XboxControllerListener(CONTROLLER_INDEX, mockObservable);
    }

    @Test
    public void buttonDown_shouldUpdateStateObservable() {
        int buttonCode = Xbox.A;
        ControllerItem button = ControllerItem.BUTTON_A;
        xboxControllerListener.buttonDown(mockController, buttonCode);
        verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, button, BUTTON_PRESSED);
    }

    @Test
    public void buttonUp_shouldUpdateStateObservable() {
        int buttonCode = Xbox.A;
        ControllerItem button = ControllerItem.BUTTON_A;
        xboxControllerListener.buttonUp(mockController, buttonCode);
        verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, button, BUTTON_RELEASED);
    }

    @Test
    public void axisMoved_shouldUpdateStateObservable() {
        int axisCode = Xbox.L_STICK_HORIZONTAL_AXIS;
        ControllerItem axis = ControllerItem.AXIS_L_STICK_HORIZONTAL_AXIS;
        xboxControllerListener.axisMoved(mockController, axisCode, AXIS_VALUE);
        verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, axis, AXIS_VALUE);
    }

    @Test
    public void povMoved_shouldUpdateStateObservable() {
        int povCode = Xbox.DPAD_UP;
        ControllerItem povDirection = ControllerItem.DPAD_NORTH;
        xboxControllerListener.povMoved(mockController, povCode, PovDirection.north);
        verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, povDirection, BUTTON_PRESSED);
    }

    @Test
    public void povMoved_shouldReleaseAllDPadDirections() {
        int povCode = Xbox.DPAD_UP;
        xboxControllerListener.povMoved(mockController, povCode, PovDirection.north);
        ControllerItem.getDPadControllerItems()
                .forEach(controllerItem -> verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, controllerItem, BUTTON_RELEASED));
    }

    @Test
    public void buttonDown_shouldReturnFalse() {
        int buttonCode = Xbox.A;
        boolean result = xboxControllerListener.buttonDown(mockController, buttonCode);
        assertThat("buttonDown should return false", result, is(false));
    }

    @Test
    public void buttonUp_shouldReturnFalse() {
        int buttonCode = Xbox.A;
        boolean result = xboxControllerListener.buttonUp(mockController, buttonCode);
        assertThat("buttonUp should return false", result, is(false));
    }

    @Test
    public void axisMoved_shouldReturnFalse() {
        int axisCode = Xbox.L_STICK_HORIZONTAL_AXIS;
        boolean result = xboxControllerListener.axisMoved(mockController, axisCode, AXIS_VALUE);
        assertThat("axisMoved should return false", result, is(false));
    }

    @Test
    public void povMoved_shouldReturnFalse() {
        int povCode = Xbox.DPAD_UP;
        boolean result = xboxControllerListener.povMoved(mockController, povCode, PovDirection.north);
        assertThat("povMoved should return false", result, is(false));
    }
}
