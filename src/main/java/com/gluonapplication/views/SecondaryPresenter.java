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
    public static long userScore = 0;
    
    private int brushRadius = 25;

    @FXML
    private View secondary;
    @FXML
    Canvas canvas;
    @FXML
    Pane mainPane;
    
    static Brush brush;
    static Fan fan;
    static Paint paint;
    
    DryingProgress progressBar;
    
    GraphicsContext graphics;
    
    ScoreCounter counter;
    
    Thread paintCheckerThread;
    
    AppThread appThread;
    
    AnimatedSprite brushAnimation;
    AnimatedSprite fanAnimation;

    boolean running;
    

    public void initialize() {    
        running = false;
        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        counter = new ScoreCounter(userScore);    
        progressBar = new DryingProgress();
        secondary.setTop(counter);
        secondary.setBottom(progressBar);
        

        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Wall");
            }
        });
               
        brush = new Brush(Brush.level.ONE);
        paint = new Paint(Paint.level.ONE);
        fan = new Fan(Fan.level.ONE);

        brushAnimation = brush.getAnimation();
        fanAnimation = fan.getAnimation();
        brushAnimation.setVisible(false);
        fanAnimation.setVisible(false);
        secondary.getChildren().add(brushAnimation);
        secondary.getChildren().add(fanAnimation);

        startGame();
    }
    
    public void newGame() {
        running = false;
        Bounds bounds = canvas.getBoundsInLocal();
        graphics.clearRect(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
        startGame();
    }
    
    public void setFan(Fan fan) {
        if (!appThread.isAlive()) {
            this.fan = fan;
        }
    }
    
    public void setBrush(Brush brush) {
        if (!appThread.isAlive()) {
            this.brush = brush;
        }
    }
    
    public void setPaint(Paint paint) {
        if (!appThread.isAlive()) {
            this.paint = paint;
        }
    }

    public void startGame() {
        brushRadius = brush.getRadius();
        counter.reset(paint.getPointIncrement());
        
        appThread = new AppThread(counter,progressBar,this);
        
        canvas.widthProperty().bind(mainPane.widthProperty());
        canvas.heightProperty().bind(mainPane.heightProperty());
        
        graphics = canvas.getGraphicsContext2D();
        graphics.setFill(paint.getColour());
        
        
        
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (!appThread.isAlive()) {
                    graphics.fillOval((me.getX() - brushRadius),(me.getY() - brushRadius), (2*brushRadius), (2*brushRadius));
                    brushAnimation.setX(me.getX() - 25);
                    brushAnimation.setY(me.getY());
                }
                else {
                    fanAnimation.setX(me.getX() - 25);
                    fanAnimation.setY(me.getY());
                }
            }
        });
        
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (appThread.isAlive()) {
                    appThread.setFrequency(fan.getSpeedIncreasePrcnt());
                    brushAnimation.setVisible(false);
                    fanAnimation.setVisible(true);
                    fanAnimation.setX(me.getX() - 25);
                    fanAnimation.setY(me.getY());
                }
                else {
                    brushAnimation.setVisible(true);
                    brushAnimation.setX(me.getX() - 25);
                    brushAnimation.setY(me.getY() - 25);
                    fanAnimation.setVisible(false);
                    
                }
                secondary.setCursor(Cursor.NONE);
            }
        });
        
        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
               appThread.resetFrequency();
               secondary.setCursor(Cursor.DEFAULT);
                brushAnimation.setVisible(false);
                fanAnimation.setVisible(false);
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
        
        while(x < bounds.getMaxX() - (2 * 25)) {
            x += 25;
            int y = (int) bounds.getMinY();
            while(y < bounds.getMaxY() - 25) {
                y += 25;
                if (pReader.getColor(x, y).equals(Color.valueOf("0xffffffff"))){
                    return false;
                }
            }
        }  
        return true;
    }   
    
}
