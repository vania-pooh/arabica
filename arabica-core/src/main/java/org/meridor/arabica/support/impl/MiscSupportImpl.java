package org.meridor.arabica.support.impl;

import org.meridor.arabica.support.MiscSupport;

import java.util.concurrent.TimeUnit;

public class MiscSupportImpl implements MiscSupport {

    @Override
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void sleep(long duration, TimeUnit timeUnit) {
        sleep(timeUnit.toMillis(duration));
    }

}
