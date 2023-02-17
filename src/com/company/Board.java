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
}
