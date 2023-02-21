package com.company;

import javax.swing.*;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game {
    int ply; //used for counting the number of moves in the game

    Player player1, player2;
    Board board;
    JFrame frame;
    ChessWindow window;
    Insets borderOffset;

    Piece selectedPiece;
    Player currentPlayer;

    public Game(){
        ply = 0;
        selectedPiece = null;
        player1 = new Player(true);
        player2 = new Player(false);
        board = new Board();

        frame = new JFrame();
        frame.setBounds(10, 10, 528, 551);

        window = new ChessWindow(board);
        frame.add(window);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                Piece piece = board.getPiece(
                        (e.getX() - borderOffset.left) / 64,
                        (e.getY() - borderOffset.top) / 64
                );

                if (piece != null && piece.player == currentPlayer) {
                    selectedPiece = piece;
                    window.setSelectedPiece(selectedPiece);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (selectedPiece != null){
                    board.movePiece(
                            selectedPiece,
                            (e.getX() - borderOffset.left) / 64,
                            (e.getY() - borderOffset.top) / 64
                    );
                    selectedPiece = null;
                    window.setSelectedPiece(null);
                    ply ++;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) { }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        borderOffset = frame.getInsets();

        initialize();
    }

    public void initialize(){
        for (int x = 0; x < 8; x ++) { //temp
            new Pawn(x,1, player2, board);
        }
        new Pawn(5, 5, player1, board);
    }

    public void start(){
        System.out.println("The game begins!");

        frame.repaint();
        board.movePiece(board.getPiece(1,1), 1, 3);
        frame.repaint();

        while (true) {
            currentPlayer =  (ply % 2 == 0) ? player1 : player2;
            frame.repaint();
        }
    }
}
