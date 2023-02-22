package com.company.pieces;

import com.company.Board;
import com.company.Player;

public class King extends Piece{
    public King(int x, int y, Player player, Board board) {
        super(x, y, player, board, Type.KING);
    }
}
