package com.andrzejdevcom.game;

import com.andrzejdevcom.game.assets.AssetDescriptors;
import com.andrzejdevcom.game.assets.AssetPaths;
import com.andrzejdevcom.game.common.ScoreController;
import com.andrzejdevcom.game.interfaces.PlayServices;
import com.andrzejdevcom.game.screen.StartScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkippyFlowersGame extends Game {

    private ScoreController scoreController;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private AdHandler adHandler;
    boolean toggle;

    private static PlayServices playServices;

    public SkippyFlowersGame(){

    }

    SkippyFlowersGame(PlayServices playServices){
        SkippyFlowersGame.playServices = playServices;
    }

    @Override
    public void create() {
        scoreController = new ScoreController();
        assetManager = new AssetManager();
        batch = new SpriteBatch();
        assetManager.load(AssetDescriptors.SCORE_FONT);
        assetManager.load(AssetDescriptors.GAME_PLAY);
        assetManager.load(AssetDescriptors.HIT);
        assetManager.load(AssetDescriptors.JUMP);
        assetManager.load(AssetDescriptors.SCORE);
        Music music = Gdx.audio.newMusic(Gdx.files.internal(AssetPaths.BG_MUSIC));
        music.setVolume(0.4f);
        music.setLooping(true);
        music.play();
        assetManager.finishLoading();
        setScreen(new StartScreen(this));
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
