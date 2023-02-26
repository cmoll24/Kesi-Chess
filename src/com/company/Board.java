package com.company;

import com.company.pieces.Piece;

public class Board {
    private Piece[][] table;

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
            //System.out.println("moving from " + piece.getPosition().x + ' ' + piece.getPosition().y + " to " + x + ' ' + y);

            if (table[x][y] != null) {
                Player player = piece.getPlayer();
                player.addCapturedPiece(table[x][y]);
            }

            removePiece(x,y);
            piece.kill();
            table[x][y] = piece;
            piece.setPosition(x, y);
        }
    }

    public void revertBoard(Piece[][] newTable){
        table = newTable;

        for (int file = 0; file < table.length; file ++) {
            for (int rank = 0; rank < table.length; rank ++) {
                Piece piece = table[rank][file];
                if (piece != null) {
                    piece.setPosition(rank, file);
                }
            }
        }
    }

    private void printTable(Piece[][] table) { //debug method
        for (int file = 0; file < table.length; file ++) {
            for (int rank = 0; rank < table.length; rank ++) {
                Piece piece = table[rank][file];
                if (piece != null) {
                    System.out.print(piece.getPieceType().toString().charAt(0) + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
