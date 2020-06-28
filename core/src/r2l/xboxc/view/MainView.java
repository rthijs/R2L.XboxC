package r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import r2l.xboxc.XboxControllerObservable;

import java.util.ArrayList;
import java.util.List;

public class MainView extends ScreenAdapter {

    private static final Batch SPRITE_BATCH = new SpriteBatch();
    private static final List<ScreenAdapter> SCREEN_ADAPTERS = new ArrayList<>();
    private static final XboxControllerObserver XBOX_CONTROLLER_OBSERVER = XboxControllerObserver.getInstance();

    static {
        XboxControllerObservable.getInstance().addPropertyChangeListener(XBOX_CONTROLLER_OBSERVER);
        SCREEN_ADAPTERS.add(new BackGround(SPRITE_BATCH));
        SCREEN_ADAPTERS.add(new XboxControllerImage(SPRITE_BATCH));
        SCREEN_ADAPTERS.add(new XboxControllerOverlay(SPRITE_BATCH));
        SCREEN_ADAPTERS.add(new MarkerOverlay(SPRITE_BATCH));
    }

    @Override
    public void render(float delta) {
        SPRITE_BATCH.begin();
        SCREEN_ADAPTERS.forEach(screenAdapter -> screenAdapter.render(delta));
        SPRITE_BATCH.end();
    }

    @Override
    public void dispose() {
        SCREEN_ADAPTERS.forEach(ScreenAdapter::dispose);
        SPRITE_BATCH.dispose();
    }
}
