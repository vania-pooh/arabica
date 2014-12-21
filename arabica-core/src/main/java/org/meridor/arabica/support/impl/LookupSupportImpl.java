package org.meridor.arabica.support.impl;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.meridor.arabica.support.LookupSupport;
import org.meridor.arabica.support.PrimaryStageAware;
import org.meridor.arabica.support.annotations.RequiresUI;

import java.util.Optional;

@RequiresUI
public class LookupSupportImpl implements LookupSupport {

    private final PrimaryStageAware primaryStageAware;

    public LookupSupportImpl(PrimaryStageAware primaryStageAware) {
        this.primaryStageAware = primaryStageAware;
    }

    public Optional<Node> lookup(String selector) {
        return lookup(this.primaryStageAware.getPrimaryStage(), selector);
    }

    @Override
    public Optional<Node> lookup(Scene scene, String selector) {
        return Optional.ofNullable(scene.lookup(selector));

    }

    @Override
    public Optional<Node> lookup(Stage stage, String selector) {
        return lookup(stage.getScene(), selector);
    }
}
