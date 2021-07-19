package be.r2l.xboxc.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;

import be.r2l.xboxc.XboxControllerObservable;


public class MainView extends ScreenAdapter {

    private final Batch batch;
    private final List<ScreenAdapter> screenAdapters = new ArrayList<>();
    
    public MainView(XboxControllerObservable observable, Batch batch) {
        this.batch = batch;
        MainViewHelper helper = getMainViewHelper(batch, observable);
        screenAdapters.add(helper.getBackGround());
        screenAdapters.add(helper.getXboxControllerImage());
        screenAdapters.add(helper.getXboxControllerOverlay());
        screenAdapters.add(helper.getMarkerOverlay());
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
    
    protected MainViewHelper getMainViewHelper(Batch batch, XboxControllerObservable observable) {
        return new MainViewHelper(batch, observable);
    }
    
}
