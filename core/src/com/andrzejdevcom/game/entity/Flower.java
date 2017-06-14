package com.andrzejdevcom.game.entity;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Andrzej on 2017-06-14.
 */

public class Flower {

    private static final float COLLISION_RECT_WIDTH = 0.5f;
    private static final float COLLISION_RECT_HALF_WIDTH = COLLISION_RECT_WIDTH / 2f;
    private static final float COLLISTION_RECT_HEIGHT = 20f;
    private static final float COLLLISTION_CIRCLER_RADIUS = 1.5f;
    private static final float MAX_SPEED = 8f;
    public static final float WIDTH = COLLLISTION_CIRCLER_RADIUS * 2;

    private final Circle collistionCircle;
    private final Rectangle collisionRect;
    private float x;
    private float y;

    public Flower() {
        collisionRect = new Rectangle(x, y, COLLISION_RECT_WIDTH,
                COLLISTION_RECT_HEIGHT);
        collistionCircle = new Circle(x + COLLISION_RECT_HALF_WIDTH,
                y + COLLISTION_RECT_HEIGHT,
                COLLLISTION_CIRCLER_RADIUS);
    }

    public void update(float dt) {
        float xSpeed = MAX_SPEED * dt;
        setX(x - xSpeed);
    }

    public void setX(float x) {
        this.x = x;
        updateCollisionCircle();
        updateCollisionRectangle();
    }

    public float getX() {
        return x;
    }

    public Circle getCollistionCircle() {
        return collistionCircle;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    private void updateCollisionCircle() {
        collistionCircle.setX(x + COLLISION_RECT_HALF_WIDTH);
        collistionCircle.setY(y + COLLISTION_RECT_HEIGHT);
    }

    private void updateCollisionRectangle() {
        collisionRect.setX(x);
    }
}
