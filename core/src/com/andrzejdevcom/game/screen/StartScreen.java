package com.andrzejdevcom.game.screen;

import com.andrzejdevcom.game.SkippyFlowersGame;
import com.andrzejdevcom.game.assets.AssetDescriptors;
import com.andrzejdevcom.game.assets.RegionNames;
import com.andrzejdevcom.game.common.ScoreController;
import com.andrzejdevcom.game.config.GameConfig;
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

    public StartScreen(SkippyFlowersGame game) {
        StartScreen.game = game;
        assetManager = game.getAssetManager();
        scoreController = game.getScoreController();
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEOGHT);
        stage = new Stage(viewport, game.getBatch());
        initUI();
        Gdx.input.setInputProcessor(stage);
    }

    private void initUI() {
        TextureAtlas atlas = assetManager.get(AssetDescriptors.GAME_PLAY);
        TextureRegion playRegion = atlas.findRegion(RegionNames.PLAY);
        ImageButton play = new ImageButton(new TextureRegionDrawable(playRegion));
        play.setPosition(GameConfig.HUD_WIDTH / 2f, GameConfig.HUD_HEOGHT / 4f, Align.center);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });
        TextureRegion leaderboardRegion = atlas.findRegion(RegionNames.LEADERBOARD);
        ImageButton leaderBoard = new ImageButton(new TextureRegionDrawable(leaderboardRegion));
        leaderBoard.setPosition(GameConfig.HUD_WIDTH / 2f, GameConfig.HUD_HEOGHT / 3f);
        leaderBoard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playServices.showScore();
            }
        });

        TextureRegion achievementRegion = atlas.findRegion(RegionNames.ACHIEVEMENT);
        ImageButton achievementBoard = new ImageButton(new TextureRegionDrawable(achievementRegion));
        achievementBoard.setPosition(GameConfig.HUD_WIDTH / 12f, GameConfig.HUD_HEOGHT / 3f);

        stage.addActor(achievementBoard);

        stage.addActor(leaderBoard);


        stage.addActor(play);
        Label.LabelStyle labeStyle = new Label.LabelStyle();
        labeStyle.font = assetManager.get(AssetDescriptors.SCORE_FONT);
        labeStyle.fontColor = Color.WHITE;

        String scoreString = "Best: " + scoreController.getHighScoreString();

        Label bestScore = new Label(scoreString, labeStyle);
        bestScore.setPosition(
                GameConfig.HUD_WIDTH / 2f,
                3 * GameConfig.HUD_HEOGHT / 4f,
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
