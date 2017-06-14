package com.andrzejdevcom.game.entity;

import com.andrzejdevcom.game.config.GameConfig;
import com.badlogic.gdx.math.Circle;

/**
 * Created by Andrzej on 2017-06-14.
 */

public class Skippy {

    private final Circle collistionCircle;
    private float x;
    private float y;
    private float ySpeed;

    public Skippy() {
        collistionCircle = new Circle(x, y, GameConfig.SKIPPY_HALF_SIZE);
    }

    public Circle getCollistionCircle() {
        return collistionCircle;
    }

    public void update(float dt) {
        ySpeed -= GameConfig.DIVE_ACC * dt;
        setY(y + ySpeed);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateCollisionCircle();
    }

    public void flyUp() {
        ySpeed = GameConfig.FLY_ACC;
        setY(y + ySpeed);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        updateCollisionCircle();
    }

    private void updateCollisionCircle() {
        collistionCircle.setX(x);
        collistionCircle.setY(y);
    }
}
