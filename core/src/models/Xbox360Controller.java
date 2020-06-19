package models;

public class Xbox360Controller {
    private Trigger leftTrigger;
    private Trigger rightTrigger;
    private Boolean leftBumper;
    private Boolean rightBumper;
    private Boolean buttonA;
    private Boolean buttonB;
    private Boolean buttonX;
    private Boolean buttonY;
    private Boolean buttonBack;
    private Boolean buttonGuide;
    private Boolean buttonStart;
    private AnalogStick leftStick;
    private AnalogStick rightStick;
    private DirectionalPad dPad;

    public Trigger getLeftTrigger() {
        return leftTrigger;
    }

    public void setLeftTrigger(Trigger leftTrigger) {
        this.leftTrigger = leftTrigger;
    }

    public Trigger getRightTrigger() {
        return rightTrigger;
    }

    public void setRightTrigger(Trigger rightTrigger) {
        this.rightTrigger = rightTrigger;
    }

    public Boolean getLeftBumper() {
        return leftBumper;
    }

    public void setLeftBumper(Boolean leftBumper) {
        this.leftBumper = leftBumper;
    }

    public Boolean getRightBumper() {
        return rightBumper;
    }

    public void setRightBumper(Boolean rightBumper) {
        this.rightBumper = rightBumper;
    }

    public Boolean getButtonA() {
        return buttonA;
    }

    public void setButtonA(Boolean buttonA) {
        this.buttonA = buttonA;
    }

    public Boolean getButtonB() {
        return buttonB;
    }

    public void setButtonB(Boolean buttonB) {
        this.buttonB = buttonB;
    }

    public Boolean getButtonX() {
        return buttonX;
    }

    public void setButtonX(Boolean buttonX) {
        this.buttonX = buttonX;
    }

    public Boolean getButtonY() {
        return buttonY;
    }

    public void setButtonY(Boolean buttonY) {
        this.buttonY = buttonY;
    }

    public Boolean getButtonBack() {
        return buttonBack;
    }

    public void setButtonBack(Boolean buttonBack) {
        this.buttonBack = buttonBack;
    }

    public Boolean getButtonGuide() {
        return buttonGuide;
    }

    public void setButtonGuide(Boolean buttonGuide) {
        this.buttonGuide = buttonGuide;
    }

    public Boolean getButtonStart() {
        return buttonStart;
    }

    public void setButtonStart(Boolean buttonStart) {
        this.buttonStart = buttonStart;
    }

    public AnalogStick getLeftStick() {
        return leftStick;
    }

    public void setLeftStick(AnalogStick leftStick) {
        this.leftStick = leftStick;
    }

    public AnalogStick getRightStick() {
        return rightStick;
    }

    public void setRightStick(AnalogStick rightStick) {
        this.rightStick = rightStick;
    }

    public DirectionalPad getdPad() {
        return dPad;
    }

    public void setdPad(DirectionalPad dPad) {
        this.dPad = dPad;
    }
}
