package sample;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import view.Difficulty;
import view.MapScreen;
import view.Towers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameController {
    // general game stuff
    private Player player;
    private MyTimer timer; // timer that manages enemies
    private static MapScreen map; // the map that we influence
    private Difficulty diff;
    private Controller controller = new Controller();
    

    // entities we track
    private Stack<Enemy> enemyStack1; // animation list
    private CopyOnWriteArrayList<Enemy> enemyQ; // queue for game
    private static ArrayList<Tower> towers;            // towers we have made

    // dynamic game attributes
    private int numEnemies;
    private int lastIdNum = 0; // will be necessary for creating enemy ID

    // For collision
    private Rectangle house = new Rectangle(1774, 145, 133, 114);

    // things we know that we will have to create for the game every time
    public GameController(MapScreen map, Difficulty diff, Player player) {
        enemyStack1 = new Stack<>();
        enemyQ = new CopyOnWriteArrayList<Enemy>();
        this.map = map;
        this.diff = diff;
        this.player = player;
    }

    public void spawn(Shape polyline, int numEnemies) {

        for (int i = 0; i < numEnemies; i++) { // actually creating said enemies
            Circle r = new Circle();
            r.setCenterX(0);
            r.setCenterY(440);
            r.setRadius(25);
            if (i < 1) {
                try {
                    Image img = new Image(new FileInputStream("src/resources/cats/cat3-jump.gif"));
                    ImagePattern pattern = new ImagePattern(img);
                    r.setFill(pattern);

                } catch (Exception exception) {
                    System.out.println("It didn't work");
                }
            }
            if (i >= 1 && i < numEnemies-3) {
                try {
                    Image img = new Image(new FileInputStream("src/resources/cats/cat1-jump.gif"));
                    ImagePattern pattern = new ImagePattern(img);
                    r.setFill(pattern);

                } catch (Exception exception) {
                    System.out.println("It didn't work");
                }
            }
            if(i >= numEnemies-3){
                try {
                    Image img = new Image(new FileInputStream("src/resources/cats/cat2-jump.gif"));
                    ImagePattern pattern = new ImagePattern(img);
                    r.setFill(pattern);

                } catch (Exception exception) {
                    System.out.println("It didn't work");
                }
            }

            enemyStack1.push(new Enemy(r, diff, lastIdNum));
            lastIdNum++;

        }

        timer = new MyTimer() {

            @Override
            public void handle(long l) {
                long dt = l - this.getPrevTime();
                long tt = l - this.getPrevTowerTime();

                if (dt > .25e9 && !enemyStack1.isEmpty() && player.getHealth() != 0) {
                    this.setPrevTime(l);
                    PathTransition transition = new PathTransition();
                    Enemy test = enemyStack1.pop();
                    Group root = map.getRoot();
                    root.getChildren().add(test.getC());
                    //System.out.println("ID: " + test.getC().getId());
                    enemyQ.add(test);

                    transition.setNode(test.getC());
                    transition.setDuration(Duration.seconds(10));
                    transition.setPath(polyline);
                    transition.setCycleCount(1);
                    transition.play();

                }
                // puts the enemy in the tower's purview
                //System.out.println("queue size: " + enemy_q.size());
                if (enemyQ.size() > 0 && player.getHealth() != 0) {
                    map.checkCollisionMon(enemyQ, enemyQ.get(0).getC(), house);
                    if (tt > 500000000) { // checking every half second
                        this.setPrevTowerTime(l);
                        //System.out.println(l/1000000000);
                        int enIndex = 0;
                        for (; enIndex < enemyQ.size(); enIndex++) {
                            // trying to get multiple enemies to work
                            Enemy e = enemyQ.get(enIndex);
                            for (Tower t : towers) {
                                if (!t.getIsAttacking() || e.getId().equals(t.getTargeting())) {
                                    // temporary case for one tower and enemy:
                                    //enemy_q.getFirst().move()
                                    int prev = enemyQ.size();
                                    enemyQ = map.checkCollisionTower(
                                            enemyQ, e, e.getC(), t, enIndex);
                                    if (enemyQ.size() < prev) {
                                        enIndex--;
                                    }
                                }
                            }
                            enIndex++;
                        }

                    }

                }
                if (enemyQ.size() == 0 && player.getHealth() != 0) {
                    map.showMenu();
                    if(player.getCounter() > 4) {
                        map.showBoss();
                    }
                    this.stop();
                }
                if (map.getLivesLabel().getText().equals("Lives: 0")) {
                    this.stop();
                }

            }
        };
        timer.start();
    }

    public void spawnBoss(Shape polyline) {

        Circle r = new Circle();
        r.setCenterX(0);
        r.setCenterY(440);
        r.setRadius(40);

        try {
            Image img = new Image(new FileInputStream("src/resources/cats/boss-cat.png"));
            ImagePattern pattern = new ImagePattern(img);
            r.setFill(pattern);

        } catch (Exception exception) {
            System.out.println("It didn't work");
        }

        enemyStack1.push(new Enemy(r, Difficulty.BOSS, lastIdNum));
        lastIdNum++;


        timer = new MyTimer() {

            @Override
            public void handle(long l) {
                long dt = l - this.getPrevTime();
                long tt = l - this.getPrevTowerTime();

                if (dt > .25e9 && !enemyStack1.isEmpty() && player.getHealth() != 0) {
                    this.setPrevTime(l);
                    PathTransition transition = new PathTransition();
                    Enemy test = enemyStack1.pop();
                    Group root = map.getRoot();
                    root.getChildren().add(test.getC());
                    //System.out.println("ID: " + test.getC().getId());
                    enemyQ.add(test);

                    transition.setNode(test.getC());
                    transition.setDuration(Duration.seconds(10));
                    transition.setPath(polyline);
                    transition.setCycleCount(1);
                    transition.play();

                }
                // puts the enemy in the tower's purview
                //System.out.println("queue size: " + enemy_q.size());
                if (enemyQ.size() > 0 && player.getHealth() != 0) {
                    map.checkBossCollisionMon(enemyQ, enemyQ.get(0).getC(), house);
                    if (tt > 500000000) { // checking every half second
                        this.setPrevTowerTime(l);
                        //System.out.println(l/1000000000);
                        int enIndex = 0;
                        for (; enIndex < enemyQ.size(); enIndex++) {
                            // trying to get multiple enemies to work
                            Enemy e = enemyQ.get(enIndex);
                            for (Tower t : towers) {
                                if (!t.getIsAttacking() || e.getId().equals(t.getTargeting())) {
                                    // temporary case for one tower and enemy:
                                    //enemy_q.getFirst().move()
                                    int prev = enemyQ.size();
                                    enemyQ = map.checkCollisionTower(
                                            enemyQ, e, e.getC(), t, enIndex);
                                    if (enemyQ.size() < prev) {
                                        enIndex--;
                                    }
                                }
                            }
                            enIndex++;
                        }

                    }

                }
                if (enemyQ.size() == 0 && player.getHealth() != 0) {
                    controller.winGame();
                    this.stop();
                }
                if (map.getLivesLabel().getText().equals("Lives: 0")) {
                    this.stop();
                }

            }
        };
        timer.start();
    }

    public static void addTower(Towers type, Circle c, String id) {
        Tower t = new Tower(type, c, id);
        towers.add(t);
    }


    public static ArrayList<Circle> setRanges() {
        // we will return this to set the range circles in the actual screen
        System.out.println("setting ranges");
        ArrayList<Circle> rangeCircles = new ArrayList<Circle>();
        for (int index = 0; index < towers.size(); index++) {
            if (towers.get(index).getRange() == null) {
                towers.get(index).setRange();
            }
            updateRange(towers.get(index));
            Circle range = towers.get(index).getRange();
            rangeCircles.add(range);
        }
        return rangeCircles;
    }

    public static void updateRange(Tower t) {
        map.removeRange(t.getRange());
        t.setRange();
    }

    public static void updatePosition(Circle s, int idNum) {
        // System.out.println("passed in updated pos: " + s.getCenterX());
        for (Tower t : towers) {
            String id = "t" + idNum;
            if (id.equals(t.getId())) {
                t.move(s);
                updateRange(t);
            }
        }
    }

    public static void reset() {
        towers = new ArrayList<>();
    }

    public static ArrayList<Tower> getTowers() {
        return towers;
    }
}
