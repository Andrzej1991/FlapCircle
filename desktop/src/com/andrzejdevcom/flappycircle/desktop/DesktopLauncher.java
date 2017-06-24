package com.andrzejdevcom.flappycircle.desktop;

import com.andrzejdevcom.flappycircle.config.GameConfig;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) GameConfig.WIDTH;
        config.height = (int) GameConfig.HEIGHT;
        new LwjglApplication(new com.andrzejdevcom.flappycircle.SkippyFlowersGame(), config);
    }
}
