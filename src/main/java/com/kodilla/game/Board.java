package com.kodilla.game;

import java.awt.*;
import javax.swing.*;

public class Board extends JFrame{

    GamePanel board;

    Board(){
        board = new GamePanel();
        this.add(board);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setBackground(Color.darkGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}