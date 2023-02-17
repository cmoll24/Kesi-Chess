package com.company;

public class Pawn extends Piece {
    public Pawn(int x, int y, Player player, Board board) {
        super(x, y, player, board);
        pieceType = Type.PAWN;
    }
}
