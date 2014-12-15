package org.meridor.arabica.impl;

import javafx.scene.Node;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EnabledMatcher extends TypeSafeMatcher<Node> {

    @Override
    protected boolean matchesSafely(Node item) {
        return !item.isDisabled();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("enabled");
    }
}
