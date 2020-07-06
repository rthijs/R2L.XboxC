package r2l.xboxc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import r2l.xboxc.view.MainView;

public class Main extends ApplicationAdapter {

	private MainView mainView;
	private XboxControllerHelper helper;
	private XboxControllerObservable observable;

	@Override
	public void create() {
		mainView = getMainView();
		helper = getXboxControllerHelper();
		helper.addListenerToEveryController();
		observable = getXboxControllerObservable();
	}

	protected MainView getMainView() {
		return new MainView(observable);
	}

	protected  XboxControllerHelper getXboxControllerHelper() {
		return new XboxControllerHelper(observable);
	}

	protected XboxControllerObservable getXboxControllerObservable() {
		return new XboxControllerObservable();
	}

	@Override
	public void render() {
		mainView.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		mainView.dispose();
	}


}