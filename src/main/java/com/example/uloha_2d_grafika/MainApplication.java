package com.example.uloha_2d_grafika;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class MainApplication extends Application {
    public static final int SCREEN_WIDTH = 1900;
    public static final int SCREEN_HEIGHT = 920;

    GraphicsContext graphicsContext;

    public int speed = 10;

    Enemy enemy;

    Random random = new Random();

    double mouseX;
    double mouseY;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Klasifikovaná úloha");
        stage.show();

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            mouseX = event.getX();
            mouseY = event.getY();
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > 1000000000l/speed) {
                    lastTick = now;
                    tick();
                }
            }
        };
        animationTimer.start();

        enemy = new Enemy(random.nextInt(SCREEN_WIDTH - 100), random.nextInt(SCREEN_HEIGHT - 100));

    }

    int counter = 0;
    int imgCounter = 0;

    private void tick(){

        if(!enemy.stop) {
            if (imgCounter == 19) {
                enemy.setTick(imgCounter);
                enemy.loadTextures();
                imgCounter = 0;
            } else {
                enemy.setTick(imgCounter);
                enemy.loadTextures();
            }
        }
        else{
            if(imgCounter == 10){
                imgCounter = 0;
                enemy.stop = false;
                enemy.changeDirection();
                enemy.x = random.nextInt(SCREEN_WIDTH - enemy.width);
                enemy.y = random.nextInt(SCREEN_HEIGHT - enemy.height);
                enemy.setTick(imgCounter);
                enemy.loadTextures();
            }
            else{
                enemy.setTick(imgCounter);
                enemy.loadTextures();
            }
        }

        if(counter == 30) {
            enemy.direction = enemy.changeDirection();
            counter = 0;
        }
        clearScreen();


        graphicsContext.drawImage(enemy.image, enemy.x, enemy.y, enemy.width, enemy.height);

        switch (enemy.direction){
            case 0 -> enemy.downMovement();
            case 1 -> enemy.upMovement();
            case 2 -> enemy.leftMovement();
            case 3 -> enemy.rightMovement();
            default -> {

            }
        }

        if(enemy.x + enemy.width >= SCREEN_WIDTH){
            enemy.direction = 2;
        }
        else if(enemy.x <= 0){
            enemy.direction = 3;
        }
        else if(enemy.y <= 0){
            enemy.direction = 0;
        }
        else if(enemy.y + enemy.height >= SCREEN_HEIGHT){
            enemy.direction = 1;
        }
        else if(!enemy.stop){
            counter++;
        }

        //kolize
        if(mouseX >= enemy.x && mouseX <= enemy.x + enemy.width && mouseY >= enemy.y && mouseY <= enemy.y + enemy.height){
            enemy.direction = 4;
            imgCounter = 0;
            enemy.stop = true;
            mouseX = 0;
            mouseY = 0;
        }
            imgCounter++;
    }

    private void clearScreen() {
        graphicsContext.clearRect(0,0, SCREEN_WIDTH, SCREEN_WIDTH);
    }

    public static void main(String[] args) {
        launch();
    }
}