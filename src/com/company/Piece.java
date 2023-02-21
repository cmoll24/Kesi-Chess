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
        player.addPiece(this);
    }

    public void setPosition(int x,int y){
        mx = x;
        my = y;
    }

    public Type getPieceType() {
        return pieceType;
    }

    public boolean isLightColored() {
        return player.isLightColored();
    }

    public void kill() {
        board.removePiece(mx,my);
    }
}
