package r2l.xboxc;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class XboxControllerHelper {
    private static final AtomicInteger controllerIndex = new AtomicInteger();

    public static void addListenerToEveryController() {
        Arrays.stream(Controllers.getControllers().toArray())
                .forEach(XboxControllerHelper::addListenerToController);
    }

    private static void addListenerToController(Controller controller) {
        controller.addListener(new XboxControllerListener(controllerIndex.getAndIncrement()));
    }
}
