package com.andrzejdevcom.flappycircle.entity;

import com.andrzejdevcom.flappycircle.config.GameConfig;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Andrzej on 2017-06-14.
 */

public class Flower {

    private static final float COLLISION_RECT_WIDTH = 0.5f;
    private static final float COLLISION_RECT_HALF_WIDTH = COLLISION_RECT_WIDTH / 2f;
    private static final float COLLISTION_RECT_HEIGHT = 25f;
    private static final float COLLLISTION_CIRCLER_RADIUS = 1.5f;
    private static final float MAX_SPEED = 8f;
    public static final float WIDTH = COLLLISTION_CIRCLER_RADIUS * 2;
    public static final float HEIGHT = COLLISTION_RECT_HEIGHT;
    private static final float HEIGHT_OFFSET = -20f;
    private static final float FLOWER_GAP = 12f;
    private static final float SENSOR_RECT_WIDTH = 0.05f;


    private final Circle bottomcollistionCircle;
    private final Rectangle bottomCollisionRect;
    private final Circle topCollistionCircle;
    private final Rectangle topCollisionRect;
    private final Rectangle sensorRectangle;

    private float x;
    private float y;

    private boolean scoreCollected;

    public Flower() {
        y = MathUtils.random(HEIGHT_OFFSET);
        bottomCollisionRect = new Rectangle(x, y, COLLISION_RECT_WIDTH,
                COLLISTION_RECT_HEIGHT);
        bottomcollistionCircle = new Circle(x + COLLISION_RECT_HALF_WIDTH,
                y + COLLISTION_RECT_HEIGHT,
                COLLLISTION_CIRCLER_RADIUS);
        float topY = bottomcollistionCircle.y + FLOWER_GAP;
        topCollisionRect = new Rectangle(x, topY, COLLISION_RECT_WIDTH, COLLISTION_RECT_HEIGHT);
        topCollistionCircle = new Circle(x + COLLISION_RECT_HALF_WIDTH, topY,
                COLLLISTION_CIRCLER_RADIUS);
        sensorRectangle = new Rectangle(x + COLLISION_RECT_HALF_WIDTH, 0, SENSOR_RECT_WIDTH,
                GameConfig.WORLD_HEIGHT);
    }

    public void update(float dt) {
        float xSpeed = MAX_SPEED * dt;
        setX(x - xSpeed);
    }

    public Circle getTopCollistionCircle() {
        return topCollistionCircle;
    }

    public Rectangle getTopCollisionRect() {
        return topCollisionRect;
    }

    public void setX(float x) {
        this.x = x;
        updateCollisionCircle();
        updateCollisionRectangle();
        updateSensorRectangle();
    }

    public float getX() {
        return x;
    }

    public boolean isSkippyColliding(Skippy skippy) {
        return overlapsTopFlower(skippy) || overlapsBottomFlower(skippy);
    }

    public Rectangle getSensorRectangle() {
        return sensorRectangle;
    }

    public boolean isSkippyCollidingWithSensor(Skippy skippy) {
        return Intersector.overlaps(skippy.getCollistionCircle(), sensorRectangle);
    }

    public void collectScore() {
        scoreCollected = true;
    }

    public boolean isScoreCollected() {
        return scoreCollected;
    }

    private boolean overlapsBottomFlower(Skippy skippy) {
        Circle skippyCollisionCircle = skippy.getCollistionCircle();
        return Intersector.overlaps(skippyCollisionCircle, bottomcollistionCircle) ||
                Intersector.overlaps(skippyCollisionCircle, bottomCollisionRect);
    }

    private boolean overlapsTopFlower(Skippy skippy) {
        Circle skippyCollisionCircle = skippy.getCollistionCircle();
        return Intersector.overlaps(skippyCollisionCircle, topCollistionCircle) ||
                Intersector.overlaps(skippyCollisionCircle, getTopCollisionRect());
    }

    private void updateSensorRectangle() {
        float newX = x + COLLISION_RECT_HALF_WIDTH;
        sensorRectangle.setX(newX);
    }


    public Circle getBottomcollistionCircle() {
        return bottomcollistionCircle;
    }

    public Rectangle getBottomCollisionRect() {
        return bottomCollisionRect;
    }

    private void updateCollisionCircle() {
        bottomcollistionCircle.setX(x + COLLISION_RECT_HALF_WIDTH);
        topCollistionCircle.setX(x + COLLISION_RECT_HALF_WIDTH);
    }

    private void updateCollisionRectangle() {
        bottomCollisionRect.setX(x);
        topCollisionRect.setX(x);
    }
}
