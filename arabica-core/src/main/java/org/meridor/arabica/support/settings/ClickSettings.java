package org.meridor.arabica.support.settings;

import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public class ClickSettings {

    private EventType<? extends MouseEvent> eventType = MouseEvent.MOUSE_CLICKED;
    private double x = 0;
    private double y = 0;
    private double screenX = 0;
    private double screenY = 0;
    private MouseButton button = MouseButton.PRIMARY;
    private int clickCount = 0;
    private boolean shiftDown = false;
    private boolean controlDown = false;
    private boolean altDown = false;
    private boolean metaDown = false;
    private boolean primaryButtonDown = false;
    private boolean middleButtonDown = false;
    private boolean secondaryButtonDown = false;
    private boolean synthesized = false;
    private boolean popupTrigger = false;
    private boolean stillSincePress = false;
    private PickResult pickResult = null;

    public MouseEvent toMouseEvent() {
        return new MouseEvent(
                eventType,
                x,
                y,
                screenX,
                screenY,
                button,
                clickCount,
                shiftDown,
                controlDown,
                altDown,
                metaDown,
                primaryButtonDown,
                middleButtonDown,
                secondaryButtonDown,
                synthesized,
                popupTrigger,
                stillSincePress,
                pickResult
        );
    }

    public void setAltDown(boolean altDown) {
        this.altDown = altDown;
    }

    public void setButton(MouseButton button) {
        this.button = button;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public void setControlDown(boolean controlDown) {
        this.controlDown = controlDown;
    }

    public void setEventType(EventType<? extends MouseEvent> eventType) {
        this.eventType = eventType;
    }

    public void setMetaDown(boolean metaDown) {
        this.metaDown = metaDown;
    }

    public void setMiddleButtonDown(boolean middleButtonDown) {
        this.middleButtonDown = middleButtonDown;
    }

    public void setPickResult(PickResult pickResult) {
        this.pickResult = pickResult;
    }

    public void setPopupTrigger(boolean popupTrigger) {
        this.popupTrigger = popupTrigger;
    }

    public void setPrimaryButtonDown(boolean primaryButtonDown) {
        this.primaryButtonDown = primaryButtonDown;
    }

    public void setScreenX(double screenX) {
        this.screenX = screenX;
    }

    public void setScreenY(double screenY) {
        this.screenY = screenY;
    }

    public void setSecondaryButtonDown(boolean secondaryButtonDown) {
        this.secondaryButtonDown = secondaryButtonDown;
    }

    public void setShiftDown(boolean shiftDown) {
        this.shiftDown = shiftDown;
    }

    public void setStillSincePress(boolean stillSincePress) {
        this.stillSincePress = stillSincePress;
    }

    public void setSynthesized(boolean synthesized) {
        this.synthesized = synthesized;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
