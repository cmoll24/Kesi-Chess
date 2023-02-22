package com.company;

import com.company.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class ChessWindow extends JPanel {
    private final Color lightColor, darkColor, selectedColor;
    private final Board board;
    private final SpriteLoader spriteLoader;
    private final SpriteLoader selectedSpriteLoader;

    private Piece selectedPiece;
    private Point selectedSquare;
    private boolean pieceDragged;

    public ChessWindow(Board board){
        this.board = board;
        lightColor = new Color(240,217,181);
        darkColor = new Color(181,136,99);
        selectedColor = Color.yellow;

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Piece[][] table = board.getTable();

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
                            piece.isLightColored());

                    g.drawImage(img,file*64,rank*64,null);
                }
            }
        }

        if (pieceDragged && selectedPiece != null && getMousePosition() != null) {
            int x = getMousePosition().x;
            int y = getMousePosition().y;

            Image img = selectedSpriteLoader.getSprite(
                    selectedPiece.getPieceType(),
                    selectedPiece.isLightColored());

            g.drawImage(img, x-32, y-32, null);
        }
    }


}
