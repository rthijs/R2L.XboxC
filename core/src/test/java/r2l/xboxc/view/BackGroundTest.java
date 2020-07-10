package r2l.xboxc.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BackGroundTest {

    private static final float REGION_WIDTH = 640;
    private static final float REGION_HEIGHT = 480;

    private Batch mockBatch;
    private Texture mockBackGround;
    private TextureRegion mockTextureRegion;
    private BackGround backGround;

    @Before
    public void setup() {
        mockBatch = mock(Batch.class);
        mockBackGround = mock(Texture.class);
        mockTextureRegion = mock(TextureRegion.class);
        backGround = new BackGroundForTesting();
    }

    @Test
    public void render_shouldDrawTextureRegion() {
        backGround.render(.123F);
        verify(mockBatch, times(1)).draw(eq(mockTextureRegion), eq(0F), eq(0F), eq(REGION_WIDTH), eq(REGION_HEIGHT));
    }

    @Test
    public void dispose_shouldDisposeBackGround() {
        backGround.dispose();
        verify(mockBackGround, times(1)).dispose();
    }

    private class BackGroundForTesting extends BackGround {

        BackGroundForTesting() {
            super(mockBatch, REGION_WIDTH, REGION_HEIGHT);
        }

        @Override
        protected Texture getRepeatingTexture() {
            return mockBackGround;
        }

        protected TextureRegion getTextureRegion() {
            return mockTextureRegion;
        }
    }
}
