package r2l.xboxc;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class XboxControllerListenerTest {

    private static final float BUTTON_PRESSED = 1F;
    private static final float BUTTON_RELEASED = 0F;
    private static final AtomicInteger CONTROLLER_INDEX = new AtomicInteger(123);
    private XboxControllerObservable xboxControllerObservable;
    private XboxControllerListener xboxControllerListener;
    @Mock
    private Controller controller;
    @Mock
    private Controllers controllers;

    @Before
    public void setup() {
        xboxControllerObservable = new XboxControllerObservable();
        xboxControllerListener = new XboxControllerListener(CONTROLLER_INDEX.getAndIncrement(), xboxControllerObservable);

        when(Controllers.getControllers()).thenReturn(new Array<>());
    }
    @Test
    public void buttonDown_shouldUpdateStateObservable() {
        xboxControllerListener.buttonDown(controller, Xbox.A);
        Float value = xboxControllerObservable.getControllerItemValue(CONTROLLER_INDEX.get(), ControllerItem.BUTTON_A);
        assertThat("Value when button pressed should be 1", value, is(BUTTON_PRESSED));
    }
}
