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
    private double speedIncreasePrcnt;
    enum level {
        ONE,
        TWO,
        THREE,
        FOUR
    }
    private level lvl;

    public Fan(level lvl) {
        this.lvl = lvl;
        switch(lvl) {
            case ONE:
                this.speedIncreasePrcnt = .4;
                break;
            case TWO:
                this.speedIncreasePrcnt = .6;
                break;
            case THREE:
                this.speedIncreasePrcnt = .8;
                break;
            case FOUR:
                this.speedIncreasePrcnt = 1.0;
                break;
            default:
                this.speedIncreasePrcnt = 0.2;
                break;
        }
    }

    public double getSpeedIncreasePrcnt() {
        return speedIncreasePrcnt;
    }
    
    public AnimatedSprite getAnimation() {
        return selectAnimation(lvl);
    }
    
    private static AnimatedSprite selectAnimation(level lvl) {
         ArrayList<String> imageUrls = new ArrayList<>();
        switch (lvl) {
            case ONE:                        
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-001.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-002.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-003.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Hand/Fan-Hand-004.png");
                break;
            case TWO:
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-001.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-002.png");
                imageUrls.add("file:./src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-003.png");
                break;
        }
        return new AnimatedSprite(imageUrls);
    }
    
}
