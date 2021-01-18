package com.codegym.games.snake;
import com.codegym.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;
    
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    
    private void createGame() {
        score = 0;
        turnDelay = 1000;
        setTurnTimer(turnDelay);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        isGameStopped = false;
        drawScene();
        setScore(score);

    }
    
    private void drawScene() {
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DARKSEAGREEN,"");
            }
        }
        snake.draw(this);
        apple.draw(this);
        
    }
    
    @Override
    public void onTurn(int direction) {
        snake.move(apple);
        if (apple.isAlive == false) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay); 
        }
        if (snake.isAlive == false) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
        
    }
    
    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            default:
                snake.setDirection(Direction.LEFT);
                break;
        }
    }
    
    private void createNewApple() {
        int appleX;
        int appleY;
        do {
            appleX = getRandomNumber(WIDTH);
            appleY = getRandomNumber(HEIGHT);
            apple = new Apple(appleX, appleY);
        } while (snake.checkCollision(apple));
    }
    
    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER \n SCORE: "+ score, Color.RED, 30);
    }
    
    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN \n SCORE: "+ score, Color.GREEN, 30);
        
    }
    
}

