package org.meridor.arabica;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.meridor.arabica.support.ArabicaInvocationHandler;
import org.meridor.arabica.support.ClickSupport;
import org.meridor.arabica.support.LookupSupport;
import org.meridor.arabica.support.PrimaryStageAware;
import org.meridor.arabica.support.impl.ClickSupportImpl;
import org.meridor.arabica.support.impl.LookupSupportImpl;
import org.meridor.arabica.support.settings.ClickSettings;

import java.lang.reflect.Proxy;
import java.util.Optional;

public abstract class JavaFXTest<T extends Application> implements LauncherAware, PrimaryStageAware, LookupSupport, ClickSupport {

    private final Launcher<T> launcher;
    private ClickSupport clickSupport;
    private LookupSupport lookupSupport;

    public JavaFXTest() {
        this.launcher = Launcher.from(getApplicationClass());
        wireSupportClasses();
    }

    public abstract Class<T> getApplicationClass();

    public Launcher<T> getLauncher() {
        return launcher;
    }

    /**
     * This is the only place in the code where implementations are wired to interfaces. We decided to not use
     * any IoC container such as Google Guice or Spring so the library still remains lightweight.
     */
    private void wireSupportClasses() {
        this.lookupSupport = createProxyIfNeeded(LookupSupport.class, new LookupSupportImpl(this));
        this.clickSupport = createProxyIfNeeded(ClickSupport.class, new ClickSupportImpl());
    }

    private <S> S createProxyIfNeeded(Class<S> interfaceToProxy, S implementationClass) {
        ArabicaInvocationHandler invocationHandler = new ArabicaInvocationHandler(this, implementationClass);
        @SuppressWarnings("unchecked")
        S proxy = (S) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{interfaceToProxy},
                invocationHandler);
        return proxy;
    }

    //--------------------------------------------------------------------
    // HERE COME GENERATED DELEGATE METHODS
    //--------------------------------------------------------------------


    public boolean isLaunched() {
        return launcher.isLaunched();
    }

    @Override
    public Stage getPrimaryStage() {
        return launcher.getPrimaryStage();
    }

    public void exit() {
        launcher.exit();
    }

    public T getApplicationInstance() {
        return launcher.getApplicationInstance();
    }

    @Override
    public void clickOn(Node node) {
        clickSupport.clickOn(node);
    }

    @Override
    public void customClickOn(Node node, ClickSettings clickSettings) {
        clickSupport.customClickOn(node, clickSettings);
    }

    @Override
    public void doubleClickOn(Node node) {
        clickSupport.doubleClickOn(node);
    }

    @Override
    public Optional<Node> lookup(Scene scene, String selector) {
        return lookupSupport.lookup(scene, selector);
    }

    @Override
    public Optional<Node> lookup(String selector) {
        return lookupSupport.lookup(selector);
    }

    @Override
    public Optional<Node> lookup(Stage stage, String selector) {
        return lookupSupport.lookup(stage, selector);
    }
}
