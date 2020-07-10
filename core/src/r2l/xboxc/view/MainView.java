package r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import r2l.xboxc.XboxControllerObservable;

import java.util.ArrayList;
import java.util.List;

public class MainView extends ScreenAdapter {

    private final Batch batch;
    private final List<ScreenAdapter> screenAdapters = new ArrayList<>();

    public MainView(XboxControllerObservable observable) {
        this.batch = getSpriteBatch();
        screenAdapters.add(new BackGround(batch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        screenAdapters.add(new XboxControllerImage(batch));
        screenAdapters.add(new XboxControllerOverlay(batch));
        screenAdapters.add(new MarkerOverlay(batch, observable));
    }

    protected SpriteBatch getSpriteBatch() {
        return new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        screenAdapters.forEach(screenAdapter -> screenAdapter.render(delta));
        batch.end();
    }

    @Override
    public void dispose() {
        screenAdapters.forEach(ScreenAdapter::dispose);
        batch.dispose();
    }
}
