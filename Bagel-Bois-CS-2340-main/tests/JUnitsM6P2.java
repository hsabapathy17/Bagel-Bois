import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.Controller;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyCode.A;
import static org.junit.Assert.assertEquals;

public class JUnitsM6P2 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);

    }

    @Test
    public void checkLose() throws AWTException, InterruptedException {
        clickOn("Continue");
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        Controller.getPlayer().setCounter(4);
        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(2);
        clickOn("Final Boss");
        TimeUnit.SECONDS.sleep(10);
        assertEquals(0, Controller.getPlayer().getHealth());
    }
}
