/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage van;     // to store the player image
    public static BufferedImage sprites;    // to store the sprites
    public static BufferedImage bullet[];   // pictures of the bullet changing color
    public static BufferedImage brick;
    public static SoundClip boo;
    public static SoundClip applause;
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.png");
        brick = ImageLoader.loadImage("/images/Pill.png");
        van = ImageLoader.loadImage("/images/Van.png");
        //getting the sprites from the picture
        sprites = ImageLoader.loadImage("/images/bullet_enemy.png");
        boo = new SoundClip ("/sounds/Boo.wav");
        applause = new SoundClip ("/sounds/Applause.wav");
        //creating array of images before animations
        SpreadSheet spritesheet = new SpreadSheet(sprites);
        bullet = new BufferedImage[8];
        //croping the pictures from the sheet into the array
        for(int i = 0; i < 8 ; i++){
            bullet[i] = spritesheet.crop(i*64,0,64,64);
        }
    }
    
}
