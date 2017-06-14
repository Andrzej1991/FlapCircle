package com.andrzejdevcom.game;

import com.andrzejdevcom.game.screen.GameScreen;
import com.badlogic.gdx.Game;

public class SkippyFlowersGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
