package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class GameScreen {

    private int width;
    private int height;
    private Difficulty difficulty;
    private String name;
    private int enemyNumber;

    public GameScreen(int width, int height, Difficulty difficulty, String name, int enemyNumber) {
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
    }

    public Scene getScene() {
        Label label1 = new Label("Welcome to the game");
        label1.setAlignment(Pos.CENTER);
        Scene scene = new Scene(label1);
        return scene;
    }

}
