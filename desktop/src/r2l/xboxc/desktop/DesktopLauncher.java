package r2l.xboxc.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import r2l.xboxc.XboxControllerMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Roel's 360 Controller Socket Server";
		config.height = 391;
		config.width = 640;
		config.resizable = false;
		config.forceExit = false;
		new LwjglApplication(new XboxControllerMain(), config);
	}
}
