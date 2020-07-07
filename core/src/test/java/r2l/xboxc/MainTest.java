package r2l.xboxc;

import org.junit.Before;
import org.junit.Test;
import r2l.xboxc.view.MainView;

import static org.mockito.Mockito.*;

public class MainTest {

    private static final float DELTA_TIME = .666f;

    private MainView mockMainView;
    private XboxControllerObservable mockObservable;
    private XboxControllerHelper mockHelper;
    private Main mainToTest;

    @Before
    public void setup() {
        mockMainView = mock(MainView.class);
        doNothing().when(mockMainView).render(DELTA_TIME);
        mockObservable = mock(XboxControllerObservable.class);
        mockHelper = mock(XboxControllerHelper.class);
        doNothing().when(mockHelper).addListenerToEveryController();
        mainToTest = new TestMain();
    }

    @Test
    public void createShouldAddListenersToControllers() {
        mainToTest.create();
        verify(mockHelper, times(1)).addListenerToEveryController();
    }

    @Test
    public void renderShouldRenderMainView() {
        mainToTest.create();
        mainToTest.render();
        verify(mockMainView, times(1)).render(DELTA_TIME);
    }

    @Test
    public void disposeShouldDisposeMainView() {
        mainToTest.create();
        mainToTest.dispose();
        verify(mockMainView, times(1)).dispose();
    }


    private class TestMain extends Main {
        @Override
        protected MainView getMainView() {
            return mockMainView;
        }

        @Override
        protected XboxControllerHelper getXboxControllerHelper() {
            return mockHelper;
        }

        @Override
        protected XboxControllerObservable getXboxControllerObservable() {
            return mockObservable;
        }

        @Override
        protected float getDeltaTime() {
            return DELTA_TIME;
        }
    }
}
