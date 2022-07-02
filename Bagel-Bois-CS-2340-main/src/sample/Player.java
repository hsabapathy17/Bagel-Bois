package sample;

public class Player {

    private int health;
    private int money;
    private String name;
    private int counter;


    public Player(int health, int money, String name) {
        this.health = health;
        this.money = money;
        this.name = name;
        this.counter = 0;

    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getHealth() {
        return health;
    }

    public int getCounter() {
        return counter;
    }


    public void setHealth(int health) {
        this.health = health;

    }
    public int getRewarded(int reward) {
        money += reward;
        return money;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int easyHealth() {
        return 60;
    }

    public int medHealth() {
        return 40;
    }

    public int hardHealth() {
        return 20;
    }

    public void incrementCounter() {
        counter++;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void endGame() {
        Controller.endGame();
    }
}
