package r2l.xboxc;

import com.badlogic.gdx.controllers.Controller;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class XboxControllerHelperTest {

    private static final int NUMBER_OF_CONTROLLERS = 10;

    private XboxControllerHelper xboxControllerHelper;
    private List<Controller> mockControllers;

    @Mock
    private XboxControllerListener listener;

    @Before
    public void setup() {
        xboxControllerHelper = new XboxControllerHelperForTesting();
        mockControllers = null;
        mockControllers = IntStream.range(0, NUMBER_OF_CONTROLLERS).mapToObj(i -> mock(Controller.class)).collect(Collectors.toList());
    }

    @Test
    public void addListenerToEveryController_shouldAddListenerToEveryController() {
        xboxControllerHelper.addListenerToEveryController();
        mockControllers.forEach(controller -> verify(controller, times(1)).addListener(listener));
    }

    class XboxControllerHelperForTesting extends XboxControllerHelper {

        public XboxControllerHelperForTesting() {
            super(null);
        }

        @Override
        protected Stream<Controller> getControllers() {
            return mockControllers.stream();
        }

        @Override
        protected XboxControllerListener getXboxControllerListener() {
            return listener;
        }
    }
}
