package com.company.pieces;

import com.company.Board;
import com.company.Player;

public class Queen extends Piece{
    public Queen(int x, int y, Player player, Board board) {
        super(x, y, player, board, Type.QUEEN);
    }
}
