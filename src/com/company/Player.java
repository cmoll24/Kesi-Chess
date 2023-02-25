package com.company;

import com.company.pieces.Piece;

import java.util.ArrayList;

public class Player {
    boolean lightColored;
    ArrayList<Piece> playerPieces;
    ArrayList<Piece> capturedPieces;

    public Player(boolean lightColored){
        this.lightColored = lightColored;

        playerPieces = new ArrayList<>();
        capturedPieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        playerPieces.add(piece);
    }

    public ArrayList<Piece> getPlayerPieces() {
        return playerPieces;
    }

    public void addCapturedPiece(Piece piece) {
        capturedPieces.add(piece);
    }

    public ArrayList<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public void undoCapture(Board board) {
        ArrayList<Piece> newCapturedPieces = new ArrayList<>();

        for (Piece capturedPiece : capturedPieces) {
            Piece piece = board.getPiece(capturedPiece.getPosition());

            if (piece != capturedPiece) {
                newCapturedPieces.add(capturedPiece);
            }
        }
        capturedPieces = newCapturedPieces;
    }

    public boolean isLightColored() {
        return lightColored;
    }
}
