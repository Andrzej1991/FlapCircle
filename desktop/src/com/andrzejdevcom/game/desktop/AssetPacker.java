package com.andrzejdevcom.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by Andrzej on 2017-06-19.
 */

public class AssetPacker {

    private static final String RAW_ASSETS_PATH = "desktop/assets_raw";
    private static final String ASSETS_PATH = "android/assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        TexturePacker.process(settings, RAW_ASSETS_PATH,
                ASSETS_PATH + "/gameplay", "gameplay");
    }
}
