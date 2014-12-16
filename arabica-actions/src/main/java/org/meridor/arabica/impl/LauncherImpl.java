package org.meridor.arabica.impl;

import javafx.application.Application;
import javafx.stage.Stage;
import org.meridor.arabica.Launcher;

public class LauncherImpl implements Launcher {

    @Override
    public <T extends Application> T launch(Class<T> applicationClass) throws Exception {
        T instance = applicationClass.newInstance();
        //TODO: this should actually launch application in a separate thread and return its instance
        instance.init();
        Stage stage = new Stage();
        instance.start(stage);
        return instance;
    }
}
