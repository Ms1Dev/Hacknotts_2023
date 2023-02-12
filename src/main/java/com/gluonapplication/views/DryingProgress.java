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
    private final int DRYING_SPEED = 50;
    double progress;
   
    DryingProgress() {
        this.setPrefHeight(40);
        this.setPrefWidth(350);
    }
    
    public void reset(int dryingSpeed) {
        progress = 0.0;
        this.setProgress(progress);
    }
    
    public boolean updateProgress() {
        progress += DRYING_SPEED / 1000.0;
        this.setProgress(progress);
        return (progress >= 1.0);
    }
    
    public double getThisProgress() {
        return this.progress;
    }
}
