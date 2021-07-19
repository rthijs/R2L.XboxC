package be.r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import be.r2l.xboxc.XboxControllerObservable;

public class MainViewHelper {

    private Batch batch;
    private XboxControllerObservable observable;
    private float width;
    private float height;

    public MainViewHelper(Batch batch, XboxControllerObservable observable) {
        this.batch = batch;
        this.observable = observable;
        this.width = getWidth();
        this.height = getHeight();
    }

    public BackGround getBackGround() {
        return new BackGround(batch, width, height);
    }

    public XboxControllerImage getXboxControllerImage() {
        return new XboxControllerImage(batch);
    }

    public XboxControllerOverlay getXboxControllerOverlay() {
        return new XboxControllerOverlay(batch);
    }

    public MarkerOverlay getMarkerOverlay() {
        return new MarkerOverlay(batch, observable);
    }

    protected float getWidth () {
        return Gdx.graphics.getWidth();
    }

    protected float getHeight () {
        return Gdx.graphics.getHeight();
    }
}
