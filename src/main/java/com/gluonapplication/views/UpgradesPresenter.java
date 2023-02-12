package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInLeftTransition;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class UpgradesPresenter {

    @FXML
    private View upgrades;

    @FXML
    private Label label;

    public void initialize() {
        upgrades.setShowTransitionFactory(BounceInLeftTransition::new);
        
        
        upgrades.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Upgrades");
//                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
//                        System.out.println("Search")));
            }
        });   
    }
    
}