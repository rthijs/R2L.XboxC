package r2l.xboxc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import r2l.xboxc.view.MainView;

public class Main extends ApplicationAdapter {

	private MainView mainView;

	@Override
	public void create() {
		mainView = new MainView();
		XboxControllerHelper.addListenerToEveryController();
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