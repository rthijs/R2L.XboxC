package be.r2l.xboxc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import be.r2l.xboxc.communication.SocketClient;
import be.r2l.xboxc.view.MainView;


public class XboxC extends ApplicationAdapter {
	
    private MainView mainView;
    private XboxControllerObservable observable;
    private SocketClient socketClient;
	
    @Override
    public void create() {
        observable = getXboxControllerObservable();
        mainView = getMainView();
        socketClient = new SocketClient();
        socketClient.startConnection("127.0.0.1", 8099);
        getXboxControllerHelper().addListenerToEveryController();
    }

    protected MainView getMainView() {
        return new MainView(observable, new SpriteBatch());
    }

    protected XboxControllerHelper getXboxControllerHelper() {
        return new XboxControllerHelper(observable, socketClient);
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
