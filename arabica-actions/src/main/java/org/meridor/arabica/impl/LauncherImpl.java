package org.meridor.arabica.impl;

import javafx.application.Application;
import javafx.stage.Stage;
import org.meridor.arabica.Launcher;

import java.util.concurrent.CountDownLatch;

public class LauncherImpl implements Launcher {


    @Override
    public <T extends Application> T launch(Class<T> applicationClass) throws Exception {
        T applicationInstance = applicationClass.newInstance();
        final CountDownLatch launcherThreadLatch = new CountDownLatch(1);
        Thread launcherThread = getLauncherThread(applicationInstance, launcherThreadLatch);
        launcherThread.start();
        launcherThreadLatch.await();
        return applicationInstance;
    }

    @Override
    public <T extends Application> void shutdown(T applicationInstance) throws Exception {

    }

    private <T extends Application> Thread getLauncherThread(T applicationInstance, CountDownLatch launcherThreadLatch){
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                //TODO: this should actually launch application in a separate thread and return its instance
                try {
                    applicationInstance.init();
                    CountDownLatch fxThreadLatch = new CountDownLatch(1);
                    Thread fxThread = getFXThread(applicationInstance, fxThreadLatch);
                    fxThread.start();
                    fxThreadLatch.await();
                } catch (Exception e) {
                    throw new RuntimeException(e); //TODO: replace by something better
                } finally {
                    launcherThreadLatch.countDown();
                }
            }
        };
        thread.setName("Arabica Application Launcher");
        return thread;
    }

    private <T extends Application> Thread getFXThread(T applicationInstance, CountDownLatch fxThreadLatch) throws Exception {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    Stage stage = new Stage();
                    applicationInstance.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    fxThreadLatch.countDown();
                }
            }
        };
        thread.setName("Arabica FX Thread");
        return thread;
    }
}
