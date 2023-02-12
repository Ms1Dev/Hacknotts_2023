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
    
    static int level = 0;

    public int getRadius() {
        switch(level) {
            case 0:
                return 30;
            case 1:
                return 40;
            case 2:
                return 50;
            case 3:
                return 60;
            default:
                return 10;
        }
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public void incLevel() {
        if (level < 3) {
           level++; 
        }
    }
    
    public AnimatedSprite getAnimation() {
        return selectAnimation(level);
    }
    
    private static AnimatedSprite selectAnimation(int lvl) {
        ArrayList<String> imageUrls = new ArrayList<>();
        switch (lvl) {
            case 0:             
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Hand/Brush-Hand-001.png");
                break;
            case 1:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Highlighter/Brush-Highlighter.png");
                break;
            case 2:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Painter/Brush-Painer-001.png");
                break;
            default:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Brush-Painter/Brush-Painer-001.png");
                break;
        }
        
        return new AnimatedSprite(imageUrls);
    }
    
}
