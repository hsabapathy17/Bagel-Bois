package sample;

import gamemodel.Gamemodel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller extends Application {

    private static Stage mainWindow;
    private static Gamemodel gameModel;
    private static GameController gameController;
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static Player player;
    private static ArrayList<Tower> shapes;
    private static int counter = 0;
    private Text status;
    private static ArrayList<Circle> cs;
    private static int totalKills;
    private static int totalDamage;

    // stuff to control tower
    private static int towerCounter = 0;


    private MediaPlayer mediaPlayer;

    public static Player getPlayer() {
        return player;
    }


    @Override
    public void start(Stage primaryStage) {
        mainWindow = primaryStage;
        mainWindow.setTitle("Tower Defense");
        gameModel = new Gamemodel();
        initStartScreen();
    }

    public void music() {
        File file = new File("src/resources/song.wav");
        Media media;
        try {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.05);
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                }
            });
            mediaPlayer.setAutoPlay(true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    private static void initStartScreen() {
        StartScreen screen = new StartScreen(WIDTH, HEIGHT);
        Button continueButton = screen.getContinueButton();
        continueButton.setOnAction(e -> gotoFirstScreen());
        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.setResizable(false);
        mainWindow.show();
    }
    private static void gotoFirstScreen() {
        FirstScreen screen = new FirstScreen(WIDTH, HEIGHT);
        Button quitButton = screen.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());
        Button playButton = screen.getPlayButton();
        playButton.setOnAction(e -> goToSecondScreen());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.setResizable(false);
        mainWindow.show();
    }

    private static void goToSecondScreen() {
        SecondScreen screen = new SecondScreen(WIDTH, HEIGHT);
        AtomicBoolean hasWhiteSpace = new AtomicBoolean(false);
        Button nextButton = screen.getNext();

        nextButton.setOnAction(e -> {
            for (int i = 0; i < screen.getTextField().getText().length(); i++) {
                if (screen.getTextField().getText().charAt(i) != ' ') {
                    hasWhiteSpace.set(false);
                } else {
                    if (screen.getTextField().getText().charAt(i) == ' ') {
                        hasWhiteSpace.set(true);
                    }
                }
            }
            if (!(screen.getTextField().getText() == null)
                    && !hasWhiteSpace.get()
                    && !(screen.getTextField().getText().isEmpty())
                    && !(screen.getDropDown().getValue() == null)) {
                if (screen.getDropDown().getValue().equals("EASY")) {
                    playGame(Difficulty.EASY, screen.getTextField().getText(),
                            (Integer) screen.getEnemyNumber().getValue());
                } else if (screen.getDropDown().getValue().equals("MEDIUM")) {
                    playGame(Difficulty.MEDIUM, screen.getTextField().getText(),
                            (Integer) screen.getEnemyNumber().getValue());
                } else if (screen.getDropDown().getValue().equals("HARD")) {
                    playGame(Difficulty.HARD,
                            screen.getTextField().getText(),
                            (Integer) screen.getEnemyNumber().getValue());
                }
            } else if (screen.getTextField().getText() == null
                    || screen.getTextField().getText().isEmpty()
                    || hasWhiteSpace.get()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Invalid inputs. Please correct your selections");
                alert.showAndWait();
            } else if (screen.getDropDown().getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Invalid inputs. Please correct your selections.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Invalid inputs. Please correct your selections.");
                alert.showAndWait();
            }
        });

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }


    public static void playGame(Difficulty gameDifficulty, String name, int enemyNumber) {

        int health = gameModel.startingHealth(gameDifficulty);
        int money = gameModel.startingMoney(gameDifficulty);
        player = new Player(health, money, name);
        MapScreen screen = new MapScreen(WIDTH, HEIGHT, player);
        Button confirmButton = screen.getButton();
        shapes = new ArrayList<>();
        gameController = new GameController(screen, gameDifficulty, player);
        gameController.reset();
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Circle r = new Circle();
                r.setCenterX(322);
                r.setCenterY(391);
                r.setRadius(32f);

                Object input = screen.getTowerSelect().getValue();

                if (input.equals("RED - Shoots at a Slow Speed, Does High Damage")) {
                    try {
                        Image img = new Image(new FileInputStream("src/resources/towers/tower-red.png"));
                        ImagePattern pattern = new ImagePattern(img);
                        r.setFill(pattern);

                    } catch (Exception exception) {
                        System.out.println("It didn't work");
                    }
                    //r.setFill(Color.RED);
                    createTower(screen, gameDifficulty, r); // adds it to screen
                    traceTower(Towers.RED, r);
                }
                if (input.equals("GREEN - Shoots at "
                        + "a High Speed, Does Low Damage")) {
                    try {
                        Image img = new Image(new FileInputStream("src/resources/towers/tower-green.png"));
                        ImagePattern pattern = new ImagePattern(img);
                        r.setFill(pattern);

                    } catch (Exception exception) {
                        System.out.println("It didn't work");
                    }
                    //r.setFill(Color.GREEN);
                    createTower(screen, gameDifficulty, r);
                    traceTower(Towers.GREEN, r);
                }
                if (input.equals("YELLOW - Shoots at a Medium Speed"
                        + ", Does Medium Damage")) {
                    try {
                        Image img = new Image(new FileInputStream("src/resources/towers/tower-yellow.png"));
                        ImagePattern pattern = new ImagePattern(img);
                        r.setFill(pattern);

                    } catch (Exception exception) {
                        System.out.println("It didn't work");
                    }
                    //r.setFill(Color.YELLOW);
                    createTower(screen, gameDifficulty, r);
                    traceTower(Towers.YELLOW, r);
                }

                shapes = gameController.getTowers();

                int i = 0;
                for (Tower t : shapes) {
                    screen.setDragListeners(t.getC(), i);
                    boolean posBad = screen.checkShapeIntersection(t.getC());
                    i++;
                }

            }


        });

        Button resetButton = screen.getReset();

        resetButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) { // this needs to be fixed
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setContentText("If you reset then all towers will"
                        + " be sold and refunded to you. Please confirm that you want to reset.");
                alert.showAndWait();
                player.setMoney(gameModel.startingMoney(gameDifficulty));
                screen.updateMoneyLabel(player);
                Group root = screen.getRoot();
                System.out.println(shapes.size());
                for (Tower tower : shapes) {
                    root.getChildren().remove(tower.getC());
                    root.getChildren().remove(tower.getRange());
                }
                cs.clear();
                shapes.clear();
                player.setMoney(gameModel.startingMoney(gameDifficulty));
                gameController.reset();
                screen.updateMoneyLabel(player);

            }
        });

        Button upgradeButton = screen.getUpgrade();
        upgradeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(player.getMoney() - (shapes.size() * 100) < 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("You do not have enough money to do that");
                    alert.showAndWait();
                } else {
                    for(Tower tower: shapes) {
                        int x = tower.getBaseDamage();
                        x = x * 2;
                        tower.setDamage(x);
                        player.setMoney(player.getMoney() - 100);
                        screen.updateMoneyLabel(player);
                    }
                }
            }
        });
        Button startButton = screen.getStartGame();
        startButton.setOnAction(e -> {
            incrementCounter();
            player.incrementCounter();
            screen.hideBoss();
            if (screen.getCollisionDetected()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setContentText("One or more of the towers "
                        + "that you have placed were placed illegally"
                        + ". Please click reset and avoid placing towers directly on the path.");
                alert.showAndWait();
            } else {
                // setting up the pathing tiles
                Polyline polyline = new Polyline();
                polyline.getPoints().addAll(new Double[]{
                    0.0, 440.0,
                    225.0, 440.0,
                    217.0, 300.0,
                    412.0, 314.0,
                    413.0, 552.0,
                    223.0, 565.0,
                    225.0, 800.0,
                    630.0, 806.0,
                    630.0, 217.0,
                    444.0, 213.0,
                    433.0, 99.0,
                    983.0, 100.0,
                    990.0, 338.0,
                    795.0, 344.0,
                    802.0, 493.0,
                    993.0, 497.0,
                    992.0, 647.0,
                    1250.0, 652.0,
                    1247.0, 798.0,
                    1479.0, 793.0,
                    1477.0, 207.0,
                    1780.0, 204.0,


                });

                cs = gameController.setRanges();
                for (Circle circle : cs) {
                    System.out.println("range in contr: " + circle.getCenterX());
                    Group root = screen.getRoot();
                    root.getChildren().add(circle);
                }

                screen.hideMenu();
                if (gameDifficulty == Difficulty.EASY) {
                    player.setMoney(player.getMoney() + 500);
                } else if (gameDifficulty == Difficulty.MEDIUM) {
                    player.setMoney(player.getMoney() + 300);
                } else if (gameDifficulty == Difficulty.HARD) {
                    player.setMoney(player.getMoney() + 200);
                }
                screen.updateMoneyLabel(player);
                gameController.spawn(polyline, enemyNumber);
            }
        });

        Button finBoss = screen.getFinalBoss();
        finBoss.setOnAction(e -> {
            screen.hideBoss();
            if (screen.getCollisionDetected()) {
            cs.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("One or more of the towers "
                    + "that you have placed were placed illegally"
                    + ". Please click reset and avoid placing towers directly on the path.");
            alert.showAndWait();
        } else {
            // setting up the pathing tiles
            Polyline polyline = new Polyline();
            polyline.getPoints().addAll(new Double[]{
                    0.0, 440.0,
                    225.0, 440.0,
                    217.0, 300.0,
                    412.0, 314.0,
                    413.0, 552.0,
                    223.0, 565.0,
                    225.0, 800.0,
                    630.0, 806.0,
                    630.0, 217.0,
                    444.0, 213.0,
                    433.0, 99.0,
                    983.0, 100.0,
                    990.0, 338.0,
                    795.0, 344.0,
                    802.0, 493.0,
                    993.0, 497.0,
                    992.0, 647.0,
                    1250.0, 652.0,
                    1247.0, 798.0,
                    1479.0, 793.0,
                    1477.0, 207.0,
                    1780.0, 204.0,


            });

            cs = gameController.setRanges();
            for (Circle circle : cs) {
                System.out.println("range in contr: " + circle.getCenterX());
                Group root = screen.getRoot();
                root.getChildren().add(circle);
            }

            screen.hideMenu();
            if (gameDifficulty == Difficulty.EASY) {
                player.setMoney(player.getMoney() + 10000);
            } else if (gameDifficulty == Difficulty.MEDIUM) {
                player.setMoney(player.getMoney() + 300);
            } else if (gameDifficulty == Difficulty.HARD) {
                player.setMoney(player.getMoney() + 200);
            }
            screen.updateMoneyLabel(player);
            if(gameDifficulty == Difficulty.EASY) {
                totalKills = (1 + (counter * enemyNumber) - (60 - player.getHealth()));
                totalDamage = totalKills + 50;
            }
            else if (gameDifficulty == Difficulty.MEDIUM) {
                totalKills = (1 + (counter * enemyNumber) - (40 - player.getHealth()));
                totalDamage = totalKills + 50;
            }

            else if (gameDifficulty == Difficulty.HARD) {
                totalKills = ((counter * enemyNumber) - (20 - player.getHealth()));
                totalDamage = totalKills * 3 + 50;
            }
            gameController.spawnBoss(polyline);
        }

        });

        Scene update = screen.getScene();
        mainWindow.setScene(update);
        mainWindow.show();

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.setResizable(false);
        mainWindow.show();
    }

    // alert helper method
    private static void moneyAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("You cannot afford to buy this tower!");
        alert.showAndWait();
    }

    private static void traceTower(Towers e, Circle r) {
        String id = "t" + towerCounter;
        gameController.addTower(e, r, id); // actually makes these towers traceable
        towerCounter++;
    }

    private static void createTower(MapScreen screen, Difficulty gameDifficulty, Circle r) {
        if (gameModel.towerCost(gameDifficulty, Towers.RED) < player.getMoney()) {
            player.setMoney(gameModel.buyTower(player, gameDifficulty, Towers.RED));
            screen.updateMoneyLabel(player);
            Group root = screen.getRoot();
            root.getChildren().add(r);
        } else {
            moneyAlert();
        }
    }


    private void updateScreen(Scene scene) {
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void winGame() {
        WinScreen win = new WinScreen(WIDTH, HEIGHT);
        Scene winscreen = win.getScene();

        Button replayButton = win.getReplayButton();

        replayButton.setOnAction( e -> {
            player.setCounter(0);
            counter = 0;
            gotoFirstScreen();
        });

        Button quitButt = win.getQuitButton();
        quitButt.setOnAction(e -> {
            mainWindow.close();
        });
        mainWindow.setScene(winscreen);
        mainWindow.setResizable(false);
        mainWindow.show();

    }

    public static void endGame() {
        EndScreen end = new EndScreen(WIDTH, HEIGHT);
        Button quitButton = end.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());
        Button playButton = end.getPlayButton();
        playButton.setOnAction(e -> {
            counter = 0;
            player.setCounter(0);
            gotoFirstScreen();
        });

        Scene ending = end.getScene();
        mainWindow.setScene(ending);
        mainWindow.setResizable(false);
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }

    public void displayPosition(MouseEvent event) {
        status.setText("X = " + event.getX() + " Y = " + event.getY());
    }


    public static void clearCS() {
        cs.clear();
    }

    public static int getCounter() {
        return counter;
    }

    private static void incrementCounter() {
        counter++;
    }

    public static int getTotalKills() {
        return totalKills;
    }

    public static int getTotalDamage() {
        return totalDamage;
    }

}
