package org.meridor.arabica.impl;

import javafx.application.Application;
import javafx.stage.Stage;
import org.meridor.arabica.Launcher;

import java.util.concurrent.CountDownLatch;

public class LauncherImpl implements Launcher {

    private final CountDownLatch launcherThreadLatch = new CountDownLatch(1);

    @Override
    public <T extends Application> T launch(Class<T> applicationClass) throws Exception {
        T applicationInstance = applicationClass.newInstance();
        Thread launcherThread = getLauncherThread(applicationInstance);
        launcherThread.start();
        launcherThreadLatch.await();
        return applicationInstance;
    }

    private <T extends Application> Thread getLauncherThread(T applicationInstance){
        return new Thread(){
            @Override
            public void run() {
                super.run();
                //TODO: this should actually launch application in a separate thread and return its instance
                try {
                    applicationInstance.init();
                    Stage stage = new Stage();
                    applicationInstance.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e); //TODO: replace by something better
                } finally {
                    launcherThreadLatch.countDown();
                }
            }
        };
    }
}
