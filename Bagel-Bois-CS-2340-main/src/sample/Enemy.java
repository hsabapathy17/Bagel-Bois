package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import view.Difficulty;

public class Enemy {
    private Circle c; // this will be what is actually displayed in MapScreen
    private Difficulty d;
    private final int basevalue = 10;
    private int reward;
    private String id;
    private int originalHealth = 1;

    // dynamic stuff with regards to how enemies are dealing and taking damage
    private int health;
    private double currentOpacity = 1;
    private int damage = 1; // damage we plan on dealing with enemy - will effect monument


    public Enemy(Circle c, Difficulty d, int num) {
        this.c = c;
        this.d = d;
        this.id = "e" + num;
        // determining qualities of enemy according to difficulty

        health = originalHealth;
        switch (d) {
        case EASY:
            health *= 1;
            reward = basevalue;
            break;

        case MEDIUM:
            health *= 2;
            originalHealth = health;
            reward = basevalue * 2;
            break;

        case HARD:
            health *= 3;
            originalHealth = health;
            reward = basevalue * 3;
            break;

        case BOSS:
            health *= 30;
            originalHealth = health;
            break;

        default:
            reward = basevalue;
        }
    }
    public void move(Circle cUpdated) {
        c.setCenterX(cUpdated.getCenterX());
        c.setCenterY(cUpdated.getCenterY());
    }

    // will return true if we need to delete
    public Double attacked(int damage) {
        System.out.println("health: " + health);
        if (toDelete()) {
            return 0.0;
        }
        health -= damage;
        currentOpacity = currentOpacity - 1.0 / originalHealth;
        c.setFill(Color.rgb(0, 0, 0, (currentOpacity)));
        return currentOpacity;
    }

    public boolean toDelete() {
        if (health <= 0 || currentOpacity <= 0) {
            // this currently does not influence the general collision with monument:
            this.damage = 0; // want to make sure that the enemy never does damage again
            return true;
        }
        return false;
    }

    public Circle getC() {
        return c;
    }
    public String getId() {
        return id;
    }
    public int getReward() {
        return reward;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int h){
        this.health = h;
    }
}
