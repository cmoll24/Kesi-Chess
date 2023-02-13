package com.company;

import javax.swing.*;
import java.awt.Color;

public class Game {
    int ply; //used for counting the number of moves in the game
    Player player1, player2;
    Board board;

    public Game(){
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 528, 551);
        JPanel panel = new ChessWindow();
        frame.add(panel);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);


        player1 = new Player(Color.white);
        player2 = new Player(Color.black);
        board = new Board();
    }

    public void start(){
        System.out.println("The game begins!");
        board.print();
    }
}
