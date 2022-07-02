package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Controller;
import sample.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WinScreen {

    private int width;
    private int height;
    private Button replayButton;
    private Label totalKills;
    private Label totalDamage;
    private Label round;
    private VBox vbox;
    private Button quitButton;

    public WinScreen(int width, int height) {
        this.width = width;
        this.height = height;
        replayButton = new Button("Replay");
        quitButton = new Button("Quit");

    }

    public Scene getScene() {
        Group root = new Group();
        ImageView splashView = new ImageView();
        try {
            Image splash = new Image(new FileInputStream(
                    "src/resources/win-screen.gif"));
            splashView = new ImageView(splash);

        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }
        root.getChildren().add(splashView);
        replayButton.setMinWidth(200);
        replayButton.setMinHeight(65);
        replayButton.setPadding((new Insets(10)));
        replayButton.setLayoutX(860);
        replayButton.setLayoutY(698);

        quitButton.setMinWidth(200);
        quitButton.setMinHeight(65);
        quitButton.setPadding((new Insets(10)));
        quitButton.setLayoutX(860);
        quitButton.setLayoutY(772);
        root.getChildren().add(quitButton);
        root.getChildren().add(replayButton);



        int totKills = Controller.getTotalKills();
        totalKills = new Label("Kills: " + totKills);
        totalKills.setTextFill(Color.WHITE);
        totalKills.setFont(new Font("Helvetica", 60));
        totalKills.setLayoutX(855);
        totalKills.setLayoutY(410);
        root.getChildren().add(totalKills);

        int totDamage = Controller.getTotalDamage();
        totalDamage = new Label("Damage: " + totDamage);
        totalDamage.setFont(new Font("Helvetica", 60));
        totalDamage.setTextFill(Color.WHITE);
        totalDamage.setLayoutY(510);
        totalDamage.setLayoutX(788);
        root.getChildren().add(totalDamage);

        int rounds = Controller.getCounter();
        round = new Label("Rounds: " + rounds);
        round.setFont(new Font("Helvetica", 60));
        round.setTextFill(Color.WHITE);
        round.setLayoutX(826);
        round.setLayoutY(610);
        root.getChildren().add(round);



        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        return scene;
    }

    public Button getReplayButton() {
        return replayButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }
}
