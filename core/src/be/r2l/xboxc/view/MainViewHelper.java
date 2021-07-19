package be.r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import be.r2l.xboxc.XboxControllerObservable;

public class MainViewHelper {

    private Batch batch;
    private XboxControllerObservable observable;
    private float width;
    private float height;
    private BackGround backGround;
    private XboxControllerImage xboxControllerImage;
    private XboxControllerOverlay xboxControllerOverlay;
    private MarkerOverlay markerOverlay;

    public MainViewHelper(Batch batch, XboxControllerObservable observable) {
        this.batch = batch;
        this.observable = observable;
        this.width = getWidth();
        this.height = getHeight();
        this.backGround = initializeBackground();
        this.xboxControllerImage = initializeXboxControllerImage();
        this.xboxControllerOverlay = initializeXboxControllerOverlay();
        this.markerOverlay = initializeMarkerOverlay();
    }

	public BackGround getBackGround() {
        return backGround;
    }

    public XboxControllerImage getXboxControllerImage() {
        return xboxControllerImage;
    }

    public XboxControllerOverlay getXboxControllerOverlay() {
        return xboxControllerOverlay;
    }

    public MarkerOverlay getMarkerOverlay() {
        return markerOverlay;
    }
    
    protected BackGround initializeBackground() {
    	return new BackGround(batch, width, height);
	}

	protected XboxControllerImage initializeXboxControllerImage() {
		return new XboxControllerImage(batch);
	}

	protected XboxControllerOverlay initializeXboxControllerOverlay() {
		return new XboxControllerOverlay(batch);
	}

	protected MarkerOverlay initializeMarkerOverlay() {
		return new MarkerOverlay(batch, observable);
	}

    protected float getWidth () {
        return Gdx.graphics.getWidth();
    }

    protected float getHeight () {
        return Gdx.graphics.getHeight();
    }
}
