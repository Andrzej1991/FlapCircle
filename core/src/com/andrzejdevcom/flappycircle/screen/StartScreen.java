package com.andrzejdevcom.flappycircle.screen;

import com.andrzejdevcom.flappycircle.SkippyFlowersGame;
import com.andrzejdevcom.flappycircle.assets.AssetDescriptors;
import com.andrzejdevcom.flappycircle.assets.RegionNames;
import com.andrzejdevcom.flappycircle.common.ScoreController;
import com.andrzejdevcom.flappycircle.config.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartScreen implements Screen {

    private final AssetManager assetManager;
    private final ScoreController scoreController;

    private Viewport viewport;
    private Stage stage;
    public static SkippyFlowersGame game;
    private ImageButton play, leaderBoard, achievementBoard, info, settings, share, rate;

    public StartScreen(SkippyFlowersGame game) {
        StartScreen.game = game;
        assetManager = game.getAssetManager();
        scoreController = game.getScoreController();
        game.playServices.submitScore(Integer.parseInt(scoreController.getHighScoreString()));
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, game.getBatch());
        initUI();
        Gdx.input.setInputProcessor(stage);
        game.playServices.showText();
    }

    private void initTextureRegions() {
        TextureAtlas atlas = assetManager.get(AssetDescriptors.GAME_PLAY);
        TextureRegion playRegion = atlas.findRegion(RegionNames.PLAY);
        play = new ImageButton(new TextureRegionDrawable(playRegion));
        play.setPosition(GameConfig.HUD_WIDTH / 2f, GameConfig.HUD_HEIGHT / 3f, Align.center);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
                game.playServices.hideText();
            }
        });
        TextureRegion leaderboardRegion = atlas.findRegion(RegionNames.LEADERBOARD);
        leaderBoard = new ImageButton(new TextureRegionDrawable(leaderboardRegion));
        leaderBoard.setPosition(GameConfig.HUD_WIDTH / 2f, GameConfig.HUD_HEIGHT / 2f, Align.center);
        leaderBoard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playServices.showScore();
            }
        });
        TextureRegion achievementRegion = atlas.findRegion(RegionNames.ACHIEVEMENT);
        achievementBoard = new ImageButton(new TextureRegionDrawable(achievementRegion));
        achievementBoard.setPosition(GameConfig.HUD_WIDTH / 4f, GameConfig.HUD_HEIGHT / 2f, Align.center);
        achievementBoard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playServices.showAchievement();
            }
        });
        TextureRegion infoRegion = atlas.findRegion(RegionNames.INFO);
        info = new ImageButton(new TextureRegionDrawable(infoRegion));
        info.setPosition((GameConfig.HUD_WIDTH / 2f) + (GameConfig.HUD_WIDTH / 4f), GameConfig.HUD_HEIGHT / 2f, Align.center);
        info.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HelpScreen1(game));
            }
        });
        TextureRegion settingsRegion = atlas.findRegion(RegionNames.SETTINGS);
        settings = new ImageButton(new TextureRegionDrawable(settingsRegion));
        settings.setPosition(GameConfig.HUD_WIDTH / 2f, GameConfig.HUD_HEIGHT / 12f, Align.center);
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new com.andrzejdevcom.flappycircle.screen.SettingsScreen(game));
            }
        });
        TextureRegion shareRegion = atlas.findRegion(RegionNames.SHARE);
        share = new ImageButton(new TextureRegionDrawable(shareRegion));
        share.setPosition((GameConfig.HUD_WIDTH / 2f) + (GameConfig.HUD_WIDTH / 4f), GameConfig.HUD_HEIGHT / 4.7f, Align.center);
        share.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playServices.shareGame();
            }
        });
        TextureRegion rateRegion = atlas.findRegion(RegionNames.RATE);
        rate = new ImageButton(new TextureRegionDrawable(rateRegion));
        rate.setPosition(GameConfig.HUD_WIDTH / 4f, GameConfig.HUD_HEIGHT / 4.7f, Align.center);
        rate.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO give link to shop when app is pushed
                game.playServices.rateGame();
            }
        });
    }

    private void initUI() {
        initTextureRegions();
        stage.addActor(achievementBoard);
        stage.addActor(leaderBoard);
        stage.addActor(play);
        stage.addActor(info);
        stage.addActor(settings);
        stage.addActor(share);
        stage.addActor(rate);
        Label.LabelStyle labeStyle = new Label.LabelStyle();
        labeStyle.font = assetManager.get(AssetDescriptors.SCORE_FONT);
        labeStyle.fontColor = Color.WHITE;
        String scoreString = "Best score: " + scoreController.getHighScoreString();
        game.playServices.unlockAchievement();
        Label bestScore = new Label(scoreString, labeStyle);
        bestScore.setPosition(
                GameConfig.HUD_WIDTH / 2f,
                3 * GameConfig.HUD_HEIGHT / 4.5f,
                Align.center
        );
        stage.addActor(bestScore);
    }

    private void play() {
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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

        stage.dispose();
    }
}
