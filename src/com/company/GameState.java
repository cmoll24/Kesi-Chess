package com.company;

import com.company.pieces.Piece;

import java.util.ArrayList;

public class GameState {
    private final String moveName;
    private final int ply;
    private final Piece[][] table;
    private final ArrayList<Piece> capturedPieces1;
    private final ArrayList<Piece> capturedPieces2;

    public GameState(String moveName, int ply, Piece[][] table, ArrayList<Piece> capturedPieces1, ArrayList<Piece> capturesPieces2){
        this.moveName = moveName;
        this.ply = ply;
        this.table = cloneTable(table);
        this.capturedPieces1 = (ArrayList<Piece>) capturedPieces1.clone();
        this.capturedPieces2 = (ArrayList<Piece>) capturesPieces2.clone();
    }

    public String getMoveName() {
        return moveName;
    }

    public int getPly() {
        return ply;
    }

    public Piece[][] getTable() {
        return cloneTable(table);
    }

    public ArrayList<Piece> getCapturedPieces1() {
        return (ArrayList<Piece>) capturedPieces1.clone();
    }

    public ArrayList<Piece> getCapturedPieces2() {
        return (ArrayList<Piece>) capturedPieces2.clone();
    }

    private Piece[][] cloneTable(Piece[][] table) {
        Piece [][] newTable = new Piece[table.length][];
        for(int i = 0; i < table.length; i++)
            newTable[i] = table[i].clone();
        return newTable;
    }
}
