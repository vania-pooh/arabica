package org.meridor.arabica.support.impl;

import javafx.scene.Node;
import org.meridor.arabica.support.MouseSupport;
import org.meridor.arabica.support.annotations.RequiresUI;
import org.meridor.arabica.support.settings.ClickSettings;

@RequiresUI
public class MouseSupportImpl implements MouseSupport {

    @Override
    public void clickOn(Node node) {
        severalPrimaryButtonClicks(node, 1);
    }

    @Override
    public void doubleClickOn(Node node) {
        severalPrimaryButtonClicks(node, 2);
    }

    private void severalPrimaryButtonClicks(Node node, int clickCount) {
        ClickSettings clickSettings = new ClickSettings();
        clickSettings.setPrimaryButtonDown(true);
        clickSettings.setClickCount(clickCount);
        customClickOn(node, clickSettings);
    }

    @Override
    public void customClickOn(Node node, ClickSettings clickSettings) {
        node.fireEvent(clickSettings.toMouseEvent());
    }
}
