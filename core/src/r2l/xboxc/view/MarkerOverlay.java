package r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;
import r2l.xboxc.XboxControllerObservable;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

import static r2l.xboxc.view.MarkerOverlayHelper.markerShouldBeDrawn;

public class MarkerOverlay extends ScreenAdapter {

    private static Batch batch;
    private static final XboxControllerObservable xBoxControllerObservable = XboxControllerObservable.getInstance();

    public MarkerOverlay(Batch batch) {
        XboxControllerObserver.getInstance();
        MarkerOverlay.batch = batch;
    }

    @Override
    public void render(float delta) {
        IntStream.range(0, Controllers.getControllers().size)
                .forEach(this::drawMarkersForController);
    }

    private void drawMarkersForController(int controllerIndex) {
        Arrays.stream(ControllerItem.values())
                .filter(controllerItem -> markerShouldBeDrawn(controllerIndex, controllerItem))
                .forEach(controllerItem -> drawMarker(controllerIndex, MarkerOverlayHelper.getCalculatedCoordinates(controllerIndex, controllerItem)));
    }

    private boolean dontDrawTwoMarkersForASingleStickFilter() {
        /*
                horizontal axis     vertical axis       draw marker for vertical    draw marker for horizontal
                    0                   0                       0                           0
                    0                   1                       1                           0
                    1                   0                       0                           1
                    1                   1                       0                           1

                    draw vertical = !horizontal + vertical
                    draw horizontal = horizontal
         */
        return true;
    }

    private boolean isJoystickAndShouldDrawMarkerForVerticalAxis(int controllerIndex, ControllerItem controllerItem) {
        return true;
    }

    private boolean isJoystickAndShouldDrawMarkerForHorizontalAxis(int controllerIndex, ControllerItem controllerItem) {
        return true;
    }

    private void drawMarker(int controllerIndex, Point coordinates) {
        batch.draw(Markers.controllerMarker(controllerIndex), coordinates.x, coordinates.y);
    }

    @Override
    public void dispose() {
    }
}
