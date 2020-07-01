package r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

import static r2l.xboxc.view.MarkerOverlayHelper.markerShouldBeDrawn;

public class MarkerOverlay extends ScreenAdapter {

    private static Batch batch;

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

    private void drawMarker(int controllerIndex, Point coordinates) {
        batch.draw(Markers.controllerMarker(controllerIndex), coordinates.x, coordinates.y);
    }

    @Override
    public void dispose() {
    }
}
