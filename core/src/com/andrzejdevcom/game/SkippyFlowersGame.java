package com.andrzejdevcom.game;

import com.andrzejdevcom.game.common.ScoreController;
import com.andrzejdevcom.game.screen.GameScreen;
import com.badlogic.gdx.Game;

public class SkippyFlowersGame extends Game {

    private ScoreController scoreController;

    @Override
    public void create() {
        scoreController = new ScoreController();
        setScreen(new GameScreen(this));
    }

    public ScoreController getScoreController() {
        return scoreController;
    }
}
