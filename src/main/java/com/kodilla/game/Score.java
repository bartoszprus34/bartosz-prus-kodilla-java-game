package com.kodilla.game;

import java.awt.*;

public class Score extends Rectangle {

    static int gameWidth;
    static int gameHeight;
    int player1;
    int player2;
    Color scoreColor = new Color(164, 128, 4);

    Score(int gameWidth, int gameHeight) {
        Score.gameWidth = gameWidth;
        Score.gameHeight = gameHeight;
    }

    public void draw(Graphics g) {
        g.setColor(scoreColor);
        g.setFont(new Font("Consolas", Font.PLAIN, 70));

        g.drawLine(gameWidth / 2, 0, gameWidth / 2, gameHeight);

        g.drawString(player1 / 10 + String.valueOf(player1 % 10), (gameWidth / 2) - 330, 50);
        g.drawString(player2 / 10 + String.valueOf(player2 % 10), (gameWidth / 2) + 285, 50);
    }
}