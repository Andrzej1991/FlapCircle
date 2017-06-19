package com.andrzejdevcom.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Andrzej on 2017-06-19.
 */

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> SCORE_FONT = new
            AssetDescriptor<BitmapFont>(AssetPaths.SCORE_FONT, BitmapFont.class);

    private AssetDescriptors() {

    }
}
