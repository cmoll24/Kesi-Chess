package com.company;

public abstract class Piece {
    int x,y;
    char symbol;
    Player player;

    public Piece(int x, int y, Player player){
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public char getSymbol() {
        return symbol;
    }
}
