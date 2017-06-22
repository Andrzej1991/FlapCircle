package com.andrzejdevcom.game.common;

import com.andrzejdevcom.game.SkippyFlowersGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreController {

    private static final String HIGHT_SCORE_KEY = "highScore";

    private final Preferences prefs;
    private int score;
    private int highScore;

    public ScoreController(){
        prefs = Gdx.app.getPreferences(SkippyFlowersGame.class.getSimpleName());
        highScore = prefs.getInteger(HIGHT_SCORE_KEY, 0);
    }

    public void incrementScore(){
        score++;
    }

    public String getScoreString(){
        return Integer.toString(score);
    }

    public int getScore() {
        return score;
    }

    public String getHighScoreString(){
        return  Integer.toString(highScore);
    }

    public void updateHighScore(){
        if(score>highScore){
            highScore = score;
            prefs.putInteger(HIGHT_SCORE_KEY, highScore);
            prefs.flush();
        }
    }

    public void reset(){
        score = 0;
    }
}
