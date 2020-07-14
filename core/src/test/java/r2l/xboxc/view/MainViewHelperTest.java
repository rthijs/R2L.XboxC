package r2l.xboxc.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Before;
import org.junit.Test;
import r2l.xboxc.XboxControllerObservable;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;

public class MainViewHelperTest {

    private static final float WIDTH = 640;
    private static final float HEIGHT = 480;

    private Batch mockBatch;
    private XboxControllerObservable mockXboxControllerObservable;
    private MainViewHelper mainViewHelper;

    @Before
    public void setup() {
        mockBatch = mock(SpriteBatch.class);
        mockXboxControllerObservable = mock(XboxControllerObservable.class);
        mainViewHelper = new MainViewHelperForTesting(mockBatch, mockXboxControllerObservable);
    }

    @Test
    public void getBackGround_shouldReturnBackGround() {
        BackGround backGround = mainViewHelper.getBackGround();
        int backGroundWidth = backGround.getTextureRegion().getRegionWidth();
        int backGroundHeight = backGround.getTextureRegion().getRegionHeight();
        assertThat(backGround, instanceOf(BackGround.class));
        assertEquals(backGroundWidth, WIDTH);
        assertEquals(backGroundHeight, HEIGHT);
    }

    @Test
    public void getXboxControllerImage_shouldReturnXboxControllerImage() {
        //XboxControllerImage xboxControllerImage = mainViewHelper.getXboxControllerImage();
    }

    private class MainViewHelperForTesting extends MainViewHelper {

        public MainViewHelperForTesting(Batch batch, XboxControllerObservable observable) {
            super(batch, observable);
        }

        @Override
        protected float getWidth() {
            return WIDTH;
        }

        @Override
        protected float getHeight() {
            return HEIGHT;
        }
    }
}
