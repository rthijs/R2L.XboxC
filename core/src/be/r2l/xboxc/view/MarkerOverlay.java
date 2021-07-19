package be.r2l.xboxc.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;

import be.r2l.xboxc.XboxControllerObservable;
import be.r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class MarkerOverlay extends ScreenAdapter {

    private final Batch batch;
    private final MarkerOverlayHelper helper;

    public MarkerOverlay(Batch batch, XboxControllerObservable observable) {
        this.batch = batch;
        this.helper = getMarkerOverlayHelper(observable);
    }

    protected MarkerOverlayHelper getMarkerOverlayHelper(XboxControllerObservable observable) {
        return new MarkerOverlayHelper(observable);
    }

    @Override
    public void render(float delta) {
        IntStream.range(0, Controllers.getControllers().size)
                .forEach(this::drawMarkersForController);
    }

    private void drawMarkersForController(int controllerIndex) {
        Arrays.stream(ControllerItem.values())
                .filter(controllerItem -> helper.markerShouldBeDrawn(controllerIndex, controllerItem))
                .forEach(controllerItem -> drawMarker(controllerIndex, helper.getCalculatedCoordinates(controllerIndex, controllerItem)));
    }

    private void drawMarker(int controllerIndex, Point coordinates) {
        batch.draw(Markers.controllerMarker(controllerIndex), coordinates.x, coordinates.y);
    }

    @Override
    public void dispose() {
    }
}
