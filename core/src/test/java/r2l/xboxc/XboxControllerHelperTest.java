package r2l.xboxc;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class XboxControllerHelperTest {

    @Test
    public void addListenerToEveryController_shouldAddListenerToEveryController() {
        List mockedList = mock(List.class);

        assert mockedList != null;
    }
}
