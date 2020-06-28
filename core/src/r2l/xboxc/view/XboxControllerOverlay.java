package r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class XboxControllerOverlay extends ScreenAdapter {
    private static final Texture xBox360ControllerOverlay = new Texture("360ControllerOverlay.png");
    private static Batch batch;

    public XboxControllerOverlay(Batch batch) {
        XboxControllerOverlay.batch = batch;
    }

    @Override
    public void render(float delta) {
        batch.draw(xBox360ControllerOverlay,0,0);
    }

    @Override
    public void dispose() {
        xBox360ControllerOverlay.dispose();
    }
}
