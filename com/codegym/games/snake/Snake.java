package com.codegym.games.snake;
import java.util.ArrayList;
import java.util.List;
import com.codegym.engine.cell.*;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<GameObject>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    
    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }
    
    public void draw(Game game) {
        Color color;
        if (isAlive) {
            color = Color.BLACK;
        }
        else {
            color = Color.RED;
        }
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject snakePart = snakeParts.get(i);
            if (i == 0) {
                game.setCellValueEx(snakePart.x, snakePart.y, Color.NONE, HEAD_SIGN, color, 75);
            }
            else{
                game.setCellValueEx(snakePart.x, snakePart.y, Color.NONE, BODY_SIGN, color, 75);
            }
        }
    }
    
    public void setDirection(Direction direction) {
        boolean setDirection = true;
        if ((direction == Direction.LEFT && this.direction == Direction.RIGHT )
            || (direction == Direction.RIGHT && this.direction == Direction.LEFT )
            || (direction == Direction.UP && this.direction == Direction.DOWN )
            || (direction == Direction.DOWN && this.direction == Direction.UP )) {
                setDirection = false;
            }
        if (setDirection) {
            this.direction = direction;
        }
        
    }
    
    public void move(Apple apple) {
        GameObject head = createNewHead();
        // room for improvement here..14 should not be hardcoded..
        if ((head.x < 0 || head.x > 14) || (head.y < 0 || head.y > 14) || checkCollision(head)) {
            isAlive = false;
        }
        else {
            snakeParts.add(0, head); //change head sting for second element in array list to body sign.
            if (apple.x == head.x && apple.y == head.y) {
                apple.isAlive=false;
            }
            else {
                removeTail();
            }
        }
        
    }
    
    public GameObject createNewHead() {
        int headX;
        int headY;
        GameObject head = snakeParts.get(0);
        switch (direction) {
            case LEFT:
                headX = head.x - 1;
                headY = head.y;
                break;
            case RIGHT:
                headX = head.x + 1;
                headY = head.y;
                break;
            case UP:
                headX = head.x;
                headY = head.y - 1;
                break;
            case DOWN:
                headX = head.x;
                headY = head.y + 1;
                break;
            default:
                headX = head.x;
                headY = head.y - 1;
                break;
        }
        return new GameObject(headX, headY);
    }
    
    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }
    
    public boolean checkCollision(GameObject gameObject) {
        for (GameObject part : snakeParts) {
            if ((gameObject.x) == part.x && (gameObject.y == part.y)) {
                return true;
            }
        }
        return false;
    }
    
    public int getLength() {
        return snakeParts.size();
    }
}