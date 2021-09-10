package be.r2l.xboxc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import be.r2l.xboxc.communication.SocketClient;
import be.r2l.xboxc.view.MainView;


public class XboxC extends ApplicationAdapter {
	
    private MainView mainView;
    private XboxControllerObservable observable;
    private String ip_address;
    private int port;
    
    public XboxC(String ip_address, int port) {
    	this.ip_address = ip_address;
    	this.port = port;
    }
	
    @Override
    public void create() {
        observable = getXboxControllerObservable();
        mainView = getMainView();
        SocketClient.getInstance(ip_address, port).startConnection();
        getXboxControllerHelper().addListenerToEveryController();
    }

    protected MainView getMainView() {
        return new MainView(observable, new SpriteBatch());
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
        SocketClient.getInstance(ip_address, port).stopConnection();
    }

    protected float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
}
