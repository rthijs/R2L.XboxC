package r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import org.junit.Before;
import org.junit.Test;
import r2l.xboxc.XboxControllerObservable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainViewTest {

    private static float WIDTH = 640;
    private static float HEIGTH = 480;

    private MainViewHelper mockHelper;
    private BackGround mockBackGround;
    private XboxControllerImage mockXboxControllerImage;
    private XboxControllerOverlay mockXboxControllerOverlay;
    private MarkerOverlay mockMarkerOverlay;

    @Before
    public void setup() {
        mockBackGround = mock(BackGround.class);
        mockXboxControllerImage = mock(XboxControllerImage.class);
        mockXboxControllerOverlay = mock(XboxControllerOverlay.class);
        mockMarkerOverlay = mock(MarkerOverlay.class);
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
        MainView mainView = new
    }

    private class MainViewForTesting extends MainView {
        public MainView(XboxControllerObservable observable)
    }
}
