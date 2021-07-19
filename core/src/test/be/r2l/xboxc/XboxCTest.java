package test.be.r2l.xboxc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import be.r2l.xboxc.XboxC;
import be.r2l.xboxc.XboxControllerHelper;
import be.r2l.xboxc.XboxControllerObservable;
import be.r2l.xboxc.view.MainView;

public class XboxCTest {

	private static final float DELTA_TIME = .666f;

	private MainView mockMainView;
	private XboxControllerObservable mockObservable;
	private XboxControllerHelper mockHelper;
	private XboxC xboxCToTest;

	@Before
	public void setup() {
		mockMainView = mock(MainView.class);
		doNothing().when(mockMainView).render(DELTA_TIME);
		mockObservable = mock(XboxControllerObservable.class);
		mockHelper = mock(XboxControllerHelper.class);
		doNothing().when(mockHelper).addListenerToEveryController();
		xboxCToTest = new TestXboxC();
	}

	@Test
	public void createShouldAddListenersToControllers() {
		xboxCToTest.create();
		verify(mockHelper, times(1)).addListenerToEveryController();
	}

	@Test
	public void renderShouldRenderMainView() {
		xboxCToTest.create();
		xboxCToTest.render();
		verify(mockMainView, times(1)).render(DELTA_TIME);
	}

	@Test
	public void disposeShouldDisposeMainView() {
		xboxCToTest.create();
		xboxCToTest.dispose();
		verify(mockMainView, times(1)).dispose();
	}

	private class TestXboxC extends XboxC {
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
