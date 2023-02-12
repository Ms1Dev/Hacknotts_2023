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
public class Fan {
    private int level = 0;

    public double getSpeedIncreasePrcnt() {
        switch(level) {
            case 0:
                return .4;
            case 1:
                return .6;
            case 2:
                return .8;
            case 3:
                return 1.0;
            default:
                return 0.2;
        }
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
            case 1:                        
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-001.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-002.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-003.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-004.png");
                break;
            case 2:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-001.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-002.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-003.png");
                break;
            default:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-001.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-002.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-003.png");
                break;
        }
        return new AnimatedSprite(imageUrls);
    }
    
}
