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
    public static BufferedImage bullet;
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
        boo = new SoundClip ("/sounds/Boo.wav");
        applause = new SoundClip ("/sounds/Applause.wav");
    }
    
}
