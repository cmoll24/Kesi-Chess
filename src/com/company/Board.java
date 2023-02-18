package com.company;

public class Board {
    Piece[][] table;

    public Board(){
        table = new Piece[8][8];
    }

    public void placePiece(int file,int rank,Piece piece){
        table[file][rank] = piece;
    }

    public Piece[][] getTable() {
        return table;
    }

    public void movePiece(int mx, int my, int x,int y){
        table[x][y] = table[my][my];
        table[my][my] = null;
    }
}
