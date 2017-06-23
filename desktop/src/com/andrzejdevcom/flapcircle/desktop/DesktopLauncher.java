package com.andrzejdevcom.flapcircle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.andrzejdevcom.flapcircle.SkippyFlowersGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) com.andrzejdevcom.flapcircle.config.GameConfig.WIDTH;
        config.height = (int) com.andrzejdevcom.flapcircle.config.GameConfig.HEIGHT;
        new LwjglApplication(new SkippyFlowersGame(), config);
    }
}
