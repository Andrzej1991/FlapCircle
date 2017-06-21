package com.andrzejdevcom.game.screen;

import com.andrzejdevcom.game.SkippyFlowersGame;
import com.andrzejdevcom.game.assets.AssetDescriptors;
import com.andrzejdevcom.game.config.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class HelpScreen implements Screen {
    private SkippyFlowersGame game;

    private final AssetManager assetManager;
    private Viewport viewport;
    private Stage stage;

    HelpScreen(SkippyFlowersGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    private void initUi() {
        Label.LabelStyle labeStyle = new Label.LabelStyle();
        labeStyle.font = assetManager.get(AssetDescriptors.SCORE_FONT);
        labeStyle.fontColor = Color.WHITE;
        String scoreString = "Information: remember this game is full free,\n but how get moneys " +
                "for prizes? Ads,\n did you see add when game is started? If not,\n please check your " +
                "internet connection." +
                "" +
                "\nHow much prizes it is\ndepends of how many people see ads.\n I know it can be flustrated, but remember,\n" +
                "the prizes is real!! Im hope\n this game make you soo rich and remember,\n if you find a but, error, whatever, \njust contact" +
                " with my email:\n" +
                "andrzejdevcom@gmail.com\n\n" +
                "Click to continue..";
        Label infoText = new Label(scoreString, labeStyle);
        infoText.setPosition(
                GameConfig.HUD_WIDTH / 2f,
                2 * GameConfig.HUD_HEOGHT / 4f,
                Align.center
        );
        stage.addActor(infoText);
    }


    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEOGHT);
        stage = new Stage(viewport, game.getBatch());
        initUi();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (Gdx.input.justTouched()) {
            game.setScreen(new HelpScreen1(game));
        }
    }

    @Override
    public void resize(int width, int height) {

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
