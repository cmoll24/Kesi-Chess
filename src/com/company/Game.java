package com.company;

import com.company.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game {
    private final Player player1, player2;
    private final Board board;
    private final JFrame frame;
    private final ChessWindow window;
    private final Insets borderOffset;

    private int ply; //used for counting the number of moves in the game
    private Piece selectedPiece;
    private Player currentPlayer;
    private final ArrayList<GameState> gameHistory;
    private String moveName; //used for storing the move name

    public Game(){
        ply = 0;
        selectedPiece = null;
        player1 = new Player(true);
        player2 = new Player(false);
        board = new Board();
        gameHistory = new ArrayList<>();
        moveName = new String();

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

                if (piece != null && piece.getPlayer() == currentPlayer) {
                    clickBuffer = selectedPiece != piece;
                    selectedPiece = piece;
                    window.setSelectedPiece(selectedPiece);
                    window.setSelectedSquare(piece.getPosition());
                    window.setPieceDragged(true);
                    frame.repaint();
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

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(KeyEvent.getKeyText(e.getKeyCode()) + " " + e.getKeyCode());

                switch (e.getKeyCode()) {
                    case 37: //left arrow
                        undoMove();
                        break;

                    case 39: //right arrow
                        redoMove();
                        break;

                    case 32: //space bar
                        switchTurn();
                        break;

                    case 80: //letter p
                        printPGN();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
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
            int x = (e.getX() - borderOffset.left) / 64;
            int y = (e.getY() - borderOffset.top) / 64;
            setMoveName(selectedPiece.getPosition(),x,y);
            board.movePiece(selectedPiece,x,y);

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
        saveGameState();
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

    private void setMoveName(Point pos, int x, int y){
        if (moveName.equals("")){
            char mCharX = (char) (pos.getX() + 97);
            char charX = (char) (x + 97);
            int my = (int) pos.getY();
            my = 8 - my;
            y = 8 - y;

            String str = mCharX + "" + my + "" + charX + "" + y;
            moveName = str;
        }
    }

    private void saveGameState(){
        if (ply < gameHistory.size()) { //if there is already a save with this ply remove it
            gameHistory.subList(ply, gameHistory.size()).clear();
        }

        gameHistory.add(new GameState(
                moveName,
                ply,
                board.getTable(),
                player1.getCapturedPieces(),
                player2.getCapturedPieces()
        ));
    }

    private void loadGameState(int index){
        GameState gameState = gameHistory.get(index);

        ply = gameState.getPly();
        board.revertBoard(gameState.getTable());
        player1.setCapturedPieces(gameState.getCapturedPieces1());
        player2.setCapturedPieces(gameState.getCapturedPieces2());
    }

    private void switchTurn(){
        ply ++;
        saveGameState();
        resetCurrentMove();
    }

    private void redoMove(){
        if (ply < gameHistory.size() - 1) {
            loadGameState(ply + 1);
            if (selectedPiece == null) {window.setSelectedSquare(null);}
            resetCurrentMove();
        }
    }

    private void undoMove(){
        if (ply > 0) {
            loadGameState(ply - 1);
            if (selectedPiece == null) {window.setSelectedSquare(null);}
            resetCurrentMove();
        }
    }

    private void resetCurrentMove(){
        moveName = "";
        currentPlayer = (ply % 2 == 0) ? player1 : player2;
        window.setLightTurn(ply % 2 == 0);
        frame.repaint();
    }

    private void printPGN(){ //rudimentary PGN position
        //cannot do promotion or en-passant but can do castling if king is moved first
        System.out.println("PGN:");
        for (GameState gameState : gameHistory) {
            String moveName = gameState.getMoveName();
            System.out.println(moveName);
        }
    }
}
