package com.company;

import javax.swing.*;
import java.awt.*;

public class ChessWindow extends JPanel {
    Color lightColor, darkColor;
    Board board;
    SpriteLoader spriteLoader;

    public ChessWindow(Board board){
        this.board = board;
        lightColor = new Color(240,217,181);
        darkColor = new Color(181,136,99);

        spriteLoader = new SpriteLoader("images//384px-Chess_Pieces_Sprite.png");
    }

    @Override
    public void paint(Graphics g) {
        Piece[][] table = board.getTable();

        for (int file = 0; file < table.length; file ++) {
            for (int rank = 0; rank < table.length; rank ++){
                boolean isLightSquare = (file + rank) % 2 == 0;
                Color color = isLightSquare ? lightColor : darkColor;
                g.setColor(color);
                g.fillRect(file*64,rank*64,64,64);

                Piece piece = table[file][rank];
                if (piece != null) {
                    Image img = spriteLoader.getSprite(
                            piece.getPieceType(),
                            piece.isLightColored());

                    g.drawImage(img,file*64,rank*64,null);
                }
            }
        }
    }
}
