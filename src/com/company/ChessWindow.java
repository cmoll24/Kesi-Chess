package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

public class ChessWindow extends JPanel {
    Color lightColor, darkColor;

    public ChessWindow(){
        lightColor = Color.white;
        darkColor = Color.black;

        //URL path = getClass().getResource(name);
        //mImage = ImageIO.read(path);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        for (int file = 0; file < 8; file ++) {
            for (int rank = 0; rank < 8; rank ++){
                boolean isLightSquare = (file + rank) % 2 == 0;
                Color color = isLightSquare ? lightColor : darkColor;
                g.setColor(color);
                g.fillRect(file*64,rank*64,64,64);
            }
        }
    }
}
