package com.codegym.games.snake;
import java.util.Random;


public class GameObject {
    public int x;
    public int y;
    
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getRandomNumber(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound - 1); 
        
    }
}