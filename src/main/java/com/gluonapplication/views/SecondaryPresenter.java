package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
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
    
    ScoreCounter counter;
    
    Thread paintCheckerThread;


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
        
        counter = new ScoreCounter();
        
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
        
        paintCheckerThread = new Thread() {
            public void run() {
                try {
                    while(true){
                        Thread.sleep(500);
                        Platform.runLater(()->checkIsPainted());
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        };
        paintCheckerThread.setDaemon(true);
        paintCheckerThread.start();
    }
    
    private void checkIsPainted() {
        if (isPainted()) {
            counter.start();
        }
    }
    
            
    private boolean isPainted() {        
        Bounds bounds = canvas.getBoundsInLocal();
        
        int x = (int) bounds.getMinX();

        WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
        PixelReader pReader = image.getPixelReader();
        
        while(x < bounds.getMaxX() - (2 * CIRCLE_RAD)) {
            x += CIRCLE_RAD;
            int y = (int) bounds.getMinY();
            while(y < bounds.getMaxY() - CIRCLE_RAD) {
                y += CIRCLE_RAD;
                if (pReader.getColor(x, y).equals(Color.valueOf("0xffffffff"))){
                    return false;
                }
            }
        }  
        return true;
    }
}
