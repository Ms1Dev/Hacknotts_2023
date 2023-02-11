package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class SecondaryPresenter {

    @FXML
    private View secondary;
    @FXML
    Canvas canvas;


    public void initialize() {
        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        
        canvas = new Canvas();
        canvas.setWidth(Double.MAX_VALUE);
        canvas.setHeight(Double.MAX_VALUE);
        
        ScoreCounter counter = new ScoreCounter();
        
        secondary.setTop(counter);
        
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Secondary");
                appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e -> 
                        counter.increaseIncrement(0.2)));
            }
        });
    }
}
