package com.andrzejdevcom.game.desktop;

import com.andrzejdevcom.game.config.GameConfig;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.andrzejdevcom.game.SkippyFlowersGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) GameConfig.WIDTH;
        config.height = (int) GameConfig.HEIGHT;
        new LwjglApplication(new SkippyFlowersGame(), config);
    }
}
