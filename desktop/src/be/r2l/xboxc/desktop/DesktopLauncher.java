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
		
		String ip_address = "127.0.0.1";
		int port = 8099;
		
		if (arg.length > 0) {
			ip_address = arg[0];
			System.out.println("Using ip address: " + ip_address);
		} else {
			System.out.println("No ip address specified, using default: " + ip_address);
		}
		
		if (arg.length == 2) {
			port = Integer.valueOf(arg[1]);
			System.out.println("Using port: " + port);
		} else {
			System.out.println("No port specified, using default: " + port);
		}
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("XBoxC - R2L");
		config.setWindowedMode(WIDTH, HEIGHT);
		config.setResizable(false);
		config.setWindowIcon(ICON_FILETYPE, ICON_ARRAY);
		new Lwjgl3Application(new XboxC(ip_address, port), config);
	}
}
