package r2l.xboxc.view;

import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.awt.*;

public class MarkerOverlayHelper {

    public static boolean drawMarker(int controllerIndex, ControllerItem item) {
        return true;
    }

    public static Point getCalculatedCoordinates(int controllerIndex, ControllerItem item) {
        return HardwareCoordinates.getCoordinates(item);
    }
}
