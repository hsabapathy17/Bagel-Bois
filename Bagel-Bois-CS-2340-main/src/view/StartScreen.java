package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartScreen {
    private int width;
    private int height;
    private Button continueButton;

    public StartScreen(int width, int height) {
        this.width = width;
        this.height = height;
        continueButton = new Button("Continue");
    }

    public Scene getScene() {
        Group root = new Group();
        ImageView splashView = new ImageView();
        try {
            Image splash = new Image(new FileInputStream(
                    "src/resources/intro-screen.gif"));
            splashView = new ImageView(splash);

        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }
        continueButton.setLayoutX(1750);// was 1700
        continueButton.setLayoutY(100);
        root.getChildren().addAll(splashView, continueButton);
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        return scene;
    }

    public Button getContinueButton() {
        return continueButton;
    }
}
