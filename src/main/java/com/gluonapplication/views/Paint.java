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
    static enum level {
        ONE,
        TWO,
        THREE,
        FOUR
    }
    Color colour;
    int pointIncrement;
    private level lvl;

    public Paint(level lvl) {
        this.lvl = lvl;
        
        switch(lvl) {
            case ONE:
                this.pointIncrement = 10;
                this.colour = Color.SPRINGGREEN;
                break;
            case TWO:
                this.pointIncrement = 20;
                this.colour = Color.CHOCOLATE;
                break;
            case THREE:
                this.pointIncrement = 30;
                this.colour = Color.DARKCYAN;
                break;
            case FOUR:
                this.pointIncrement = 40;
                this.colour = Color.FUCHSIA;
                break;
            default:
                this.pointIncrement = 10;
                this.colour = Color.BISQUE;
                break;
        }
        
    }

    public Color getColour() {
        return colour;
    }

    public int getPointIncrement() {
        return pointIncrement;
    }  
}
