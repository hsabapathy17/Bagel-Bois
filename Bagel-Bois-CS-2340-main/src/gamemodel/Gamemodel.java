package gamemodel;

import sample.Player;
import view.Difficulty;
import view.Enemies;
import view.Towers;

import java.util.HashMap;


public class Gamemodel {

    private int state = 0;
    private final int basemoney = 400;
    private final int baseHealth = 20;
    private final int basecost = 25;
    private final int basedmg = 1;



    public int startingMoney(Difficulty diff) {
        int change = basemoney;
        if (diff.equals(Difficulty.MEDIUM)) {
            change += basemoney;
        } else if (diff.equals(Difficulty.EASY)) {
            change += basemoney * 2;
        }
        return change;
    }

    public int startingHealth(Difficulty diff) {
        int lives = baseHealth;
        if (diff.equals(Difficulty.MEDIUM)) {
            lives += baseHealth;
        } else if (diff.equals(Difficulty.EASY)) {
            lives += baseHealth * 2;
        }
        return lives;
    }

    public int towerCost(Difficulty diff, Towers tow) {

        HashMap<Enum, Integer> difficulty = new HashMap<Enum, Integer>();
        difficulty.put(Difficulty.EASY, 0);
        difficulty.put(Difficulty.MEDIUM, 25);
        difficulty.put(Difficulty.HARD, 75);
        difficulty.put(Towers.RED, 0);
        difficulty.put(Towers.YELLOW, 100);
        difficulty.put(Towers.GREEN, 200);

        int cost = basecost + difficulty.get(diff) + difficulty.get(tow);

        return cost;


    }
    public int enemyHealthDmg(Difficulty diff, Enemies enem) {
        HashMap<Enum, Integer> dmg = new HashMap<Enum, Integer>();
        dmg.put(Difficulty.EASY, 0);
        dmg.put(Difficulty.MEDIUM, 1);
        dmg.put(Difficulty.HARD, 2);
        dmg.put(Enemies.BLACK, 0);
        dmg.put(Enemies.WHITE, 1);
        dmg.put(Enemies.GOLD, 2);

        int totaldmg = basedmg + dmg.get(diff) + dmg.get(enem);

        return totaldmg;
    }

    public int buyTower(Player player, Difficulty diff, Towers tow) {
        return player.getMoney() - towerCost(diff, tow);
    }
}
