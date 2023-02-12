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
    private int level = 0;
    
    public void incLevel() {
        if (level < 3) {
           level++; 
        }
    }

    public void setLevel(int level){
        this.level = level;
    }
    
    public Color getColour() {
        
        switch(level) {
            case 0:
                return Color.SPRINGGREEN;
            case 1:
                return Color.CHOCOLATE;
            case 2:
                return Color.DARKCYAN;
            case 3:
                return Color.FUCHSIA;
            default:
                return Color.BISQUE;
        } 
    }

     public double getPointIncrement() {
        
        switch(level) {
            case 0:
                return 0.5;
            case 1:
                return 1.2;
            case 2:
                return 1.5;
            case 3:
                return 2.2;
            default:
                return 4.5;
        } 
    }

}
