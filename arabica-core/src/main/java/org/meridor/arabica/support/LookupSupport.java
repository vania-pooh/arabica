package org.meridor.arabica.support;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Optional;

public interface LookupSupport {

    Optional<Node> lookup(String selector);

    Optional<Node> lookup(Scene scene, String selector);

    Optional<Node> lookup(Stage stage, String selector);

}
