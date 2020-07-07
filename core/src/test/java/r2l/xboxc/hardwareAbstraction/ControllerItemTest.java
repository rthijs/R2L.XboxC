package r2l.xboxc.hardwareAbstraction;

import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControllerItemTest {

    private static final int INVALID_CODE = 123456789;

    @Test
    public void requestingControllerItemForUnknownLabelShouldReturnUNKNOWN() {
        ControllerItem item = ControllerItem.valueOfLabel("INVALID_LABEL");
        assertThat("Invalid label should return UNKNOWN ControllerItem.", item, is(ControllerItem.UNKNOWN));
    }

    @Test
    public void getButtons_shouldOnlyReturnButtons() {
        List<ControllerItem> nonButtonItems = ControllerItem.getButtons().stream().filter(item -> !item.name().contains("BUTTON")).collect(Collectors.toList());
        assertThat("getButtons should only return ControllerItems that are buttons.", nonButtonItems.size(), is(0));
    }

    @Test
    public void getAxes_shouldOnlyReturnAxes() {
        List<ControllerItem> nonAxeItems = ControllerItem.getAxes().stream().filter(item -> !item.name().contains("AXIS")).collect(Collectors.toList());
        assertThat("getAxes should only return ControllerItems that are axes.", nonAxeItems.size(), is(0));
    }

    @Test
    public void getDPadControllerItems_shouldOnlyReturnAxes() {
        List<ControllerItem> nonDPadItems = ControllerItem.getDPadControllerItems().stream().filter(item -> !item.name().contains("DPAD")).collect(Collectors.toList());
        assertThat("getDPadControllerItems should only return ControllerItems that are DPad buttons.", nonDPadItems.size(), is(0));
    }

    @Test
    public void getButtonForCode_shouldReturnUNKNOWNForInvalidCode() {
        ControllerItem item = ControllerItem.getButtonForCode(INVALID_CODE);
        assertThat("getButtonForCode should return UNKNOWN for an invalid code.", item, is(ControllerItem.UNKNOWN));
    }

    @Test
    public void getAxisForCode_shouldReturnUNKNOWNForInvalidCode() {
        ControllerItem item = ControllerItem.getAxisForCode(INVALID_CODE);
        assertThat("getAxisForCode should return UNKNOWN for an invalid code.", item, is(ControllerItem.UNKNOWN));
    }

    @Test
    public void getDpadDirectionForCode_shouldReturnUNKNOWNForInvalidCode() {
        ControllerItem item = ControllerItem.getDpadDirectionForCode(INVALID_CODE);
        assertThat("getButtonForCode should return UNKNOWN for an invalid code.", item, is(ControllerItem.UNKNOWN));
    }

    @Test
    public void getButtonForCode_shouldReturnControllerItemForValidButtonCode() {
        ControllerItem item = ControllerItem.getButtonForCode(Xbox.A);
        assertThat("getButtonForCode should return BUTTON_A for a Button A code.", item, is(ControllerItem.BUTTON_A));
    }

    @Test
    public void getAxisForCode_shouldReturnControllerItemForValidAxisCode() {
        ControllerItem item = ControllerItem.getAxisForCode(Xbox.L_STICK_HORIZONTAL_AXIS);
        assertThat("getAxisForCode should return AXIS_L_STICK_HORIZONTAL_AXIS for an L_STICK_HORIZONTAL_AXIS code.", item, is(ControllerItem.AXIS_L_STICK_HORIZONTAL_AXIS));
    }

    @Test
    public void getDpadDirectionForCode_shouldReturnControllerItemForValidDPadCode() {
        ControllerItem item = ControllerItem.getDpadDirectionForCode(PovDirection.south.ordinal());
        assertThat("getButtonForCode should return DPAD_SOUTH for DPAD_DOWN code.", item, is(ControllerItem.DPAD_SOUTH));
    }
}
