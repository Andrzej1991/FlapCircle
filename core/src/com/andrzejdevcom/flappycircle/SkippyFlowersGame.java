package com.andrzejdevcom.flappycircle;

import com.andrzejdevcom.flappycircle.assets.AssetDescriptors;
import com.andrzejdevcom.flappycircle.assets.AssetPaths;
import com.andrzejdevcom.flappycircle.common.ScoreController;
import com.andrzejdevcom.flappycircle.interfaces.PlayServices;
import com.andrzejdevcom.flappycircle.screen.StartScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkippyFlowersGame extends Game {

    private ScoreController scoreController;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private Music music;
    private String currentGameScore;

    public static PlayServices playServices;

    public SkippyFlowersGame(){

    }

    SkippyFlowersGame(PlayServices playServices){
        this.playServices = playServices;
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
        music = Gdx.audio.newMusic(Gdx.files.internal(AssetPaths.BG_MUSIC));
        music.setVolume(0.4f);
        music.setLooping(true);
        music.play();
        assetManager.finishLoading();
        setScreen(new StartScreen(this));
    }

    public Music getMusic() {
        return music;
    }

    public ScoreController getScoreController() {
        return scoreController;
    }

    public void setCurrentGameScore(String currentGameScore) {
        this.currentGameScore = currentGameScore;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public String getCurrentGameScore() {
        return currentGameScore;
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
        batch.dispose();
    }
}
