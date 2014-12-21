package org.meridor.arabica.support;

import javafx.scene.Node;
import org.meridor.arabica.support.settings.ClickSettings;

public interface ClickSupport {

    void clickOn(Node node);

    void doubleClickOn(Node node);

    void customClickOn(Node node, ClickSettings clickSettings);

}
