package org.meridor.arabica.support;

import java.util.concurrent.TimeUnit;

public interface MiscSupport {

    void sleep(long milliseconds);

    void sleep(long duration, TimeUnit timeUnit);

}
