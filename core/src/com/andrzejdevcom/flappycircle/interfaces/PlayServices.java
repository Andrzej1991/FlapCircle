package com.andrzejdevcom.flappycircle.interfaces;

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
    void shareGame();
    void muteAndUnmute();
    void unlockInfoAchievement();
}