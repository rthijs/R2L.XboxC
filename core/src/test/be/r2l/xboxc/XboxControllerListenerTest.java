package test.be.r2l.xboxc;

import com.badlogic.gdx.controllers.Controller;

import be.r2l.xboxc.XboxControllerListener;
import be.r2l.xboxc.XboxControllerObservable;
import be.r2l.xboxc.hardwareAbstraction.ControllerItem;
import be.r2l.xboxc.hardwareAbstraction.ControllerItemCodes;

import org.junit.Before;
import org.junit.Test;

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
        int buttonCode = ControllerItemCodes.BUTTON_A;
        ControllerItem button = ControllerItem.BUTTON_A;
        xboxControllerListener.buttonDown(mockController, buttonCode);
        verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, button, BUTTON_PRESSED);
    }

    @Test
    public void buttonUp_shouldUpdateStateObservable() {
        int buttonCode = ControllerItemCodes.BUTTON_A;
        ControllerItem button = ControllerItem.BUTTON_A;
        xboxControllerListener.buttonUp(mockController, buttonCode);
        verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, button, BUTTON_RELEASED);
    }

    @Test
    public void axisMoved_shouldUpdateStateObservable() {
        int axisCode = ControllerItemCodes.AXIS_L_STICK_HORIZONTAL_AXIS;
        ControllerItem axis = ControllerItem.AXIS_L_STICK_HORIZONTAL_AXIS;
        xboxControllerListener.axisMoved(mockController, axisCode, AXIS_VALUE);
        verify(mockObservable, times(1)).setControllerItemValue(CONTROLLER_INDEX, axis, AXIS_VALUE);
    }

    @Test
    public void buttonDown_shouldReturnFalse() {
        int buttonCode = ControllerItemCodes.BUTTON_A;
        boolean result = xboxControllerListener.buttonDown(mockController, buttonCode);
        assertThat("buttonDown should return false", result, is(false));
    }

    @Test
    public void buttonUp_shouldReturnFalse() {
        int buttonCode = ControllerItemCodes.BUTTON_A;
        boolean result = xboxControllerListener.buttonUp(mockController, buttonCode);
        assertThat("buttonUp should return false", result, is(false));
    }

    @Test
    public void axisMoved_shouldReturnFalse() {
        int axisCode = ControllerItemCodes.AXIS_L_STICK_HORIZONTAL_AXIS;
        boolean result = xboxControllerListener.axisMoved(mockController, axisCode, AXIS_VALUE);
        assertThat("axisMoved should return false", result, is(false));
    }
}
