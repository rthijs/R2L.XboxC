package r2l.xboxc.view;

import com.badlogic.gdx.Gdx;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HardwareCoordinates {

    private static final List<HardwareItem> HARDWARE_ITEM_LIST = new ArrayList<>();

    private static class HardwareItem {

        final ControllerItem hardwareItem;
        final Point coordinate;

        public HardwareItem(ControllerItem hardwareItem, Point coordinate) {
            this.hardwareItem = hardwareItem;
            this.coordinate = coordinate;
        }
    }

    static {
        HARDWARE_ITEM_LIST.add(new HardwareItem(ControllerItem.A, createCoordinates(518,240)));
        HARDWARE_ITEM_LIST.add(new HardwareItem(ControllerItem.B, createCoordinates(573,195)));
        HARDWARE_ITEM_LIST.add(new HardwareItem(ControllerItem.X, createCoordinates(466,200)));
        HARDWARE_ITEM_LIST.add(new HardwareItem(ControllerItem.Y, createCoordinates(523,157)));
    }

    private static Point createCoordinates(int x, int y) {
        /* The Gimp gives coordinates with (0,0) in the upper left corner
           LibGDX draws the screen with coordinates origin in the lower left corner.
           Therefore I'm translating the coordinates here.  */
        int windowHeight = Gdx.graphics.getHeight();
        int markerOffset = Markers.controllerMarker(0).getRegionHeight() / 2;

        return new Point(x - markerOffset, windowHeight - y - markerOffset);
    }

    public static Point getCoordinates(ControllerItem hardware) {
        return HARDWARE_ITEM_LIST.stream()
                .filter(item -> item.hardwareItem.equals(hardware))
                .findFirst()
                .get()
                .coordinate;
    }

    public static List<ControllerItem> getHardwareItems() {
        return HARDWARE_ITEM_LIST.stream()
                .map(hardwareItem -> hardwareItem.hardwareItem)
                .collect(Collectors.toList());
    }
}
