package view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainView implements Screen {

    private static Batch batch = new SpriteBatch();
    private static BackGround backGround = new BackGround();
    private static Texture xBox360ControllerImage = new Texture("360Controller.png");

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        backGround.render(batch);
        batch.draw(xBox360ControllerImage,0,0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backGround.dispose();
        xBox360ControllerImage.dispose();
        batch.dispose();
    }
}
