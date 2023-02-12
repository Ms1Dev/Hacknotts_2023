/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author mdswa
 */
public class AnimatedSprite extends ImageView {
    ArrayList<Image> images = new ArrayList<>();
    int imageIndex = 0;
    private static final int BLINK_RATE = 300;
    SequentialTransition animation;
    
    AnimatedSprite(ArrayList<String> imageUrls) {
        for (String url: imageUrls) {
            Image image = new Image(url, 50, 0, true, true);
            images.add(image);
        }
        PauseTransition pause = new PauseTransition(Duration.millis(200));
        pause.setOnFinished(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae) {
                changeImage();
                pause.play();
            }
        });
        pause.play();  
    }
    
    private void changeImage() {
        this.setImage(images.get(imageIndex));
        imageIndex++;
        if (imageIndex >= images.size()) {
            imageIndex = 0;
        }
    }
}
