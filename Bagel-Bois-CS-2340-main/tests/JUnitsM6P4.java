import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobotInterface;
import org.testfx.framework.junit.ApplicationTest;
import sample.Controller;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyCode.A;
import static org.junit.Assert.assertEquals;


public class JUnitsM6P4 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);

    }

    @Test
    public void checkGreenTower() throws AWTException {
        clickOn("Continue");
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("Confirm");
        Robot r = new Robot();
        assertEquals(42, r.getPixelColor(310, 380).getRed());
        assertEquals(70, r.getPixelColor(310, 380).getGreen());
        assertEquals(45, r.getPixelColor(310, 380).getBlue());
    }

    @Test
    public void checkYellowTower() throws AWTException {
        clickOn("Continue");
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("YELLOW - Shoots at a Medium Speed, Does Medium Damage");
        clickOn("Confirm");
        Robot r = new Robot();
        assertEquals(213, r.getPixelColor(310, 380).getRed());
        assertEquals(193, r.getPixelColor(310, 380).getGreen());
        assertEquals(139, r.getPixelColor(310, 380).getBlue());
    }

    @Test
    public void checkCounter() throws AWTException, InterruptedException {
        clickOn("Continue");
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(2);

        assertEquals(1, Controller.getPlayer().getCounter());
    }

    @Test
    public void moveRed() {
        Controller testController = new Controller();
        clickOn("Continue");
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redCircle = clickOn(318, 395);
        drag(318, 395);
        dropTo(150, 150);
        assertEquals(redCircle, clickOn(150, 150));

    }
    @Test
    public void moveGreen() {
        Controller testController = new Controller();
        clickOn("Continue");
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("Confirm");
        FxRobotInterface greenCircle = clickOn(318, 395);
        drag(318, 395);
        dropTo(150, 150);
        assertEquals(greenCircle, clickOn(150, 150));

    }

    @Test
    public void checkRedTower() throws AWTException {
        clickOn("Continue");
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
        Robot r = new Robot();
        assertEquals(48, r.getPixelColor(310, 380).getRed());
        assertEquals(14, r.getPixelColor(310, 380).getGreen());
        assertEquals(9, r.getPixelColor(310, 380).getBlue());
    }

    @Test
    public void moveYellow() {
        Controller testController = new Controller();
        clickOn("Continue");
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");
        clickOn("Select Tower");
        clickOn("YELLOW - Shoots at a Medium Speed, Does Medium Damage");
        clickOn("Confirm");
        FxRobotInterface yellowCircle = clickOn(318, 395);
        drag(318, 395);
        dropTo(150, 150);
        assertEquals(yellowCircle, clickOn(150, 150));

    }
}