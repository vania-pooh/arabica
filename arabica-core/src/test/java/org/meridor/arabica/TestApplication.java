package org.meridor.arabica;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class TestApplication extends Application {

    private int clicksNumber = 0;

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Click counter");
        Button button = new Button();
        button.setId("button");
        button.setText("Click me!");
        button.setOnMouseClicked(event -> clicksNumber++);
        Scene scene = new Scene(new Pane(button));
        stage.setScene(scene);
        stage.show();
    }

    public int getClicksNumber() {
        return clicksNumber;
    }
}
