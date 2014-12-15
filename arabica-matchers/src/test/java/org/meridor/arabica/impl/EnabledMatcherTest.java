package org.meridor.arabica.impl;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnabledMatcherTest {

    @Test
    public void testMatchesCorrectly() {
        EnabledMatcher matcher = new EnabledMatcher();
        Node node = new Pane();

        node.setDisable(true);
        assertFalse(matcher.matchesSafely(node));

        node.setDisable(false);
        assertTrue(matcher.matchesSafely(node));
    }

}
