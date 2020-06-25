package r2l.xboxc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import view.BackGround;
import view.MainView;

public class XboxControllerMain extends ApplicationAdapter {

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