package org.meridor.arabica;

import javafx.scene.Node;
import org.hamcrest.Matcher;
import org.meridor.arabica.impl.DisabledMatcher;
import org.meridor.arabica.impl.EnabledMatcher;
import org.meridor.arabica.impl.NodeSelectorPresenceMatcher;
import org.meridor.arabica.impl.VisibleMatcher;

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

}
