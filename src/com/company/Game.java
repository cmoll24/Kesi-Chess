package com.company;

import javax.swing.*;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game {
    private final Player player1, player2;
    private final Board board;
    private final JFrame frame;
    private final ChessWindow window;
    private Insets borderOffset;

    private int ply; //used for counting the number of moves in the game
    private Piece selectedPiece;
    private Player currentPlayer;

    public Game(){
        ply = 0;
        selectedPiece = null;
        player1 = new Player(true);
        player2 = new Player(false);
        board = new Board();
        currentPlayer = player1;

        frame = new JFrame();
        frame.setBounds(10, 10, 528, 551);

        window = new ChessWindow(board);
        frame.add(window);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click");
                Piece piece = getPiece(e);

                /*if (piece != null && piece.getPlayer() == currentPlayer) { // you click on one of your own pieces
                    if (selectedPiece == null) { // you have no selected piece
                        selectedPiece = piece;
                        window.setSelectedPiece(selectedPiece);

                    } else if (selectedPiece == piece){ // you click on a piece you have already selected
                        selectedPiece = null;
                        window.setSelectedPiece(null);
                    }
                } else */
                if (selectedPiece != piece) { // you click on a square to move your piece to
                    movePiece(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Piece piece = getPiece(e);

                if (piece != null && piece.getPlayer() == currentPlayer) {
                    selectedPiece = piece;
                    window.setSelectedPiece(selectedPiece);
                }
                frame.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Piece piece = getPiece(e);

                if (selectedPiece != piece) {
                    movePiece(e);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null) {
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        borderOffset = frame.getInsets();

        initialize();
    }

    private Piece getPiece(MouseEvent e) {
        return board.getPiece(
                (e.getX() - borderOffset.left) / 64,
                (e.getY() - borderOffset.top) / 64
        );
    }

    private void movePiece(MouseEvent e) {
        if (selectedPiece != null) {
            board.movePiece(
                    selectedPiece,
                    (e.getX() - borderOffset.top) / 64,
                    (e.getY() - borderOffset.top) / 64
            );
            selectedPiece = null;
            window.setSelectedPiece(null);
            frame.repaint();
            switchTurn();
        }
    }

    private void initialize(){
        for (int x = 0; x < 8; x ++) { //temp
            new Pawn(x,1, player2, board);
        }
        new Pawn(5, 5, player1, board);
    }

    public void test(){
        System.out.println("The game begins!");
        board.movePiece(board.getPiece(1,1), 1, 3);
        frame.repaint();
    }

    private void switchTurn(){
        ply ++;
        currentPlayer =  (ply % 2 == 0) ? player1 : player2;
    }
}
