package com.andrzejdevcom.game.common;

/**
 * Created by Andrzej on 2017-06-19.
 */

public class ScoreController {

    private int score;

    public ScoreController(){

    }

    public void incrementScore(){
        score++;
    }

    public String getScoreString(){
        return Integer.toString(score);
    }

    public void reset(){
        score = 0;
    }
}
