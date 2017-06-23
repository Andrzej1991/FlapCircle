package com.andrzejdevcom.flapcircle;

import com.andrzejdevcom.flapcircle.assets.AssetDescriptors;
import com.andrzejdevcom.flapcircle.common.ScoreController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkippyFlowersGame extends Game {

    private ScoreController scoreController;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private Music music;
    private AdHandler adHandler;
    boolean toggle;
    private Sound sound;

    public static com.andrzejdevcom.flapcircle.interfaces.PlayServices playServices;

    public SkippyFlowersGame(){

    }

    SkippyFlowersGame(com.andrzejdevcom.flapcircle.interfaces.PlayServices playServices){
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
        music = Gdx.audio.newMusic(Gdx.files.internal(com.andrzejdevcom.flapcircle.assets.AssetPaths.BG_MUSIC));
        music.setVolume(0.4f);
        music.setLooping(true);
        music.play();
        assetManager.finishLoading();
        setScreen(new com.andrzejdevcom.flapcircle.screen.StartScreen(this));
    }

    public Music getMusic() {
        return music;
    }

    public Sound getSound() {
        return sound;
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
