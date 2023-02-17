package com.company;

public enum Type {
    KING(0),
    QUEEN(1),
    BISHOP(2),
    KNIGHT(3),
    ROOK(4),
    PAWN(5);

    private final int value;
    Type(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
