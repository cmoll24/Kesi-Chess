package com.company;

import com.company.pieces.Piece;

import java.util.ArrayList;

public class Player {
    boolean lightColored;
    ArrayList<Piece> playerPieces;

    public Player(boolean lightColored){
        this.lightColored = lightColored;

        playerPieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        playerPieces.add(piece);
    }

    public ArrayList<Piece> getPlayerPieces() {
        return playerPieces;
    }

    public boolean isLightColored() {
        return lightColored;
    }
}
