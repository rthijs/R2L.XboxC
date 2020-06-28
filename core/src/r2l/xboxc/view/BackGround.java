package r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackGround extends ScreenAdapter {

    private static final Texture backGround = new Texture("bgNoise.png");
    private static final TextureRegion textureRegion = new TextureRegion(backGround, 0, 0, Gdx.graphics.getWidth(), backGround.getHeight());
    private static Batch batch;

    static {
        backGround.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public BackGround(Batch batch) {
        BackGround.batch = batch;
    }

    @Override
    public void render(float delta) {
        batch.draw(textureRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
        backGround.dispose();
    }
}
