/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

import javafx.application.Platform;

/**
 *
 * @author mdswa
 */
public class AppThread extends Thread {
    private ScoreCounter scoreCounter;
    private DryingProgress dryingProgress;
    private SecondaryPresenter presenter;
    private static final int DEFAULT_FREQ = 800;
    private int frequency = DEFAULT_FREQ;
    
    AppThread(ScoreCounter scoreCounter, DryingProgress dryingProgress, SecondaryPresenter presenter) {
        this.scoreCounter = scoreCounter;
        this.dryingProgress = dryingProgress;
        this.presenter = presenter;
        this.setDaemon(true);
    }
    
    public void run() {
        scoreCounter.setCounting(true);
        try {
            while(!dryingProgress.updateProgress()){
                Thread.sleep(frequency);
                Platform.runLater(()->scoreCounter.updateScore());
            }
            scoreCounter.setCounting(false);
            presenter.newGame();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void setFrequency(double speedIncreasePrcnt) {
        frequency = (int) (frequency - frequency * speedIncreasePrcnt);
    }
    
    public void resetFrequency() {
        this.frequency = DEFAULT_FREQ;
    }
}
