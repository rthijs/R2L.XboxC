package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackGround extends ScreenAdapter {

    private static Texture backGround = new Texture("bgNoise.png");
    private static TextureRegion textureRegion = new TextureRegion(backGround, 0, 0, Gdx.graphics.getWidth(), backGround.getHeight());

    public BackGround() {
        backGround.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public void render(Batch batch) {
        batch.draw(textureRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
    }
}
