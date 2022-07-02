package view;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import sample.*;
import javafx.scene.image.Image;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapScreen {

    private int width;
    private int height;
    private Player player;
    private Label label;
    private Color path = Color.rgb(255, 255, 255);
    private Color grass = Color.rgb(53, 198, 94);
    private Color water = Color.rgb(40, 144, 255);
    private ComboBox towerSelect;
    private ArrayList<Shape> nodes;
    private Button button;
    private Button upgrade;
    private Group root;
    private boolean collisionDetected;
    private Button reset;
    private Button startGame;
    private Label livesLabel;
    private Label moneyLabel;
    private Rectangle house;
    private Button finalBoss;




    public MapScreen(int width, int height, Player player) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.towerSelect = new ComboBox();
        this.button = new Button("Confirm");
        this.reset = new Button("Reset");
        this.upgrade = new Button("Upgrade");
        this.startGame = new Button("Start Round");
        label = new Label("the map");
        this.finalBoss = new Button("Final Boss");
        this.house = new Rectangle(1774, 145, 133, 114);
    }

    public Scene getScene() {
        root = new Group();

        try {
        Image image = new Image(new FileInputStream("src/resources/screen-no-lakes.png"));
        ImageView img = new ImageView(image);
        root.getChildren().add(img);
        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }

        Scene scene = new Scene(root, width, height);
        nodes = new ArrayList<>();

        ArrayList<Rectangle> pathOfMobs = createPath();
        createOther();
        createMenu();
        createPlayerInfo();

        //Deals with not being able to place the Towers on the Path or in the water as of right now.
        checkShapeIntersection(nodes.get(nodes.size() - 1));

        return scene;
    }

    // helper creating path
    private ArrayList<Rectangle> createPath() {
        //Creates Path in the Map
        ArrayList<Rectangle> pathOfMobs = new ArrayList<>();
        pathOfMobs.add(new Rectangle(0, 418, 250, 52));
        pathOfMobs.add(new Rectangle(193, 299, 57, 171));
        pathOfMobs.add(new Rectangle(193, 280, 250, 52));
        pathOfMobs.add(new Rectangle(390, 280, 57, 301));
        pathOfMobs.add(new Rectangle(197, 529, 250, 52));
        pathOfMobs.add(new Rectangle(197, 529, 57, 301));
        pathOfMobs.add(new Rectangle(199, 778, 465, 52));
        pathOfMobs.add(new Rectangle(607, 187, 57, 643));
        pathOfMobs.add(new Rectangle(419, 187, 241, 52));
        pathOfMobs.add(new Rectangle(415, 68, 57, 171));
        pathOfMobs.add(new Rectangle(413, 68, 605, 52));
        pathOfMobs.add(new Rectangle(963, 68, 57, 301));
        pathOfMobs.add(new Rectangle(770, 317, 250, 52));
        pathOfMobs.add(new Rectangle(770, 317, 57, 202));
        pathOfMobs.add(new Rectangle(770, 467, 250, 52));
        pathOfMobs.add(new Rectangle(963, 467, 57, 202));
        pathOfMobs.add(new Rectangle(963, 617, 317, 52));
        pathOfMobs.add(new Rectangle(1223, 617, 57, 202));
        pathOfMobs.add(new Rectangle(1223, 767, 229, 52));
        pathOfMobs.add(new Rectangle(1452, 176, 332, 52));
        pathOfMobs.add(new Rectangle(1452, 176, 57, 643));

        for (Rectangle r : pathOfMobs) {
            r.setFill(path);
            root.getChildren().add(r);
        }

        //To add the path to the collisions guide
        for (Shape shape : pathOfMobs) {
            nodes.add(shape);
        }

        return pathOfMobs;
    }

    // helper creating other map scenery
    private void createOther() {
        //Creates the Lakes on the Map
        try {
            Image image = new Image(new FileInputStream("src/resources/lake.png"));
            ImagePattern img = new ImagePattern(image);
            Circle lake = new Circle(818, 730, 100);
            lake.setFill(img);
            Circle bigLake = new Circle(1235, 423, 146);
            bigLake.setFill(img);

            root.getChildren().addAll(lake, bigLake);
        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }
        //Builds the House
        try {
            Image image = new Image(new FileInputStream("src/resources/monument.png"));
            ImagePattern img = new ImagePattern(image);
            house.setFill(img);
        } catch (FileNotFoundException fnfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("File Not Found");
            a.setContentText("Please put a correct file.");
            a.showAndWait();
        }
        root.getChildren().add(house);
        Polygon roof = new Polygon(1774, 145, 133, 113);

        nodes.add(house);
        nodes.add(roof);
    }

    private void createMenu() {
        //Builds ComboBox for the Towers
        Label label2 = new Label("Tower Select:");
        label2.setFont(new Font(25));
        label2.setTextFill(Color.WHITE);
        towerSelect.getItems().add("RED - Shoots at a Slow Speed, Does High Damage");
        towerSelect.getItems().add("YELLOW - Shoots at a Medium Speed, Does Medium Damage");
        towerSelect.getItems().add("GREEN - Shoots at a High Speed, Does Low Damage");
        towerSelect.setPrefWidth(150);
        towerSelect.setLayoutX(1567);
        towerSelect.setLayoutY(56);
        towerSelect.setPromptText("Select Tower");
        towerSelect.setVisibleRowCount(3);
        root.getChildren().add(towerSelect);



        //Adds confirmation for the tower select and creates the tower
        button.setLayoutX(1717);
        button.setLayoutY(56);
        button.setPrefWidth(70);
        root.getChildren().add(button);

        //startGame button
        startGame.setLayoutX(1804);
        startGame.setLayoutY(56);
        root.getChildren().add(startGame);

        //reset towers button
        reset.setLayoutY(88);
        reset.setLayoutX(1717);
        reset.setPrefWidth(70);
        root.getChildren().add(reset);

        //upgrade towers button
        upgrade.setLayoutX(1717);
        upgrade.setLayoutY(120);
        upgrade.setPrefWidth(70);
        root.getChildren().add(upgrade);

        //finalBoss button
        finalBoss.setLayoutX(1804);
        finalBoss.setLayoutY(88);
        finalBoss.setPrefWidth(70);
        finalBoss.setVisible(false);
        root.getChildren().add(finalBoss);
    }

    private void createPlayerInfo() {
        int lives = player.getHealth();
        int money = player.getMoney();

        livesLabel = new Label();
        livesLabel.setText("Lives: " + lives);
        moneyLabel = new Label();
        moneyLabel.setText("Money: $" + money);
        VBox stats = new VBox(livesLabel, moneyLabel);
        livesLabel.setFont(new Font("Helvetica", 32));
        moneyLabel.setFont(new Font("Helvetica", 32));
        stats.setLayoutX(1650);
        stats.setLayoutY(704);
        root.getChildren().add(stats);
    }

    public void setDragListeners(Circle block, int idNum) {
        final Delta dragDelta = new Delta();

        block.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = block.getCenterX() - mouseEvent.getSceneX();
                dragDelta.y = block.getCenterY() - mouseEvent.getSceneY();
                //block.setCursor(Cursor.NONE);
                checkShapeIntersection(block);
                //System.out.println("clicked: " + block.getCenterX());
            }
        });
        block.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                block.setCursor(Cursor.HAND);
                checkShapeIntersection(block);
                System.out.println("FINAL: " + block.getCenterX());
            }
        });
        block.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                block.setCenterX(mouseEvent.getSceneX() + dragDelta.x);
                block.setCenterY(mouseEvent.getSceneY() +  dragDelta.y);
                checkShapeIntersection(block);
                GameController.updatePosition(block, idNum);
                //System.out.println("block updated: " + block.getCenterX()); // debug
            }
        });
    }


    public boolean checkShapeIntersection(Shape block) {
        collisionDetected = false;
        //Paint original = (ImagePattern) block.getFill();
        //block.setFill(original);
        for (Shape staticBlock : nodes) {
            Shape intersect = Shape.intersect(block, staticBlock);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                collisionDetected = true;
            }
        }
        return collisionDetected;
    }

    public int checkIntersectWithMonument(Rectangle house, Circle enemy, int lives, int damage) {
        Shape intersect = Shape.intersect(enemy, house);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            lives = lives - damage;
        }
        return lives;

    }

    public void checkCollisionMon(CopyOnWriteArrayList q, Circle shape1, Shape shape2) {
        //Circle test = (Circle) q.getFirst();
        if (shape1.getBoundsInParent().intersects(shape2.getBoundsInParent())) {
            System.out.println("collision with monument");
            q.remove(0);
            root.getChildren().remove(shape1);


            player.setHealth(player.getHealth() - 1);

            livesLabel.setText("Lives: " + player.getHealth());
            if (getLivesLabel().getText().equals("Lives: 0")) {
                Controller.endGame();

            }

        }

    }

    public void checkBossCollisionMon(CopyOnWriteArrayList q, Circle shape1, Shape shape2) {
        //Circle test = (Circle) q.getFirst();
        if (shape1.getBoundsInParent().intersects(shape2.getBoundsInParent())) {
            System.out.println("collision with monument");
            q.remove(0);
            root.getChildren().remove(shape1);


            player.setHealth(0);

            livesLabel.setText("Lives: " + player.getHealth());
            if (player.getHealth() <= 0) {
                Controller.endGame();

            }

        }

    }
    public void removeRange(Circle r) {
        root.getChildren().remove(r);
    }

    public CopyOnWriteArrayList<Enemy> checkCollisionTower(
            CopyOnWriteArrayList<Enemy> q, Enemy enemy, Circle ec, Tower tower, int index) {
        Circle tc = tower.getRange(); // range of tower circle
        boolean collision = ec.getBoundsInParent().intersects(tc.getBoundsInParent());
        System.out.println("Tower " + tower.getId()
                + " enemy: " + enemy.getId() + " at index: " + index + " collision: " + collision);
        //Circle ec = enemy.getC(); // enemy circle

        if (ec.getBoundsInParent().intersects(tc.getBoundsInParent())) {
            tower.setIsAttacking(true, enemy.getId());
            Double opacity = enemy.attacked(tower.getDamage()); // true means that enemy is dead
            boolean dead = enemy.toDelete();

            System.out.println(opacity);
            ec.setFill(Color.rgb(1, 1, 1, opacity));

            System.out.println("Enemy Health after attack: " + enemy.getHealth());
            if (dead) {
                System.out.println("Enemy: " + enemy.getId() + " is removed");
                root.getChildren().remove(ec); // should be 1 in general
                q.remove(index);
                player.getRewarded(enemy.getReward());

                updateMoneyLabel(player);
                tower.setIsAttacking(false, "0");
            }
        } else {
            if (tower.getTargeting().equals(enemy.getId())) {
                // means that they are no longer colliding but the
                tower.setIsAttacking(false, "0");
                System.out.println("tower no longer targeting");
            }
        }
        return q;
    }

    public void showBoss() {
        finalBoss.setVisible(true);
    }

    public void hideMenu() {
        startGame.setVisible(false);
        button.setVisible(false);
        reset.setVisible(false);
        towerSelect.setVisible(false);
        moneyLabel.setVisible(false);
        upgrade.setVisible(false);
    }

    public void showMenu() {
        startGame.setVisible(true);
        button.setVisible(true);
        reset.setVisible(true);
        towerSelect.setVisible(true);
        moneyLabel.setVisible(true);
        upgrade.setVisible(true);
    }

    public void hideBoss() {
        finalBoss.setVisible(false);
    }

    // The land of getters
    public Label getLivesLabel() {
        return livesLabel;
    }

    public boolean getCollisionDetected() {
        return collisionDetected;
    }

    public Button getReset() {
        return reset;
    }

    public Button getStartGame() {
        return startGame;
    }

    public Button getButton() {
        return button;
    }

    public Button getUpgrade() {
        return upgrade;
    }

    public ComboBox getTowerSelect() {
        return towerSelect;
    }

    public Group getRoot() {
        return root;
    }

    public void setCollisionDetected(boolean collisionDetected) {
        this.collisionDetected = collisionDetected;
    }


    public void updateMoneyLabel(Player player) {
        moneyLabel.setText("Money: $" + player.getMoney());

    }

    public Button getFinalBoss() {
        return finalBoss;
    }

    class Delta {

        private double x;
        private double y;
    }
}
