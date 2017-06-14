package com.andrzejdevcom.game.screen;

import com.andrzejdevcom.game.config.GameConfig;
import com.andrzejdevcom.game.entity.Flower;
import com.andrzejdevcom.game.entity.Skippy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andrzej on 2017-06-14.
 */

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer rendered;

    private Skippy skippe;
    private Array<Flower> flowers = new Array<Flower>();

    public GameScreen() {
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        rendered = new ShapeRenderer();

        skippe = new Skippy();
        skippe.setPosition(GameConfig.WORLD_WIDTH / 4f, GameConfig.WORLD_HEIGHT / 2);
        createNewFlower();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        viewport.apply();
        renderDebug();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        rendered.dispose();
    }

    private void update(float delta) {
        skippe.update(delta);
        if (Gdx.input.justTouched()) {
            skippe.flyUp();
        }
        blockSkippyFromLeavingTheWorld();

        for (Flower flower : flowers) {
            flower.update(delta);
        }
        spawnFlower();
        removePassedFlowers();
    }

    private void removePassedFlowers() {
        if (flowers.size > 0) {
            Flower firstFlower = flowers.first();
            if (firstFlower.getX() < -Flower.WIDTH) {
                flowers.removeValue(firstFlower, true);
            }
        }
    }

    private void createNewFlower() {
        Flower flower = new Flower();
        flower.setX(GameConfig.WORLD_WIDTH - Flower.WIDTH);
        flowers.add(flower);
    }

    private void spawnFlower() {
        if (flowers.size == 0) {
            createNewFlower();
        } else {
            Flower lastFlower = flowers.peek();
            if (lastFlower.getX() < GameConfig.WORLD_WIDTH - GameConfig.GAP_BETWEEN_FLOWERS) ;
        }
    }

    private void blockSkippyFromLeavingTheWorld() {
        float newY = MathUtils.clamp(skippe.getY(), 0, GameConfig.WORLD_HEIGHT);
        skippe.setY(newY);
    }

    private void renderDebug() {
        rendered.setProjectionMatrix(camera.combined);
        rendered.begin(ShapeRenderer.ShapeType.Line);
        drawDebug();
        rendered.end();
    }

    private void drawDebug() {
        Circle skippyCollisionsCircle = skippe.getCollistionCircle();
        rendered.circle(skippyCollisionsCircle.x, skippyCollisionsCircle.y,
                skippyCollisionsCircle.radius, 30);
        for (Flower flower : flowers) {
            Circle flowerCollisionCircle = flower.getCollistionCircle();
            Rectangle flowerCollisionRectangle = flower.getCollisionRect();
            rendered.circle(flowerCollisionCircle.x, flowerCollisionCircle.y, flowerCollisionCircle.radius, 30);
            rendered.rect(flowerCollisionRectangle.x, flowerCollisionRectangle.y,
                    flowerCollisionRectangle.width, flowerCollisionRectangle.height);
        }
    }
}