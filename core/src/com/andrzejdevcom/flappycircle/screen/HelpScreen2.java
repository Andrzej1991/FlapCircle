package com.andrzejdevcom.flappycircle.screen;

import com.andrzejdevcom.flappycircle.SkippyFlowersGame;
import com.andrzejdevcom.flappycircle.assets.AssetDescriptors;
import com.andrzejdevcom.flappycircle.config.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class HelpScreen2 implements Screen {

    private SkippyFlowersGame game;
    private Viewport viewport;
    private Texture texture;
    private AssetManager assetManager;
    private Stage stage;

    HelpScreen2(SkippyFlowersGame game) {
        this.game = game;
        texture = new Texture("help2.png");
        assetManager = game.getAssetManager();
        game.playServices.hideText();
    }

    private void initUi() {
        Label.LabelStyle labeStyle = new Label.LabelStyle();
        labeStyle.font = assetManager.get(AssetDescriptors.SCORE_FONT);
        labeStyle.fontColor = Color.WHITE;
        String scoreString = "Click to continue..";
        Label infoText = new Label(scoreString, labeStyle);
        infoText.setPosition(
                200,
                150,
                Align.center
        );
        stage.addActor(infoText);
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, game.getBatch());
        initUi();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());
        game.getBatch().end();
        stage.act();
        stage.draw();
        if (Gdx.input.justTouched()) {
            game.setScreen(new StartScreen(game));
        }
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
        texture.dispose();
        stage.dispose();
    }
}
