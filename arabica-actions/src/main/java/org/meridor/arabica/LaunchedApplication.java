package org.meridor.arabica;

import javafx.application.Application;
import javafx.stage.Stage;

public class LaunchedApplication {

    private final Application runningInstance;

    private final Stage stage;

    private final Thread initThread;

    private final Thread fxThread;

    public LaunchedApplication(Thread fxThread, Application runningInstance, Stage stage, Thread initThread) {
        this.fxThread = fxThread;
        this.runningInstance = runningInstance;
        this.stage = stage;
        this.initThread = initThread;
    }


}
