package r2l.xboxc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class XboxControllerHelper {

    private final AtomicInteger controllerIndex = new AtomicInteger();
    private final XboxControllerObservable observable;

    public XboxControllerHelper(XboxControllerObservable observable) {
        this.observable = observable;
    }

    public void addListenerToEveryController() {
        getControllers().forEach(controller -> addListenerToController(controller));
    }

    private void addListenerToController(Controller controller) {
        controller.addListener(getXboxControllerListener());
    }

    protected Stream<Controller> getControllers() {
        return Arrays.stream(Controllers.getControllers().toArray());
    }

    protected XboxControllerListener getXboxControllerListener() {
        return new XboxControllerListener(controllerIndex.getAndIncrement(), observable);
    }
}
