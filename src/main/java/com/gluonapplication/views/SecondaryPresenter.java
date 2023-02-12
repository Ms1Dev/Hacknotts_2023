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
    private long userScore = 0;
    
    private int brushRadius = 25;

    @FXML
    private View secondary;
    @FXML
    Canvas canvas;
    @FXML
    Pane mainPane;
    
    DryingProgress progressBar;
    
    GraphicsContext graphics;
    
    ScoreCounter counter;
    
    Thread paintCheckerThread;
    
    AppThread appThread;

    boolean running;
    

    public void initialize() {    
        running = false;
        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        counter = new ScoreCounter(userScore);    
        progressBar = new DryingProgress(this);
        secondary.setTop(counter);
        secondary.setBottom(progressBar);
        
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Secondary");
            }
        });
        
        Brush brush = new Brush(80);
        Paint paint = new Paint(Color.DARKGOLDENROD, 30, 1);
        Fan fan = new Fan(0.2);
        startGame(paint, brush, fan);
    }
    
    public void newGame() {
        running = false;
        Bounds bounds = canvas.getBoundsInLocal();
        graphics.clearRect(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
        
        Brush brush = new Brush(50);
        Paint paint = new Paint(Color.ALICEBLUE, 30, 5);
        Fan fan = new Fan(0.5);
        startGame(paint, brush, fan);
    }
    
    
    public void startGame(Paint paint, Brush brush, Fan fan) {
        brushRadius = brush.getRadius();
        counter.reset(paint.getPointIncrement());
        progressBar.reset(paint.getDryingSpeed());
        
        appThread = new AppThread(counter,progressBar,this);
        
        canvas.setCursor(Cursor.CROSSHAIR);
        canvas.widthProperty().bind(mainPane.widthProperty());
        canvas.heightProperty().bind(mainPane.heightProperty());
        
        graphics = canvas.getGraphicsContext2D();
        graphics.setFill(paint.getColour());
                           
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                graphics.fillOval((me.getX() - brushRadius),(me.getY() - brushRadius), (2*brushRadius), (2*brushRadius));
            }
        });
        
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                appThread.setFrequency(fan.getSpeedIncreasePrcnt());
            }
        });
        
        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
               appThread.resetFrequency();
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
        if (isPainted() && !running) {
            running = true;
            appThread.start();
        }
    }
    
            
    private boolean isPainted() {        
        Bounds bounds = canvas.getBoundsInLocal();
        
        int x = (int) bounds.getMinX();

        WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
        PixelReader pReader = image.getPixelReader();
        
        while(x < bounds.getMaxX() - (2 * brushRadius)) {
            x += brushRadius;
            int y = (int) bounds.getMinY();
            while(y < bounds.getMaxY() - brushRadius) {
                y += brushRadius;
                if (pReader.getColor(x, y).equals(Color.valueOf("0xffffffff"))){
                    return false;
                }
            }
        }  
        return true;
    }   
    
}
