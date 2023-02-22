package com.company.pieces;

import com.company.Board;
import com.company.Player;

import java.awt.Point;

public abstract class Piece {
    private int x,y;
    private final Player player;
    private final Board board;
    private final Type pieceType;

    public Piece(int x, int y, Player player, Board board, Type pieceType) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.board = board;
        this.pieceType = pieceType;
        board.placePiece(x,y,this);
        player.addPiece(this);
    }

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Point getPosition() {
        return new Point(x,y);
    }

    public Player getPlayer() {
        return player;
    }

    public Type getPieceType() {
        return pieceType;
    }

    public boolean isLightColored() {
        return player.isLightColored();
    }

    public void kill() {
        board.removePiece(x,y);
    }
}
