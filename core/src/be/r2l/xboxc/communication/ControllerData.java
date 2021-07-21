package be.r2l.xboxc.communication;

import be.r2l.xboxc.hardwareAbstraction.ControllerItem;

public class ControllerData {
	
	int controllerIndex;
	ControllerItem item;
	float value;
	
	public ControllerData(int controllerIndex, ControllerItem item, float value) {
		this.controllerIndex = controllerIndex;
		this.item = item;
		this.value = value;
	}

	public int getControllerIndex() {
		return controllerIndex;
	}

	public void setControllerIndex(int controllerIndex) {
		this.controllerIndex = controllerIndex;
	}

	public ControllerItem getItem() {
		return item;
	}

	public void setItem(ControllerItem item) {
		this.item = item;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
