package com.company;

import com.company.pieces.Piece;
import com.company.pieces.Type;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessWindow extends JPanel {
    private final Color lightColor, darkColor, selectedColor;
    private final Board board;
    private final SpriteLoader spriteLoader;
    private final SpriteLoader selectedSpriteLoader;

    private Piece selectedPiece;
    private Point selectedSquare;
    private boolean pieceDragged;
    private boolean isLightTurn;

    private final Player player1, player2; //temp
    private final int fWidth;
    private final int fHeight;

    public ChessWindow(int fWidth, int fHeight, Board board, Player player1, Player player2){
        this.fWidth = fWidth;
        this.fHeight = fHeight;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;

        lightColor = new Color(240,217,181);
        darkColor = new Color(181,136,99);
        selectedColor = Color.yellow;
        isLightTurn = true;

        spriteLoader = new SpriteLoader("images/384px-Chess_Pieces_Sprite.png");
        selectedSpriteLoader = new SpriteLoader("images/384px-Chess_Pieces_Sprite_selected.png");
    }

    public void setSelectedSquare(Point selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    public void setPieceDragged(boolean pieceDragged) {
        this.pieceDragged = pieceDragged;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void setLightTurn(boolean lightTurn) {
        isLightTurn = lightTurn;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Piece[][] table = board.getTable();

        // ------- DRAWING THE TABLE --------
        for (int file = 0; file < table.length; file ++) {
            for (int rank = 0; rank < table.length; rank ++){
                Color color;

                if (selectedSquare != null && selectedSquare.x == file && selectedSquare.y == rank) {
                    color = selectedColor;
                } else {
                    boolean isLightSquare = (file + rank) % 2 == 0;
                    color = isLightSquare ? lightColor : darkColor;
                }

                g.setColor(color);
                g.fillRect(file*64,rank*64,64,64);

                Piece piece = table[file][rank];
                if (piece != null && (piece != selectedPiece || !pieceDragged)) {
                    Image img = spriteLoader.getSprite(
                            piece.getPieceType(),
                            piece.isLightColored()
                    );
                    g.drawImage(img,file*64,rank*64,null);
                }
            }
        }

        //------- DRAWING THE CAPTURED PIECES --------
        drawCapturedPieces(g, player1, false);
        drawCapturedPieces(g, player2, true);

        //------- TURN COLOR MARKER --------
        Image img = spriteLoader.getSprite(Type.PAWN,isLightTurn);
        img = img.getScaledInstance(32,32,Image.SCALE_DEFAULT);
        g.drawImage(img,fWidth-64,fHeight/2-32,null);

        // ------- DRAWING THE SELECTED PIECE --------
        if (pieceDragged && selectedPiece != null && getMousePosition() != null) {
            int x = getMousePosition().x;
            int y = getMousePosition().y;

            img = selectedSpriteLoader.getSprite(
                    selectedPiece.getPieceType(),
                    selectedPiece.isLightColored());

            g.drawImage(img, x-32, y-32, null);
        }
    }

    private void drawCapturedPieces(Graphics g, Player player, boolean isOpponent){
        ArrayList<Piece> capturedPieces = player.getCapturedPieces();

        int heightOffset = (isOpponent) ? 0 : fHeight-72;
        int coef = (isOpponent) ? 1 : -1;

        for (int i = 0; i < capturedPieces.size(); i++){
            Piece piece = capturedPieces.get(i);
            Image img = spriteLoader.getSprite(
                    piece.getPieceType(),
                    piece.isLightColored()
            );
            img = img.getScaledInstance(32,32,Image.SCALE_DEFAULT);

            int widthOffset = (i>=8) ? -32 : 0;
            g.drawImage(img,fWidth-48+widthOffset,heightOffset+((i%8)*32*coef),null);
        }
    }

}
