package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import view.Towers;

public class Tower {

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    private boolean isAttacking = false;
    private Circle c;
    private Circle range;
    private Towers type;

    private String id;

    // tower game dynamic properties
    private int baseDamage = 1;
    private String targeting = "0"; // "0" means it is targetting nothing
    private long rateOfAttack; // what we will divid the

    // dynamic properties determined by class
    private int damage;

    public int getDamage() {
        return damage;
    }

    public Tower(Towers type, Circle c, String id) {
        this.type = type;
        this.c = c;
        this.id = id;
        switch (type) {
        case RED:
            damage = baseDamage * 3;
            break;

        case YELLOW:
            damage = baseDamage * 2;
            break;

        case GREEN:
            damage = baseDamage * 1;
            break;
        default:
            damage = baseDamage * 1;
            break;
        }
    }

    public void move(Circle cUpdated) {

        System.out.println(cUpdated.getCenterX());
        c.setCenterX(cUpdated.getCenterX());
        c.setCenterY(cUpdated.getCenterY());

        System.out.println("moved circle: " + c.getCenterX());
    }

    public Circle setRange() {
        //System.out.println("tower circle: " + c.getCenterX());
        range = new Circle();
        range.setCenterX(c.getCenterX());
        range.setCenterY(c.getCenterY());
        range.setRadius(c.getRadius() * 5);
        range.setStroke(Color.BLACK);
        range.setStrokeWidth(5);
        range.setFill(null);
        //System.out.println("range circle in set range: " + range.getCenterX());
        return range;
    }

    public String getId() {
        return id;
    }

    public Circle getC() {
        return c;
    }

    public boolean getIsAttacking() {
        return isAttacking;
    }

    public void setIsAttacking(boolean b, String enId) {
        isAttacking = b;
        targeting = enId;
    }

    public Circle getRange() {
        return range;
    }

    public String getTargeting() {
        return targeting;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
