package com.kodilla.game;

import java.awt.*;
import java.awt.event.*;

public class Player extends Rectangle{

    int id;
    int yVelocity;
    int speed = 7;
    Color player1 = new Color(255, 0, 0);
    Color player2 = new Color(21, 10, 171);

    Player(int x, int y, int playerWidth, int playerHeight, int id){
        super(x,y,playerWidth,playerHeight);
        this.id=id;
    }

    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y= y + yVelocity;
    }
    public void draw(Graphics g) {
        if(id==1)
            g.setColor(player1);
        else
            g.setColor(player2);
        g.fillRect(x, y, width, height);
    }
}