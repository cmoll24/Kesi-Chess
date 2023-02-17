package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteLoader {
    Image[] images;

    public SpriteLoader(String pathName){
        URL path = this.getClass().getClassLoader().getResource(pathName);
        BufferedImage all = null;
        try { all = ImageIO.read(path); }
        catch (IOException e) { e.printStackTrace(); }

        int tileSize = all.getHeight()/2;
        images = new Image[12];
        int i = 0;
        for (int y = 0; y < 2; y ++) {
            for (int x = 0; x < 6; x ++) {
                images[i] = all.getSubimage(x*tileSize, y*tileSize, tileSize, tileSize).getScaledInstance(64,64, Image.SCALE_DEFAULT);
                i ++;
            }
        }
    }

    public Image getSprite(Type type, boolean isLightColored) {
        int colorOffset = (isLightColored) ? 0:6;
        int index = type.getValue() + colorOffset;
        return images[index];
    }
}