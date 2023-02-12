/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

import com.gluonhq.charm.glisten.control.ProgressBar;
import javafx.application.Platform;

/**
 *
 * @author mdswa
 */
public class DryingProgress extends ProgressBar {
    int dryingSpeed;
    double progress;
    Thread progressThread;
    boolean drying;
    SecondaryPresenter presenter;
   
    DryingProgress(SecondaryPresenter presenter) {
        this.presenter = presenter;
        this.setPrefHeight(40);
        this.setPrefWidth(350);
    }
    
    public void reset(int dryingSpeed) {
        drying = false;
        this.dryingSpeed = dryingSpeed;
        progress = 0.0;
        this.setProgress(progress);
    }
    
    public void start() {
        if (!drying) {
            drying = true;
            progressThread = new Thread() {
                public void run() {
                    try {
                        while(getThisProgress() <= 1.0){
                            Thread.sleep(500);
                            updateProgress();  
                        }
                        Platform.runLater(()->presenter.newGame());
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            progressThread.setDaemon(true);
            progressThread.start();
        }
    }
    
    private void updateProgress() {
        progress += dryingSpeed / 1000.0;
        this.setProgress(progress);
    }
    
    public double getThisProgress() {
        return this.progress;
    }
}
