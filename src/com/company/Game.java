package com.company;

import com.company.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game {
    private final Player player1, player2;
    private final Board board;
    private final JFrame frame;
    private final ChessWindow window;
    private final Insets borderOffset;

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

        int fWidth = 528+64;//528;
        int fHeight= 551;

        frame = new JFrame();
        frame.setTitle("Kesi Chess");
        frame.setBounds(10, 10, fWidth, fHeight);
        frame.setMinimumSize(new Dimension(fWidth,fHeight));

        window = new ChessWindow(fWidth, fHeight, board, player1, player2);
        frame.add(window);
        frame.addMouseListener(new MouseListener() {
            boolean clickBuffer = false; //buffer so you cannot unselect a piece on the same click you select it

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                Piece piece = getPiece(e);

                if (piece != null) {
                    if (selectedPiece == null || piece.getPlayer() == selectedPiece.getPlayer()) { //== currentPlayer
                        clickBuffer = selectedPiece != piece;
                        selectedPiece = piece;
                        window.setSelectedPiece(selectedPiece);
                        window.setSelectedSquare(piece.getPosition());
                        window.setPieceDragged(true);
                        frame.repaint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                window.setPieceDragged(false);

                Piece piece = getPiece(e);
                if (selectedPiece != null) {
                    if (selectedPiece != piece && (piece == null || piece.getPlayer() != selectedPiece.getPlayer())) {
                        movePiece(e);
                    } else if (selectedPiece == piece && !clickBuffer) {
                        selectedPiece = null;
                        window.setSelectedPiece(null);
                        window.setSelectedSquare(null);
                    }
                }
                clickBuffer = false;
                frame.repaint();
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

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                    (e.getX() - borderOffset.left) / 64,
                    (e.getY() - borderOffset.top) / 64
            );
            selectedPiece = null;
            window.setSelectedPiece(null);
            frame.repaint();
            //switchTurn();
        }
    }

    private void initialize(){
        String setup = "RNBQKBNRpppppppp";

        for (int y = 0; y < 2; y ++){
            for (int x = 0; x < 8; x ++) {
                char c = setup.charAt(y*8+x);
                createPiece(c,x,y,player2);
            }
        }
        for (int y = 0; y < 2; y ++){
            for (int x = 0; x < 8; x ++) {
                char c = setup.charAt(y*8+x);
                createPiece(c,x,7-y,player1);
            }
        }
    }

    private void createPiece(char c, int x, int y, Player player) {
        switch (c) {
            case 'K':
                new King(x, y, player, board);
                break;
            case 'Q':
                new Queen(x, y, player, board);
                break;
            case 'B':
                new Bishop(x, y, player, board);
                break;
            case 'N':
                new Knight(x, y, player, board);
                break;
            case 'R':
                new Rook(x, y, player, board);
                break;
            case 'p':
                new Pawn(x, y, player, board);
                break;
        }
    }

    private void switchTurn(){
        ply ++;
        currentPlayer = (ply % 2 == 0) ? player1 : player2;
    }
}
