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
    private int fanLevel = 0;

    public Fan(double speedIncreasePrcnt) {
        this.speedIncreasePrcnt = speedIncreasePrcnt;
    }

    public double getSpeedIncreasePrcnt() {
        return speedIncreasePrcnt;
    }
    
    public AnimatedSprite getAnimation() {
        return selectAnimation(fanLevel);
    }
    
    private static AnimatedSprite selectAnimation(int id) {
         ArrayList<String> imageUrls = new ArrayList<>();
        switch (id) {
            case 0:             
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-001.png");
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-002.png");
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-003.png");
                break;
            case 1:
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-001.png");
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-002.png");
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-003.png");
                break;
            case 2:
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-001.png");
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-002.png");
                imageUrls.add("file:C:/Melon/NetBeans/GluonApplication/src/main/resources/Assets/SPRITES/Fan-Ped/Fan-Ped-003.png");
                break;
        }
        return new AnimatedSprite(imageUrls);
    }
    
}
