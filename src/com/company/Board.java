package com.company;

public class Board {
    Piece[][] table;
    Player player1, player2;

    public Board(Player player1, Player player2){
        table = new Piece[8][8];
        this.player1 = player1; //temp
        this.player2 = player2; //temp

        initialize();
    }

    public void initialize(){

        Piece pawn1 = new Pawn(0,0, player1); //temp
        table[0][0] = pawn1;
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
