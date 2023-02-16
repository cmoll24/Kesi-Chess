package com.company;

import javax.swing.*;
import java.awt.Color;
import java.io.IOException;

public class Game {
    int ply; //used for counting the number of moves in the game
    Player player1, player2;
    Board board;
    JFrame frame;

    public Game(){
        player1 = new Player(true);
        player2 = new Player(false);
        board = new Board();

        frame = new JFrame();
        frame.setBounds(10, 10, 528, 551);
        JPanel panel;
        panel = new ChessWindow(board);
        frame.add(panel);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        initialize();
    }

    public void initialize(){
        for (int x = 0; x < 8; x ++) { //temp
            new Pawn(x,1, player2, board);
        }
    }

    public void start(){
        System.out.println("The game begins!");
        new Pawn(5,5,player1, board);
        frame.repaint();
    }
}
