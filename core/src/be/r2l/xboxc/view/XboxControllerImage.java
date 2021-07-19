package be.r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class XboxControllerImage extends ScreenAdapter {

    private final Texture xBox360ControllerImage = new Texture("360Controller.png");
    private Batch batch;

    public XboxControllerImage(Batch batch) {
        this.batch = batch;
    }

    @Override
    public void render(float delta) {
        batch.draw(xBox360ControllerImage,0,0);
    }

    @Override
    public void dispose() {
        xBox360ControllerImage.dispose();
    }
}
