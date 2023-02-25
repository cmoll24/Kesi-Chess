package com.company.pieces;

import com.company.Board;
import com.company.Player;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    int startingY;

    public Pawn(int x, int y, Player player, Board board) {
        super(x, y, player, board, Type.PAWN);
        startingY = y;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);

        if (abs(startingY-y) == 6){ //the pawn has moved 6 squares
            new Queen(x,y,player,board);
        }
    }
}
