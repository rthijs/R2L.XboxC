package test.be.r2l.xboxc;

import com.badlogic.gdx.controllers.Controller;
import be.r2l.xboxc.XboxControllerHelper;
import be.r2l.xboxc.XboxControllerListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
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
        mockControllers = generateMockControllers();
    }

    @Test
    public void addListenerToEveryController_shouldAddListenerToEveryController() {
        xboxControllerHelper.addListenerToEveryController();
        mockControllers.forEach(controller -> verify(controller, times(1)).addListener(listener));
    }
    
    private List<Controller> generateMockControllers() {
    	List<Controller> controllerList = new ArrayList<Controller>();
    	for (int i = 0; i < NUMBER_OF_CONTROLLERS; i++) {
    		controllerList.add(mock(Controller.class));
    	}
    	return controllerList;
    	
    }

    class XboxControllerHelperForTesting extends XboxControllerHelper {

        public XboxControllerHelperForTesting() {
            super(null);
        }

        @Override
        protected List<Controller> getControllers() {
        	return mockControllers;
        }

        @Override
        protected XboxControllerListener getXboxControllerListener() {
            return listener;
        }
    }
}
