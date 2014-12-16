package org.meridor.arabica.impl;

import javafx.scene.control.Button;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HasLabelMatcherTest {

    @Ignore //TODO: requires platform to be launched
    @Test
    public void testMatchesCorrectly() {
        final String PRESENT_LABEL = "aLabel";
        Button button = new Button();
        button.setText(PRESENT_LABEL);

        HasLabelMatcher presentMatcher = new HasLabelMatcher(PRESENT_LABEL);
        assertTrue(presentMatcher.matchesSafely(button));

        HasLabelMatcher missingMatcher = new HasLabelMatcher("missingLabel");
        assertFalse(missingMatcher.matchesSafely(button));
    }

}
