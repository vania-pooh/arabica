package org.meridor.arabica.support;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public interface KeyboardSupport {

    void pressKey(KeyCode keyCode);

    void pressKey(Node node, KeyCode keyCode);

}
