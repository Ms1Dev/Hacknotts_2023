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
        text = new Text();
        this.getChildren().add(text);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setPadding(new Insets(10, 0, 10,0));
        this.setHeight(30);
        this.setWidth(Double.MAX_VALUE);
    }
    
    public void reset(int increment) {
        score = 0;
        counting = false;
        this.increment = increment;
        text.setText("0 ");
        text.applyCss();
    }
    
    public void start() {
        if (!counting) {
            counting = true;
            counterThread = new Thread() {
                public void run() {
                    try {
                        while(counting){
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
        if(counting) {
            score += increment;
            int truncScore = (int) score;
            String scoreText = String.valueOf(truncScore);
            text.setText(scoreText);
        }
    }
    
    public void increaseIncrement(double inc) {
        increment += inc;
    }
    
    public void setCounting(boolean counting) {
        this.counting = counting;
    }
}
