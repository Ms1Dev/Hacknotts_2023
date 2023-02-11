package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SecondaryPresenter {
    static final int CIRCLE_RAD = 25;

    @FXML
    private View secondary;
    @FXML
    Canvas canvas;
    @FXML
    Pane mainPane;
    
    GraphicsContext graphics;


    public void initialize() {
        secondary.setShowTransitionFactory(BounceInRightTransition::new);

        canvas.setCursor(Cursor.CROSSHAIR);
        canvas.widthProperty().bind(mainPane.widthProperty());
        canvas.heightProperty().bind(mainPane.heightProperty());
        
        graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.CHOCOLATE);
           
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                graphics.fillOval((me.getX() - CIRCLE_RAD),(me.getY() - CIRCLE_RAD), (2*CIRCLE_RAD), (2*CIRCLE_RAD));
                
            }
        });
        
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
    
            
    private boolean isPainted() {
        boolean painted = false;
            double x = canvas.getLayoutX();
            double y = canvas.getLayoutY();
            
            int xPixels = (int) (canvas.getWidth() / CIRCLE_RAD);
            int yPixels = (int) (canvas.getHeight() / CIRCLE_RAD);
            
            for(int i = 0; i < xPixels; i++) {
                x += CIRCLE_RAD;
                for(int j = 0; j < yPixels; j++) {
                    y += CIRCLE_RAD;
//                    WritableImage image = canvas.s
                }
            }
            // for pixels x
                // for pixels y
                    // painted = pixel at x,y has colour 
        
        return painted;
    }
}
