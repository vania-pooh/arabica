package org.meridor.arabica.impl;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DisabledMatcherTest {

    @Test
    public void testMatchesCorrectly() {
        DisabledMatcher matcher = new DisabledMatcher();
        Node node = new Pane();

        node.setDisable(true);
        assertTrue(matcher.matchesSafely(node));

        node.setDisable(false);
        assertFalse(matcher.matchesSafely(node));
    }

}
