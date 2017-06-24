package com.andrzejdevcom.flappycircle.screen;

import com.andrzejdevcom.flappycircle.SkippyFlowersGame;
import com.andrzejdevcom.flappycircle.assets.AssetDescriptors;
import com.andrzejdevcom.flappycircle.common.ScoreController;
import com.andrzejdevcom.flappycircle.config.GameConfig;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andrzej on 2017-06-24.
 */

public class ScoreDialogScreen extends ApplicationAdapter implements Screen {

    public static SkippyFlowersGame game;


    private Viewport viewport;
    private Stage stage;
    private TextButton playAgain;
    private TextButton startScreen;
    private AssetManager assetManager;
    ScoreController scoreController;

    ScoreDialogScreen(SkippyFlowersGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
        scoreController = new ScoreController();
    }

    private void initTextureRegions() {
        Table table = new Table();
        table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("skin/ui-green.atlas"));
        skin.addRegions(buttonAtlas);
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button_04");
        playAgain = new TextButton("Play again", textButtonStyle);
        playAgain.getLabel().setFontScale(2.0f, 2.0f);
        playAgain.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        });
        startScreen = new TextButton("Main menu", textButtonStyle);
        startScreen.getLabel().setFontScale(2.0f, 2.0f);
        startScreen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new StartScreen(game));
            }
        });
        table.add(startScreen).minWidth(GameConfig.WIDTH /3f).minHeight(GameConfig.HEIGHT / 5);
        table.add(playAgain).minWidth(GameConfig.WIDTH /3f).minHeight(GameConfig.HEIGHT / 5);
        Label.LabelStyle labeStyle = new Label.LabelStyle();
        labeStyle.font = assetManager.get(AssetDescriptors.SCORE_FONT);
        labeStyle.fontColor = Color.WHITE;
        Label infoText = new Label(game.getCurrentGameScore(), labeStyle);
        infoText.setFontScale(2.0f, 2.0f);
        infoText.setPosition(
                GameConfig.WIDTH / 2f,
                GameConfig.HEIGHT / 1.5f
        );
        stage.addActor(infoText);
        stage.addActor(table);
        Gdx.input.setCatchBackKey(true);
    }


    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        initTextureRegions();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
