package org.meridor.arabica;

import javafx.scene.Node;
import javafx.scene.control.Labeled;
import org.hamcrest.Matcher;
import org.meridor.arabica.impl.*;

public class Matchers {

    public static Matcher<Node> isEnabled() {
        return new EnabledMatcher();
    }

    public static Matcher<Node> isDisabled() {
        return new DisabledMatcher();
    }

    public static Matcher<Node> isVisible() {
        return new VisibleMatcher();
    }

    public static Matcher<Node> containsSelector(String selector) {
        return new NodeSelectorPresenceMatcher(selector);
    }

    public static Matcher<Labeled> hasLabel(String label) {
        return new HasLabelMatcher(label);
    }

}
