package com.company;

public class Board {
    private final Piece[][] table;

    public Board(){
        table = new Piece[8][8];
    }

    public void placePiece(int file,int rank,Piece piece){
        table[file][rank] = piece;
    }

    public void removePiece(int file,int rank){
        table[file][rank] = null;
    }

    public Piece[][] getTable() {
        return table;
    }

    public Piece getPiece(int x,int y) {
        if (x < 8 && y < 8){
            return table[x][y];
        } else {
            return null;
        }
    }

    public void movePiece(Piece piece, int x,int y){
        if (x < 8 && y < 8) {
            removePiece(x,y);
            piece.kill();
            table[x][y] = piece;
            piece.setPosition(x, y);
        }
    }
}
