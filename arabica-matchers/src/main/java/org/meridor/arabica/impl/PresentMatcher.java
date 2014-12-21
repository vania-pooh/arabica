package org.meridor.arabica.impl;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

public class PresentMatcher extends TypeSafeMatcher<Optional> {

    @Override
    protected boolean matchesSafely(Optional item) {
        return item.isPresent();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("must be present");
    }

}
