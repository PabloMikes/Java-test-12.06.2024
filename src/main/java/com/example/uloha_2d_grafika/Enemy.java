package com.example.uloha_2d_grafika;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.List;
import java.util.Random;

public class Enemy {
    public static final List<String> TEXTURES_MOVEMENT = List.of("pohyb1.png", "pohyb2.png","pohyb3.png","pohyb4.png","pohyb5.png","pohyb6.png","pohyb7.png","pohyb8.png","pohyb9.png","pohyb10.png","pohyb11.png","pohyb12.png","pohyb13.png","pohyb14.png","pohyb15.png","pohyb16.png","pohyb17.png","pohyb18.png","pohyb19.png","pohyb20.png");

    public static final List<String> TEXTURES_POP = List.of("pop1.png", "pop2.png","pop3.png","pop4.png","pop5.png","pop6.png","pop7.png","pop8.png","pop9.png","pop10.png","pop11.png");

    Image image;

    int x;
    int y;
    int height;
    int width;
    int counter;
    int direction;
    int tick;
    boolean stop;

    Random random = new Random();

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 5;
        this.counter = 0;
        this.direction = 0;
        this.stop = false;
        loadTextures();
    }

    URL url;

    public void loadTextures() {
        if(!stop) {
            url = getClass().getResource("/images/" + TEXTURES_MOVEMENT.get(tick));
        }
        else{
            url = getClass().getResource("/images/" + TEXTURES_POP.get(tick));
        }
        if (url != null) {
            image = new Image(url.toString());
        } else {
            System.out.println("Nejsou obrazky 3:");
        }
        setWidth((int)image.getWidth()/3);
        setHeight((int)image.getHeight()/3);
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int changeDirection(){
        direction = random.nextInt(4);
        System.out.println(direction);
        return direction;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void downMovement(){
        y += 10;
    }
    public void upMovement(){
        y -= 10;
    }
    public void leftMovement(){
        x -= 10;
    }
    public void rightMovement(){
        x += 10;
    }

}
