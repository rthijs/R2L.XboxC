package r2l.xboxc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import r2l.xboxc.view.MainView;

public class Main extends ApplicationAdapter {

    private MainView mainView;
    private XboxControllerObservable observable;

    @Override
    public void create() {
        observable = getXboxControllerObservable();
        mainView = getMainView();
        getXboxControllerHelper().addListenerToEveryController();
    }

    protected MainView getMainView() {
        return new MainView(observable);
    }

    protected XboxControllerHelper getXboxControllerHelper() {
        return new XboxControllerHelper(observable);
    }

    protected XboxControllerObservable getXboxControllerObservable() {
        return new XboxControllerObservable();
    }

    @Override
    public void render() {
        mainView.render(getDeltaTime());
    }

    @Override
    public void dispose() {
        mainView.dispose();
    }

    protected float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }


}