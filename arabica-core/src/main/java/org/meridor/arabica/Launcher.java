package org.meridor.arabica;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Launcher<T extends Application> {

    private final Class<T> applicationClass;

    private volatile T applicationInstance;

    private volatile Stage primaryStage;

    private volatile AtomicBoolean launched = new AtomicBoolean();

    public static <T extends Application> Launcher<T> from(Class<T> applicationClass) {
        return new Launcher<>(applicationClass);
    }

    private Launcher(Class<T> applicationClass) {
        this.applicationClass = applicationClass;
    }

    public void launch() {
        T instance = instatiate();
        launch(instance);
    }

    public void exit() {
        Platform.exit();
    }

    public boolean isLaunched() {
        return launched.get();
    }

    public T getApplicationInstance() {
        requireLaunchedApplication();
        return applicationInstance;
    }

    public Stage getPrimaryStage() {
        requireLaunchedApplication();
        return primaryStage;
    }

    private T instatiate() {
        try {
            return applicationClass.newInstance();
        } catch (Exception e) {
            throw new ArabicaException(e);
        }
    }

    private void launch(T applicationInstance) {
        Thread.currentThread().setName("Arabica Launcher");
        this.applicationInstance = applicationInstance;
        final CountDownLatch launcherThreadLatch = new CountDownLatch(1);
        try {
            applicationInstance.init();
            startApplication();
            launched.set(true);
        } catch (Exception e) {
            throw new ArabicaException(e);
        } finally {
            launcherThreadLatch.countDown();
        }
        await(launcherThreadLatch);
    }

    private void startApplication() {

        PlatformImpl.startup(() -> {});
        withUI(() -> {
            try {
                primaryStage = new Stage();
                applicationInstance.start(primaryStage);
            } catch (Exception e) {
                throw new ArabicaException(e);
            }
        });
    }

    public void withUI(Runnable runnable) {
        CountDownLatch fxThreadLatch = new CountDownLatch(1);
        AtomicReference<AssertionError> assertionErrorContainer = new AtomicReference<>();
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
                    } catch (AssertionError e) {
                        assertionErrorContainer.set(e);
                        fxThreadLatch.countDown();
                    } catch (Throwable e) {
                        fxThreadLatch.countDown();
                        throw new ArabicaException(e);
                    }
                }
            });
        }
        await(fxThreadLatch);

        AssertionError assertionError = assertionErrorContainer.get();
        if (assertionError != null) {
            throw assertionError;
        }
    }

    public <V> V withUI(Callable<V> callable) throws Exception {
        AtomicReference<V> returnValueContainer = new AtomicReference<>();
        AtomicReference<Exception> exceptionContainer = new AtomicReference<>();
        withUI(() -> {
            try {
                V returnValue = callable.call();
                returnValueContainer.set(returnValue);
            } catch (Exception e) {
                exceptionContainer.set(e);
            }
        });
        Exception exception = exceptionContainer.get();
        if (exception != null) {
            throw exception;
        }
        return returnValueContainer.get();
    }

    private void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new ArabicaException(e);
        }
    }

    public void requireLaunchedApplication() {
        if (!isLaunched()){
            launch();
        }
    }


}
