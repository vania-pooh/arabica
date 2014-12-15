package org.meridor.arabica.impl;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VisibleMatcherTest {

    @Test
    public void testMatchesCorrectly() {
        VisibleMatcher matcher = new VisibleMatcher();
        Node node = new Pane();

        node.setVisible(true);
        assertTrue(matcher.matchesSafely(node));

        node.setVisible(false);
        assertFalse(matcher.matchesSafely(node));
    }

}
