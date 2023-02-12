/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

import java.util.ArrayList;

/**
 *
 * @author mdswa
 */
public class Brush {
    
    static enum level {
        ONE,
        TWO,
        THREE,
        FOUR
    }
    private level lvl;
    
    private int radius;

    public Brush(level lvl) {
        this.lvl = lvl;
        switch(lvl) {
            case ONE:
                this.radius = 10;
                break;
            case TWO:
                this.radius = 25;
                break;
            case THREE:
                this.radius = 35;
                break;
            case FOUR:
                this.radius = 50;
                break;
            default:
                this.radius = 10;
                break;
        }
        
//        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(level lvl) {
        switch(lvl) {
            case ONE:
                this.radius = 10;
                break;
            case TWO:
                this.radius = 25;
                break;
            case THREE:
                this.radius = 35;
                break;
            case FOUR:
                this.radius = 50;
                break;
            default:
                this.radius = 10;
                break;
        }
    }
    
    public AnimatedSprite getAnimation() {
        return selectAnimation(this.lvl);
    }
    
    private static AnimatedSprite selectAnimation(level lvl) {
        ArrayList<String> imageUrls = new ArrayList<>();
        switch (lvl) {
            case ONE:             
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Hand/Brush-Hand-001.png");
                break;
            case TWO:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Highlighter/Brush-Highlighter.png");
                break;
            case THREE:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Painter/Brush-Painer-001.png");
                break;
        }
        
        return new AnimatedSprite(imageUrls);
    }
    
}
