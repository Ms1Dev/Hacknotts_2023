/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

/**
 *
 * @author jakem
 */
public class User {
    private Brush brush;
    private Fan fan;
    private Paint paint;
    
    private long userScore;
    
    private static User instance;
    
    private User(){
        userScore = 0;
        brush = new Brush();
        fan = new Fan();
        paint = new Paint();
    }
    
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public Brush getBrush() {
        return brush;
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public long getUserScore() {
        return userScore;
    }

    public void setUserScore(long userScore) {
        this.userScore = userScore;
    }
        
    
}
