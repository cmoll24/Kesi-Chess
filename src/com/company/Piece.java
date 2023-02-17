package com.company;

public abstract class Piece {
    int x,y;
    Player player;
    Board board;
    Type pieceType;

    public Piece(int file, int rank, Player player, Board board) {
        this.x = file;
        this.y = rank;
        this.player = player;
        this.board = board;
        board.placePiece(file,rank,this);
    }

    public Type getPieceType() {
        return pieceType;
    }

    public boolean isLightColored() {
        return player.isLightColored();
    }
}
