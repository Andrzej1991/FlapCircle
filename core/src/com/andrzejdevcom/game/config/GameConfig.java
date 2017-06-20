package com.andrzejdevcom.game.config;

import com.badlogic.gdx.Gdx;

/**
 * Created by Andrzej on 2017-06-14.
 */

public class GameConfig {

    public static final float WIDTH = Gdx.graphics.getWidth();
    public static final float HEIGHT = Gdx.graphics.getHeight();
    public static final float WORLD_WIDTH = 30f;
    public static final float WORLD_HEIGHT = 40f;
    public static final float SKIPPY_SIZE = 3f;
    public static final float SKIPPY_HALF_SIZE = SKIPPY_SIZE / 2f;
    public static final float FLY_ACC = 0.3f;
    public static final float DIVE_ACC = 0.5f;
    public static final float GAP_BETWEEN_FLOWERS = 8f;
    public static final float HUD_WIDTH = 600f;
    public static final float HUD_HEOGHT = 800f;

    private GameConfig() {
    }
}
