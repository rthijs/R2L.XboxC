package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class XboxControllerHelper {
    private static final AtomicInteger CONTROLLER_INDEX = new AtomicInteger();

    public static void addListenerToEveryController() {
        Arrays.stream(Controllers.getControllers().toArray())
                .forEach(XboxControllerHelper::addListenerToController);
    }

    private static void addListenerToController(Controller controller) {
        Gdx.app.log("Listener added to controller " + CONTROLLER_INDEX.get() , controller.getName());
        controller.addListener(new XboxControllerListener(CONTROLLER_INDEX.getAndIncrement()));
    }
}
