package org.meridor.arabica;

import javafx.scene.Node;
import javafx.scene.control.Button;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.meridor.arabica.Matchers.isPresent;

public class LauncherTest extends JavaFXTest<TestApplication> {

    @Test
    public void testLaunch() {
        TestApplication application = getApplicationInstance();
        assertNotNull(application);
        assertThat(application.getClicksNumber(), equalTo(0));
        Optional<Node> node = lookup("#button");
        assertThat(node, isPresent());
        assertThat(node.get(), instanceOf(Button.class));
        Button button = (Button) node.get();
        clickOn(button);
        assertThat(application.getClicksNumber(), equalTo(1));
    }

    @Override
    public Class<TestApplication> getApplicationClass() {
        return TestApplication.class;
    }
}
