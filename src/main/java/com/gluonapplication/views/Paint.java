/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

import javafx.scene.paint.Color;

/**
 *
 * @author mdswa
 */
public class Paint {
    Color colour;
    int dryingSpeed, pointIncrement;

    public Paint(Color colour, int dryingSpeed, int pointIncrement) {
        this.colour = colour;
        this.dryingSpeed = dryingSpeed;
        this.pointIncrement = pointIncrement;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public int getDryingSpeed() {
        return dryingSpeed;
    }

    public void setDryingSpeed(int dryingSpeed) {
        this.dryingSpeed = dryingSpeed;
    }

    public int getPointIncrement() {
        return pointIncrement;
    }

    public void setPointIncrement(int pointIncrement) {
        this.pointIncrement = pointIncrement;
    }
    
    
}
