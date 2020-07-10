package r2l.xboxc;

import org.junit.Before;
import org.junit.Test;
import r2l.xboxc.hardwareAbstraction.ControllerItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class XboxControllerObservableTest {

    private final static int NUMBER_OF_CONTROLLERS = 13;
    private final static int CONTROLLER_INDEX = 7;

    private PropertyChangeSupport mockSupport;

    private XboxControllerObservable xboxControllerObservable;

    @Before
    public void setup() {
        mockSupport = mock(PropertyChangeSupport.class);
        xboxControllerObservable = new XboxControllerObservableForTesting();
    }

    @Test
    public void addPropertyChangeListener_ShouldAddPropertyChangeListenerToPropertyChangeSupport() {
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        xboxControllerObservable.addPropertyChangeListener(mockListener);
        verify(mockSupport, times(1)).addPropertyChangeListener(mockListener);
    }

    @Test
    public void setControllerItemValue_shouldCallPropertyChangeSupportFireIndexedPropertyChange() {
        ControllerItem item = ControllerItem.BUTTON_A;
        Float newValue = .123F;
        Float oldValue = 0F;
        xboxControllerObservable.setControllerItemValue(CONTROLLER_INDEX, item, newValue);
        verify(mockSupport, times(1)).fireIndexedPropertyChange(item.name(), CONTROLLER_INDEX, oldValue, newValue);
    }

    @Test
    public void getControllerItemValue_shouldReturnZeroWhenNotYetUpdated() {
        ControllerItem item = ControllerItem.BUTTON_A;
        Float actual = xboxControllerObservable.getControllerItemValue(CONTROLLER_INDEX, item);
        assertThat("Not yet updated item should return 0", actual, is(0F));
    }

    @Test
    public void getControllerItemValue_shouldReturnUpdatedValue() {
        ControllerItem item = ControllerItem.BUTTON_A;
        Float expected = .123F;
        xboxControllerObservable.setControllerItemValue(CONTROLLER_INDEX, item, expected);
        Float actual = xboxControllerObservable.getControllerItemValue(CONTROLLER_INDEX, item);
        assertThat("getControllerItemValue should return updated value", actual, is(expected));
    }

    private class XboxControllerObservableForTesting extends XboxControllerObservable {

        @Override
        protected PropertyChangeSupport getSupport() {
            return mockSupport;
        }

        @Override
        protected int numberOfControllers() {
            return NUMBER_OF_CONTROLLERS;
        }
    }
}
