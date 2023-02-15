package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public abstract class Piece {
    int x,y;
    char symbol;
    Player player;
    Board board;
    Image sprite;

    public Piece(int x, int y, Player player, Board board){
        this.x = x;
        this.y = y;
        this.player = player;
        this.board = board;
        board.placePiece(x,y,this);
    }

    public void loadSprite(String name){
        URL path = this.getClass().getClassLoader().getResource(name);
        try {
            sprite = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public Image getSprite() {
        return sprite;
    }
}
