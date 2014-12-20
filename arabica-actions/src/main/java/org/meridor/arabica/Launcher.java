package org.meridor.arabica;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Launcher<T extends Application> {

    private final Class<T> applicationClass;

    private volatile T applicationInstance;

    private volatile Stage primaryStage;

    public static <T extends Application> Launcher<T> from(Class<T> applicationClass) {
        return new Launcher<>(applicationClass);
    }

    private Launcher(Class<T> applicationClass) {
        this.applicationClass = applicationClass;
    }

    public void launch() throws Throwable {
        launch(applicationClass.newInstance());
    }

    public void exit() throws Exception {
        Platform.exit();
    }

    public T getApplicationInstance() {
        return applicationInstance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void launch(T applicationInstance) throws Throwable {
        Thread.currentThread().setName("Arabica Launcher");
        this.applicationInstance = applicationInstance;
        final CountDownLatch launcherThreadLatch = new CountDownLatch(1);
        try {
            applicationInstance.init();
            startApplication();
        } catch (Exception e) {
            throw new RuntimeException(e); //TODO: replace by something better
        } finally {
            launcherThreadLatch.countDown();
        }
        launcherThreadLatch.await();
    }

    private void startApplication() throws Throwable {

        PlatformImpl.startup(() -> {});
        doUIOperation(() -> {
            try {
                primaryStage = new Stage();
                applicationInstance.start(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void doUIOperation(Runnable runnable) throws Throwable {
        CountDownLatch fxThreadLatch = new CountDownLatch(1);
        AtomicReference<Throwable> throwableContainer = new AtomicReference<>();
        if (Platform.isFxApplicationThread()) {
            runnable.run();
            fxThreadLatch.countDown();
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        runnable.run();
                        fxThreadLatch.countDown();
                    } catch (Throwable e) {
                        throwableContainer.set(e);
                        fxThreadLatch.countDown();
                    }
                }
            });
        }
        fxThreadLatch.await();

        Throwable throwable = throwableContainer.get();
        if (throwable != null) {
            throw throwable;
        }
    }


}
