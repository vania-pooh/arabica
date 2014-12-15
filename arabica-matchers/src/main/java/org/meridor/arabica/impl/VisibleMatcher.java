package org.meridor.arabica.impl;

import javafx.scene.Node;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class VisibleMatcher extends TypeSafeMatcher<Node> {

    public void describeTo(Description desc) {
        desc.appendText("visible");
    }

    @Override
    public boolean matchesSafely(Node item) {
        return item.isVisible();
    }
}
