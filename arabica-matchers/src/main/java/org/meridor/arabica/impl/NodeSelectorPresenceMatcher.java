package org.meridor.arabica.impl;

import javafx.scene.Node;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class NodeSelectorPresenceMatcher extends TypeSafeMatcher<Node> {

    private final String selector;

    public NodeSelectorPresenceMatcher(String selector) {
        this.selector = selector;
    }

    @Override
    protected boolean matchesSafely(Node node) {
        return node.lookupAll(selector).size() > 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("selector")
                .appendValue(selector)
                .appendText("present on node");
    }
}
