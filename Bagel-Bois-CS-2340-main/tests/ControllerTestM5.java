import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobotInterface;
import org.testfx.framework.junit.ApplicationTest;
import sample.Controller;

import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyCode.A;
import static org.junit.Assert.assertEquals;

public class ControllerTestM5 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);

    }

    @Test
    public void checkMoneyGainEasy() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(12);
        assertEquals(1700, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainMedium() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("MEDIUM");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(12);
        assertEquals(1100, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainHard() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(12);
        assertEquals(600, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainEasy5() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(322, 406);

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(1725, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainMedium5() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("MEDIUM");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(322, 406);

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(1150, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainHard5() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(322, 406);

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(650, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainEasy10() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("10");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(322, 406);

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(1775, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainMedium10() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("MEDIUM");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("10");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(322, 406);

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(10);
        assertEquals(1250, Controller.getPlayer().getMoney());
    }

    @Test
    public void checkMoneyGainHard10() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("10");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(322, 406);

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(10);
        assertEquals(800, Controller.getPlayer().getMoney());
    }

    @Test
    public void moveTowerInGame() {
        Controller testController = new Controller();
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface greenCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(150, 150);
        clickOn("Start Round");
        drag(150, 150);
        dropTo(100, 100);
        assertEquals(greenCircle, clickOn(100, 100));

    }

}
