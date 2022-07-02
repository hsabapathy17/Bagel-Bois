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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Need for this screen is made by player, is created by controller
 */
public class EndScreen {
    private int height;
    private int width;
    private Button playAgain;
    private Button quitButton;
    private Label totalKills;
    private Label totalDamage;
    private Label round;


    public EndScreen(int width, int height) {
        this.width = width;
        this.height = height;
        playAgain = new Button("Start Over");
        quitButton = new Button("Come Back Later");
    }

    public Scene getScene() {
        //Stacking allows for overlay of button onto image background
        Group root = new Group();
        BorderPane back = new BorderPane();

        // The button
        VBox butt = new VBox();
        Label emptyLabel = new Label();
        butt.setAlignment(Pos.CENTER);
        //Label label = new Label("Begin");
        butt.getChildren().addAll(playAgain, quitButton);
        butt.setSpacing(20);

        // Images are buggy - need to make sure we can still run without it
        ImageView imageView = new ImageView();
        try {
            Image image = new Image(new FileInputStream("src/resources/lose-screen.gif"));
            imageView = new ImageView(image);
            root.getChildren().add(imageView);
        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }


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
        totalDamage.setLayoutY(520);
        totalDamage.setLayoutX(788);
        root.getChildren().add(totalDamage);

        int rounds = Controller.getCounter();
        round = new Label("Rounds: " + rounds);
        round.setFont(new Font("Helvetica", 60));
        round.setTextFill(Color.WHITE);
        round.setLayoutX(826);
        round.setLayoutY(610);
        root.getChildren().add(round);

        playAgain.setMinWidth(200);
        playAgain.setMinHeight(65);
        playAgain.setPadding((new Insets(10)));
        playAgain.setLayoutX(860);
        playAgain.setLayoutY(698);

        quitButton.setMinWidth(200);
        quitButton.setMinHeight(65);
        quitButton.setPadding((new Insets(10)));
        quitButton.setLayoutX(860);
        quitButton.setLayoutY(772);
        root.getChildren().add(quitButton);
        root.getChildren().add(playAgain);

        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        return scene;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getPlayButton() {
        return playAgain;
    }
}
