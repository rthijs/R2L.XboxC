package be.r2l.xboxc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;

import be.r2l.xboxc.communication.SocketClient;

public class XboxControllerHelper {

    private final AtomicInteger controllerIndex = new AtomicInteger();
    private final XboxControllerObservable observable;
    private final SocketClient socketClient;

    public XboxControllerHelper(XboxControllerObservable observable, SocketClient socketClient) {
        this.observable = observable;
        this.socketClient = socketClient;
    }

    public void addListenerToEveryController() {
    	getControllers().forEach(this::addListenerToController);
    }

    private void addListenerToController(Controller controller) {
        controller.addListener(getXboxControllerListener());
    }

    protected List<Controller> getControllers() {
    	List<Controller> controllerList = new ArrayList<Controller>();
    	Controllers.getControllers().forEach(controller -> controllerList.add(controller));
    	return controllerList;
    }

    protected XboxControllerListener getXboxControllerListener() {
        return new XboxControllerListener(getControllerIndex(), observable, socketClient);
    }

    protected int getControllerIndex() {
        return controllerIndex.getAndIncrement();
    }
}
