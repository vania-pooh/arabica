package org.meridor.arabica;

import javafx.application.Application;

public interface Launcher {

    <T extends Application> T launch(Class<T> applicationClass) throws Exception;

}
