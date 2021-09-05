package com.kodilla.game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int gameWidth = 1530;
    static final int gameHeight = (int)(gameWidth * (0.52));
    static final Dimension screenSize = new Dimension(gameWidth,gameHeight);
    static final int ballDiameter = 25;
    static final int playerWidth = 30;
    static final int playerHeight = 150;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Ball ball;
    Player player1;
    Player player2;
    Score score;

    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(gameWidth,gameHeight);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(screenSize);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((gameWidth/2)-(ballDiameter/2),random.nextInt(gameHeight-ballDiameter),ballDiameter,ballDiameter);
    }
    public void newPaddles() {
        player1 = new Player(0,(gameHeight/2)-(playerHeight/2),playerWidth,playerHeight,1);
        player2 = new Player(gameWidth-playerWidth,(gameHeight/2)-(playerHeight/2),playerWidth,playerHeight,2);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);
        score.draw(g);
    }
    public void move() {
        ball.move();
        player1.move();
        player2.move();
    }
    public void checkCollision() {
        if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= gameHeight-ballDiameter) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(player1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(player2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(player1.y<=0)
            player1.y=0;
        if(player1.y >= (gameHeight-playerHeight))
            player1.y = gameHeight-playerHeight;
        if(player2.y<=0)
            player2.y=0;
        if(player2.y >= (gameHeight-playerHeight))
            player2.y = gameHeight-playerHeight;
        if(ball.x <=0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: "+score.player2);
        }
        if(ball.x >= gameWidth-ballDiameter) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: "+score.player1);
        }
    }
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }
}