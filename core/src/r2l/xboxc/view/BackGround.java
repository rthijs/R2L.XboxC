package r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackGround extends ScreenAdapter {

    private static final String TEXTURE_PATH = "bgNoise.png";

    private final float width;
    private final float height;
    private final Batch batch;
    private final Texture backGround;
    private final TextureRegion textureRegion;

    public BackGround(Batch batch, float regionWidth, float regionHeight) {
        this.width = regionWidth;
        this.height = regionHeight;
        this.batch = batch;
        this.backGround = getRepeatingTexture();
        this.textureRegion = getTextureRegion();
    }

    @Override
    public void render(float delta) {
        batch.draw(textureRegion, 0, 0, width, height);
    }

    @Override
    public void dispose() {
        backGround.dispose();
    }

    protected Texture getRepeatingTexture() {
        Texture texture = new Texture(TEXTURE_PATH);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        return texture;
    }

    protected TextureRegion getTextureRegion() {
        return new TextureRegion(backGround, 0, 0, width, height);
    }
}
