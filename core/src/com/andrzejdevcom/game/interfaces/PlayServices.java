package com.andrzejdevcom.game.interfaces;

public interface PlayServices
{
    void signIn();
    void signOut();
    void rateGame();
    void unlockAchievement();
    void submitScore(int highScore);
    void showAchievement();
    void showScore();
    boolean isSignedIn();
    void hideText();
    void showText();
}