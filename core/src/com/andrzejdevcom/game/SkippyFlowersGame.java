package com.andrzejdevcom.game;

import com.andrzejdevcom.game.assets.AssetDescriptors;
import com.andrzejdevcom.game.common.ScoreController;
import com.andrzejdevcom.game.screen.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkippyFlowersGame extends Game {

    private ScoreController scoreController;
    private AssetManager assetManager;
    private SpriteBatch batch;

    @Override
    public void create() {
        scoreController = new ScoreController();
        assetManager = new AssetManager();
        batch = new SpriteBatch();
        assetManager.load(AssetDescriptors.SCORE_FONT);

        assetManager.finishLoading();
        setScreen(new GameScreen(this));
    }

    public ScoreController getScoreController() {
        return scoreController;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
        batch.dispose();
    }
}
