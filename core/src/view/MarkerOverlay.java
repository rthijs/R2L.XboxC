package view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static view.HardwareCoordinates.getCoordinates;
import static view.HardwareCoordinates.getHardwareItems;

public class MarkerOverlay extends ScreenAdapter {
    private static Batch batch;

    public MarkerOverlay(Batch batch) {
        MarkerOverlay.batch = batch;
    }

    @Override
    public void render(float delta) {
        getHardwareItems().forEach(this::drawMarkers);
    }

    private void drawMarkers(ControllerItem item) {
        IntStream.range(0, Controllers.getControllers().size)
                .mapToObj(Markers::controllerMarker)
                .collect(Collectors.toList())
                .forEach(marker ->batch.draw(marker, getCoordinates(item).x, getCoordinates(item).y));
    }

    @Override
    public void dispose() {
    }
}
