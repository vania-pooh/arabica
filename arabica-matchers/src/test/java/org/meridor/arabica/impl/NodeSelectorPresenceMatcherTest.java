package org.meridor.arabica.impl;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeSelectorPresenceMatcherTest {

    @Test
    public void testMatchesCorrectly() {
        final String PRESENT_ID = "existingId";
        Pane childPane = new Pane();
        childPane.setId(PRESENT_ID);
        Parent parentNode = new Pane(childPane);

        NodeSelectorPresenceMatcher missingNodeMatcher = new NodeSelectorPresenceMatcher("#notExistingId");
        assertFalse(missingNodeMatcher.matchesSafely(parentNode));

        NodeSelectorPresenceMatcher presentNodeMatcher = new NodeSelectorPresenceMatcher("#" + PRESENT_ID);
        assertTrue(presentNodeMatcher.matchesSafely(parentNode));

    }

}
