package org.meridor.arabica;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.meridor.arabica.Launcher.doUIOperation;

public class LauncherTest {

    @Test
    public void testLaunch() throws Throwable {
        final Launcher<TestApplication> launcher = Launcher.from(TestApplication.class);
        launcher.launch();
        TestApplication application = launcher.getApplicationInstance();
        assertNotNull(application);
        assertThat(application.getClicksNumber(), equalTo(0));
        System.out.println("Clicking on button");
        doUIOperation(() -> {
            Stage primaryStage = launcher.getPrimaryStage();
            Node node = primaryStage.getScene().lookup("#button");
            assertNotNull(node);
            assertThat(node, instanceOf(Button.class));
            Button button = (Button) node;
            button.fire();
            button.fireEvent(new MouseEvent(
                    MouseEvent.MOUSE_CLICKED,
                    0,
                    0,
                    0,
                    0,
                    MouseButton.PRIMARY,
                    1,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    null
            ));
        });
        assertThat(application.getClicksNumber(), equalTo(1));
    }

}
