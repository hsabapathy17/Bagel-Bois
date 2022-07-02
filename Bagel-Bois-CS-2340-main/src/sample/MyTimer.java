package sample;

import javafx.animation.AnimationTimer;

public abstract class MyTimer extends AnimationTimer {

    private long prevTime = 0;
    private long prevTowerTime = 0;
    private boolean running;

    public void start() {
        super.start();
        running = true;
    }

    public void stop() {
        super.stop();
        running = false;
    }

    public boolean getRunning() {
        return running;
    }

    public long getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(long val) {
        prevTime = val;
    }

    public long getPrevTowerTime() {
        return prevTowerTime;
    }

    public void setPrevTowerTime(long val) {
        prevTowerTime = val;
    }

}
