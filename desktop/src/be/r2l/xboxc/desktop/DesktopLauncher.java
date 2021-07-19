package be.r2l.xboxc.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import be.r2l.xboxc.XboxC;

public class DesktopLauncher {
	
	private static int HEIGHT = 391;
	private static int WIDTH = 640;
	private static FileType ICON_FILETYPE = FileType.Internal;
	private static String[] ICON_ARRAY = new String[] {
			"icons/360ControllerIcon128x128.png",
			"icons/360ControllerIcon32x32.png",
			"icons/360ControllerIcon16x16.png"};
	
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("XBoxC - R2L");
		config.setWindowedMode(WIDTH, HEIGHT);
		config.setResizable(false);
		config.setWindowIcon(ICON_FILETYPE, ICON_ARRAY);
		new Lwjgl3Application(new XboxC(), config);
	}
}
