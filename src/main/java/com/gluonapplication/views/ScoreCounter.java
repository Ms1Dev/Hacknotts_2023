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
    private double increment;
    final static int FREQ = 500;
    boolean counting;
    
    ScoreCounter() {
        increment = 10;
        text = new Text();
        text.setText("0 ");
        this.getChildren().add(text);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setPadding(new Insets(10, 0, 10,0));
        this.setHeight(30);
        this.setWidth(Double.MAX_VALUE);
    }
    
    public void updateScore_() {
        String scoreText = String.valueOf(User.getInstance().getUserScore());
        text.setText(scoreText);
    }
    
    public void reset() {
        counting = false;
        text.applyCss();
    }

    public void updateScore() {
        User user = User.getInstance();
        long score = user.getUserScore();
        if (counting) {
            score += (long) increment;
            String scoreText = String.valueOf(score);
            text.setText(scoreText);
            user.setUserScore(score);
        }
    }
    
    public void increaseIncrement(double inc) {
        increment += inc;
    }
    
    public void setCounting(boolean counting) {
        this.counting = counting;
    }
}
