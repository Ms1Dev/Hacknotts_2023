package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInLeftTransition;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class UpgradesPresenter {

    @FXML
    private View upgrades;

    @FXML
    private Label label;
    
    @FXML
    private Button handButton;
    @FXML
    private Button highlighterButton;
    @FXML
    private Button brushButton;
    @FXML
    private Button handFanButton;
    @FXML
    private Button tableFanButton;
    @FXML
    private Button pcFanButton;

    public void initialize() {
        upgrades.setShowTransitionFactory(BounceInLeftTransition::new);
        
        
        upgrades.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Upgrades");
            }
        });   
    }
    
    @FXML
    private void buyHand() {     
        User user = User.getInstance();
        if (user.getUserScore() >= 100) {
            user.setUserScore(user.getUserScore() - 100);
            user.getBrush().incLevel();
            handButton.setDisable(true);
        }
    }
    
    
    @FXML
    private void buyHighlighter() {
        User user = User.getInstance();
        if (user.getUserScore() >= 300) {
            user.setUserScore(user.getUserScore() - 300);
            user.getBrush().incLevel();
            highlighterButton.setDisable(true);
        }
    }
    
    @FXML
    private void buyBrush() {
        User user = User.getInstance();
        if (user.getUserScore() >= 600) {
            user.setUserScore(user.getUserScore() - 600);
            user.getBrush().incLevel();
            brushButton.setDisable(true);
        }
    }
    
    @FXML
    private void buyHandFan() {
        User user = User.getInstance();
        if (user.getUserScore() >= 150) {
            user.setUserScore(user.getUserScore() - 150);
            user.getFan().incLevel();
            handFanButton.setDisable(true);
        }
    }
        
    @FXML
    private void buyTableFan() {
        User user = User.getInstance();
        if (user.getUserScore() >= 400) {
            user.setUserScore(user.getUserScore() - 400);
            user.getFan().incLevel();
            tableFanButton.setDisable(true);
        }
    }
        
    @FXML
    private void buyPcFan() {
        User user = User.getInstance();
        if (user.getUserScore() >= 700) {
            user.setUserScore(user.getUserScore() - 700);
            user.getFan().incLevel();
            pcFanButton.setDisable(true);
        }
    }
}
