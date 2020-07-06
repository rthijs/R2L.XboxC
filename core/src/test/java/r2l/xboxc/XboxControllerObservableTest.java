package r2l.xboxc;

import org.junit.Test;

public class XboxControllerObservableTest {

    private final int NUMBER_OF_CONTROLLERS = 123;

    @Test
    public void testTest() {
        XboxControllerObservable xboxControllerObservable = new XboxControllerObservableForTesting();


        assert  xboxControllerObservable != null;

    }


    class XboxControllerObservableForTesting extends XboxControllerObservable {

        @Override
        protected int numberOfControllers() {
            return NUMBER_OF_CONTROLLERS;
        }
    }

}
