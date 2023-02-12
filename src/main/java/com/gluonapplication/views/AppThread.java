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
    private int frequency = 800;
    
    AppThread(ScoreCounter scoreCounter, DryingProgress dryingProgress, SecondaryPresenter presenter) {
        this.scoreCounter = scoreCounter;
        this.dryingProgress = dryingProgress;
        this.presenter = presenter;
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
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
