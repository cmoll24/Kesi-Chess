package com.company.pieces;

import com.company.Board;
import com.company.Player;

public class Rook extends Piece{
    public Rook(int x, int y, Player player, Board board) {
        super(x, y, player, board, Type.ROOK);
    }
}
