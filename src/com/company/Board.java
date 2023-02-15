package com.company;

public class Board {
    Piece[][] table;

    public Board(){
        table = new Piece[8][8];
        //this.player1 = player1; //temp
        //this.player2 = player2; //temp

        //initialize();
    }

    public void placePiece(int x,int y,Piece piece){
        table[x][y] = piece;
    }

    public Piece[][] getTable() {
        return table;
    }

    public void print(){
        for (int file = 0; file < table.length; file ++) {
            for (int rank = 0; rank < table[0].length; rank ++){
                boolean isLightSquare = (file + rank) % 2 == 0;
                Piece piece = table[file][rank];

                char chr = (piece != null) ? piece.getSymbol() : (isLightSquare) ? '-' : '=';
                System.out.print(" " + chr + " ");
            }
            System.out.print("\n");
        }
    }
}
