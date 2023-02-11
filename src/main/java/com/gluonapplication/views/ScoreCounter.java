/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


/**
 *
 * @author mdswa
 */
public class ScoreCounter extends TextFlow {
    Text text;
    private double increment,score;
    final static int FREQ = 500;
    Thread counterThread;
    boolean counting;
    
    ScoreCounter() {
        counting = false;
        score = 0;
        increment = 1;
        text = new Text("0 ");
        text.applyCss();
        this.getChildren().add(text);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setPadding(new Insets(10, 0, 10,0));
        this.setHeight(30);
        this.setWidth(Double.MAX_VALUE);
    }
    
    public void start() {
        if (!counting) {
            counting = true;
            counterThread = new Thread() {
                public void run() {
                    try {
                        while(true){
                            Thread.sleep(FREQ);
                            updateScore();  
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            counterThread.setDaemon(true);
            counterThread.start();
        }
    }
    
    private void updateScore() {
        score += increment;
        int truncScore = (int) score;
        String scoreText = String.valueOf(truncScore);
        text.setText(scoreText);
    }
    
    public void increaseIncrement(double inc) {
        increment += inc;
    }
}
