package com.company;

import com.company.pieces.Piece;

import java.awt.Point;
import java.util.ArrayList;

public class Board {
    private Piece[][] table;
    private final ArrayList<Piece[][]> boardHistory;

    public Board(){
        table = new Piece[8][8];
        boardHistory = new ArrayList<>();
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

    public boolean hasHistory() {
        return (boardHistory.size() > 1);
    }

    public Piece getPiece(int x,int y) {
        if (x < 8 && y < 8){
            return table[x][y];
        } else {
            return null;
        }
    }
    public Piece getPiece(Point point) {
        int x = point.x;
        int y = point.y;
        return getPiece(x,y);
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

    public void recordBoard(){
        boardHistory.add(cloneTable(table));
    }

    public void revertBoard(){
        if (boardHistory.size() <= 1) {return;}

        int index = boardHistory.size() - 1; //current position
        table = cloneTable(boardHistory.get(index - 1)); //revert to before current position
        boardHistory.remove(index); //remove current position

        for (int file = 0; file < table.length; file ++) {
            for (int rank = 0; rank < table.length; rank ++) {
                Piece piece = table[rank][file];
                if (piece != null) {
                    piece.setPosition(rank, file);
                }
            }
        }
    }

    private Piece[][] cloneTable(Piece[][] table) {
        Piece [][] newTable = new Piece[table.length][];
        for(int i = 0; i < table.length; i++)
            newTable[i] = table[i].clone();
        return newTable;
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
