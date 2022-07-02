package view;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

// for images
import javafx.scene.control.Alert;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class FirstScreen {

    private int width;
    private int height;
    private Button playButton;
    private Button quitButton;

    public FirstScreen(int width, int height) {
        this.width = width;
        this.height = height;
        playButton = new Button("Start Game");
        quitButton = new Button("Come Back Later");
    }

    public Scene getScene() {
        //Stacking allows for overlay of button onto image background
        StackPane root = new StackPane();
        BorderPane back = new BorderPane();

        playButton.setMinWidth(200);
        playButton.setMinHeight(65);
        playButton.setPadding((new Insets(10)));

        // The button
        VBox butt = new VBox();
        Label emptyLabel = new Label();
        butt.setAlignment(Pos.CENTER);
        //Label label = new Label("Begin");
        butt.getChildren().addAll(playButton, quitButton);
        butt.setSpacing(20);

        // Images are buggy - need to make sure we can still run without it
        ImageView imageView = new ImageView();
        try {
            Image image = new Image(new FileInputStream(
                    "src/resources/start-screen-with-name.gif"));
            imageView = new ImageView(image);
        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }

        // final setup - ready to return
        root.getChildren().addAll(imageView, butt);
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        return scene;
    }


    public Button getQuitButton() {
        return quitButton;
    }

    public Button getPlayButton() {
        return playButton;
    }
}
