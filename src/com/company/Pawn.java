package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Pawn extends Piece {
    public Pawn(int x, int y, Player player, Board board) {
        super(x, y, player, board);
        symbol = 'p';

        char color = (player.isLightColored()) ? 'l' : 'd';
        String name = "images//64px-Chess_tile_p"+color+".png";
        loadSprite(name);
    }
}
