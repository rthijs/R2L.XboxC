package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class XboxControllerHelper {
    static final AtomicInteger controllerIndex = new AtomicInteger();

    static final void addListenerToEveryController() {
        Arrays.stream(Controllers.getControllers().toArray())
                .forEach(XboxControllerHelper::addListenerToController);
    }

    private static void addListenerToController(Controller controller) {
        Gdx.app.log("adding listener", controller.toString());
        controller.addListener(new XboxControllerListener(controllerIndex.getAndIncrement()));
    }
}
