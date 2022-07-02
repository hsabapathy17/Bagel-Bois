
import gamemodel.Gamemodel;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobotInterface;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import sample.Controller;
import view.Difficulty;
import view.SecondScreen;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static javafx.scene.input.KeyCode.A;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.testfx.api.FxAssert.verifyThat;


public class ControllerTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);

    }

    @Test
    public void testStartGame() {
        clickOn("Start Game");
        verifyThat("Please Enter Your Name...", NodeMatchers.isNotNull());
    }

    @Test
    public void testMoney() {
        Gamemodel tester = new Gamemodel();
        assertEquals(800, tester.startingMoney(Difficulty.MEDIUM));
        assertEquals(1200, tester.startingMoney(Difficulty.EASY));
        assertEquals(400, tester.startingMoney(Difficulty.HARD));

    }

    @Test
    public void testHealth() {
        Gamemodel tester = new Gamemodel();
        assertEquals(40, tester.startingHealth(Difficulty.MEDIUM));
        assertEquals(60, tester.startingHealth(Difficulty.EASY));
        assertEquals(20, tester.startingHealth(Difficulty.HARD));
    }

    @Test
    public void testSecondScreen() {
        SecondScreen tester = new SecondScreen(1920, 1080);
        tester.getScene();
        assertEquals(3, tester.getDropDown().getItems().size());
    }

    @Test
    public void invalidLogin() {
        clickOn("Start Game");
        verifyThat("Please Enter Your Name...", NodeMatchers.isNotNull());
        clickOn("Continue");
        verifyThat("Please Enter Your Name...", NodeMatchers.isNotNull());
    }

    @Test
    public void selectGreenTower() {
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
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");

    }

    @Test
    public void selectRedTower() {
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

    }

    @Test
    public void selectYellowTower() {
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
        clickOn("YELLOW - Shoots at a Medium Speed, Does Medium Damage");
    }

    @Test
    public void dragGreenTower() {
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
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("Confirm");
        FxRobotInterface greenCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(150, 150);
        assertEquals(greenCircle, clickOn(150, 150));

    }

    @Test
    public void dragYellowTower() {
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
        clickOn("YELLOW - Shoots at a Medium Speed, Does Medium Damage");
        clickOn("Confirm");
        FxRobotInterface greenCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(150, 150);
        assertEquals(greenCircle, clickOn(150, 150));

    }

    @Test
    public void dragRedTower() {
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
        assertEquals(greenCircle, clickOn(150, 150));

    }

    @Test
    public void checkTowerNotEqual() throws AWTException {
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
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("Confirm");
        FxRobotInterface greenCircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(150, 150);

        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("Confirm");
        FxRobotInterface redcircle = clickOn(91, 91);
        drag(91, 91);
        dropTo(200, 200);
        Robot r = new Robot();
        assertNotEquals(r.getPixelColor(150, 150), r.getPixelColor(200, 200));
        clickOn("RED - Shoots at a Slow Speed, Does High Damage");
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("Confirm");

        assertEquals(r.getPixelColor(91, 91), r.getPixelColor(150, 150));

    }

    @Test
    public void checkTowerEqual() throws AWTException {
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
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("Confirm");
        drag(91, 91);
        dropTo(150, 150);
        Robot r = new Robot();
        clickOn("GREEN - Shoots at a High Speed, Does Low Damage");
        clickOn("Confirm");
        assertEquals(r.getPixelColor(91, 91), r.getPixelColor(150, 150));

    }

    @Test
    public void checkWater() throws AWTException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        Robot r = new Robot();
        assertEquals(40, r.getPixelColor(818, 730).getRed());
        assertEquals(144, r.getPixelColor(818, 730).getGreen());
        assertEquals(255, r.getPixelColor(818, 730).getBlue());

    }

    @Test
    public void checkMonument() throws AWTException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");
        Robot r = new Robot();
        assertEquals(168, r.getPixelColor(1841, 202).getRed());
        assertEquals(132, r.getPixelColor(1841, 202).getGreen());
        assertEquals(75, r.getPixelColor(1841, 202).getBlue());

    }

    @Test
    public void checkEnemiesEasy1() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(59, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkEnemiesEasy5() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(55, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkEnemiesEasy10() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("EASY");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("10");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(50, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkEnemiesMedium1() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("MEDIUM");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(39, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkEnemiesMedium5() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("MEDIUM");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(35, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkEnemiesMedium10() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("MEDIUM");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("10");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(30, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkEnemiesHard1() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("1");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(19, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkEnemiesHard5() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("5");
        clickOn("Continue");

        clickOn("Start Round");
        TimeUnit.SECONDS.sleep(5);
        assertEquals(15, Controller.getPlayer().getHealth());

    }

    @Test
    public void checkEnemiesHard10() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("10");
        clickOn("Continue");

        clickOn("Start Round");

        TimeUnit.SECONDS.sleep(5);
        assertEquals(10, Controller.getPlayer().getHealth());
    }

    @Test
    public void checkDeath() throws InterruptedException {
        clickOn("Start Game");
        clickOn("Choose Difficulty");
        clickOn("HARD");
        clickOn("Type your name");
        type(A);
        clickOn("Choose Enemy Number");
        clickOn("200");
        clickOn("Continue");
        clickOn("Start Round");

        TimeUnit.SECONDS.sleep(22);
    }
}
