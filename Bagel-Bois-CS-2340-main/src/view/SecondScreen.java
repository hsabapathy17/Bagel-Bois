package view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SecondScreen {

    private int width;
    private int height;
    private Button next;
    private TextField textField;
    private ComboBox difficulty;
    private ComboBox enemyNumber;
    private int enemies;

    public SecondScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.next = new Button("Continue");
        this.textField = new TextField();
        this.difficulty = new ComboBox();
    }

    public Scene getScene() {
        StackPane rooter = new StackPane();
        Label label1 = new Label("Please Enter Your Name...");
        label1.setFont(new Font(25));
        label1.setTextFill(Color.WHITE);
        textField.setPrefWidth(350);
        textField.setMaxWidth(350);
        textField.setPromptText("Type your name");
        textField.setOnAction(e -> textField.setText(textField.getText()));

        Label label2 = new Label("Difficulty:");
        label2.setFont(new Font(25));
        label2.setTextFill(Color.WHITE);
        difficulty.setPrefWidth(350);
        difficulty.setMaxWidth(350);
        difficulty.getItems().addAll("EASY", "MEDIUM", "HARD");
        difficulty.setPromptText("Choose Difficulty");
        difficulty.setPrefWidth(800);

        Label label3 = new Label("Enemy Number:");
        label3.setFont(new Font(25));
        label3.setTextFill(Color.WHITE);
        enemyNumber = new ComboBox();
        enemyNumber.setPrefWidth(350);
        enemyNumber.setMaxWidth(350);
        enemyNumber.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 200);
        enemyNumber.setPromptText("Choose Enemy Number");
        enemyNumber.setVisibleRowCount(11);
        enemyNumber.setPrefWidth(800);

        VBox vbox = new VBox(label1, textField, label2, difficulty, label3, enemyNumber, next);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        ImageView imageView = new ImageView();
        try {
            Image image = new Image(new FileInputStream("src/resources/input-screen.gif"));
            imageView = new ImageView(image);
        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }

        rooter.getChildren().addAll(imageView, vbox);

        Scene scene = new Scene(rooter, width, height);

        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        return scene;
    }

    public Button getNext() {
        return next;
    }

    public TextField getTextField() {
        return textField;
    }

    public ComboBox getDropDown() {
        return difficulty;
    }

    public ComboBox getEnemyNumber() {
        return enemyNumber;
    }

    public int getEnemies() {
        return enemies;
    }
}
