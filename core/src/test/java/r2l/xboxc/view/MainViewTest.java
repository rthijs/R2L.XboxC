package r2l.xboxc.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Before;
import org.junit.Test;
import r2l.xboxc.XboxControllerObservable;

import static org.mockito.Mockito.*;

public class MainViewTest {

    private static float WIDTH = 640;
    private static float HEIGTH = 480;
    private static float DELTA_TIME = .333F;

    private MainViewHelper mockHelper;
    private BackGround mockBackGround;
    private XboxControllerImage mockXboxControllerImage;
    private XboxControllerOverlay mockXboxControllerOverlay;
    private MarkerOverlay mockMarkerOverlay;
    private Batch mockBatch;
    private XboxControllerObservable mockXboxControllerObservable;

    @Before
    public void setup() {
        mockBackGround = mock(BackGround.class);
        mockXboxControllerImage = mock(XboxControllerImage.class);
        mockXboxControllerOverlay = mock(XboxControllerOverlay.class);
        mockMarkerOverlay = mock(MarkerOverlay.class);
        mockBatch = mock(SpriteBatch.class);
        mockXboxControllerObservable = mock(XboxControllerObservable.class);
        mockHelper = mock(MainViewHelper.class);
        when(mockHelper.getBackGround()).thenReturn(mockBackGround);
        when(mockHelper.getXboxControllerImage()).thenReturn(mockXboxControllerImage);
        when(mockHelper.getXboxControllerOverlay()).thenReturn(mockXboxControllerOverlay);
        when(mockHelper.getMarkerOverlay()).thenReturn(mockMarkerOverlay);
        when(mockHelper.getWidth()).thenReturn(WIDTH);
        when(mockHelper.getHeight()).thenReturn(HEIGTH);
    }

    @Test
    public void render_shouldRenderAllScreenAdapters() {
        MainView mainView = new MainViewForTesting();
        mainView.render(DELTA_TIME);
        verify(mockBackGround, times(1)).render(DELTA_TIME);
        verify(mockXboxControllerImage, times(1)).render(DELTA_TIME);
        verify(mockXboxControllerOverlay, times(1)).render(DELTA_TIME);
        verify(mockMarkerOverlay, times(1)).render(DELTA_TIME);
    }

    @Test
    public void dispose_shouldDisposeAllDisposables() {
        MainView mainView = new MainViewForTesting();
        mainView.dispose();
        verify(mockBackGround, times(1)).dispose();
        verify(mockXboxControllerImage, times(1)).dispose();
        verify(mockXboxControllerOverlay, times(1)).dispose();
        verify(mockMarkerOverlay, times(1)).dispose();
        verify(mockBatch, times(1)).dispose();
    }

    private class MainViewForTesting extends MainView {
        public MainViewForTesting(){
            super(mockXboxControllerObservable, mockBatch);
        }

        @Override
        protected MainViewHelper getMainViewHelper(Batch batch, XboxControllerObservable observable) {
            return mockHelper;
        }
    }
}
