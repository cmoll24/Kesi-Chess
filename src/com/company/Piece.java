package com.company;

public abstract class Piece {
    int mx,my;
    Player player;
    Board board;
    Type pieceType;

    public Piece(int x, int y, Player player, Board board) {
        mx = x;
        my = y;
        this.player = player;
        this.board = board;
        board.placePiece(x,y,this);
    }

    public Type getPieceType() {
        return pieceType;
    }

    public boolean isLightColored() {
        return player.isLightColored();
    }
}
