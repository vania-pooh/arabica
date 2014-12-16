package org.meridor.arabica.impl;

import javafx.scene.control.Labeled;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasLabelMatcher extends TypeSafeMatcher<Labeled> {

    private final String label;

    public HasLabelMatcher(String label) {
        this.label = label;
    }

    public void describeTo(Description desc) {
        desc.appendText("visible");
    }

    @Override
    public boolean matchesSafely(Labeled item) {
        return item.getText().equals(label);
    }
}
