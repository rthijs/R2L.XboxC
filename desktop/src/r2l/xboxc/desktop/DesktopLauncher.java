package r2l.xboxc.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import r2l.xboxc.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "R2L XBOXC";
		config.height = 391;
		config.width = 640;
		config.resizable = false;
		config.forceExit = false;
		config.addIcon("icons/360ControllerIcon128x128.png", Files.FileType.Internal);
		config.addIcon("icons/360ControllerIcon32x32.png", Files.FileType.Internal);
		config.addIcon("icons/360ControllerIcon16x16.png", Files.FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}
