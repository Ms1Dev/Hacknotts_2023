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
    enum level {
        ONE,
        TWO,
        THREE,
        FOUR
    }
    
    private int radius;
    private int fanLevel = 0;

    public Brush(level l) {
        switch(l) {
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

    public void setRadius(level l) {
        switch(l) {
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
        return selectAnimation(fanLevel);
    }
    
    private static AnimatedSprite selectAnimation(int id) {
         ArrayList<String> imageUrls = new ArrayList<>();
        switch (id) {
            case 0:             
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Hand/Brush-Hand-001.png");
                break;
            case 1:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Highlighter/Brush-Highlighter.png");
                break;
            case 2:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Painter/Brush-Painer-001.png");
                break;
        }
        return new AnimatedSprite(imageUrls);
    }
    
}
