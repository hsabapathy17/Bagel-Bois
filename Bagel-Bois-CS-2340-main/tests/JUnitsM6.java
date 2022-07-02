import javafx.scene.control.Control;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobotInterface;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import sample.Controller;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyCode.A;
import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class JUnitsM6 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);

    }

    @Test
    public void checkBossButton() throws AWTException, InterruptedException {
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
    }




}