package com.company;

public class Board {
    int[][] table;

    public Board(){
        table = new int[8][8];
    }

    public void initialize(){

    }

    public void print(){
        for (int file = 0; file < table.length; file ++) {
            for (int rank = 0; rank < table[0].length; rank ++){
                boolean isLightSquare = (file + rank) % 2 == 0;
                char chr = (isLightSquare) ? '-' : '=';
                System.out.print(" "+chr+" ");
            }
            System.out.print("\n");
        }
    }
}
