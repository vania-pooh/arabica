package org.meridor.arabica.impl;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PresentMatcherTest {

    @Test
    public void testMatchesCorrectly() {
        PresentMatcher presentMatcher = new PresentMatcher();
        assertTrue(presentMatcher.matchesSafely(Optional.of("some-value")));
        assertFalse(presentMatcher.matchesSafely(Optional.empty()));
    }

}