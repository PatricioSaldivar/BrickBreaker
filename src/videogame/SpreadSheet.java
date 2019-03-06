/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Patricio y Diego
 */
public class SpreadSheet {
        private BufferedImage sheet;    //to store the spritesheet
    
    /**
     * Create a new spritesheet
     * @param sheet the <code>image</code> with the sprites
     */
    public SpreadSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    /**
     * Crop a sprite from the spritesheet
     * @param x and <code>int</code> value with the x coordinate
     * @param y and <code>int</code> value with the y coordinate
     * @param width and <code>int</code> value with the width of the sprite
     * @param heightand <code>int</code> value with the height of the sprite
     * @return
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
    
}
