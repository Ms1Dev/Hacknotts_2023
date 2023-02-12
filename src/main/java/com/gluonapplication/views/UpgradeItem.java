/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gluonapplication.views;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author jakem
 */
public class UpgradeItem extends GridPane {
    ImageView image;
    Button btn;
    
    UpgradeItem(String name, String imagePath) {
        this.image = new ImageView();
//        File file = new File(imagePath);
//        Image img = new Image(file.toURI().toString());
//        this.image.setImage(img);
        
        this.btn = new Button();
        this.btn.setText(name);
        this.btn.setAlignment(Pos.CENTER);
        
        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0);
        this.getColumnConstraints().add(colConst);
        
        RowConstraints rowConst1 = new RowConstraints();
        rowConst1.setPrefHeight(100.0);
        this.getRowConstraints().add(rowConst1);
        
        RowConstraints rowConst2 = new RowConstraints();
        rowConst2.setPrefHeight(50.0);
        this.getRowConstraints().add(rowConst2);
        
        this.getChildren().add(this.image);
        this.getChildren().add(this.btn);
    }
}
