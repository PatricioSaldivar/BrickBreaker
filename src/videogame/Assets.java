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

    public static BufferedImage sprites;    // to store the sprites
    public static BufferedImage bullet[];   // pictures of the bullet changing color
    public static BufferedImage vanLeft;     // to store the player image
    public static BufferedImage vanRight;     // to store the player image
<<<<<<< HEAD

    public static BufferedImage brick[];
=======
    public static BufferedImage gameOver;      //to store gameover image
    public static BufferedImage brick;
>>>>>>> refs/remotes/origin/Diego
    public static SoundClip boo;
    public static SoundClip applause;

    /**
     * initializing the images of the game
     */
    public static void init() {
        brick = new BufferedImage[4];
        background = ImageLoader.loadImage("/images/Background.png");
<<<<<<< HEAD
        brick[0] = ImageLoader.loadImage("/images/Pill1.png");
        brick[1] = ImageLoader.loadImage("/images/Pill2.png");
        brick[2] = ImageLoader.loadImage("/images/Pill3.png");
        brick[3] = ImageLoader.loadImage("/images/Pill4.png");
=======
        brick = ImageLoader.loadImage("/images/Pill.png");
        gameOver = ImageLoader.loadImage("/images/gameover.jpg");
>>>>>>> refs/remotes/origin/Diego

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

        vanLeft = ImageLoader.loadImage("/images/VanLeft.png");
        vanRight = ImageLoader.loadImage("/images/VanRight.png");

    }

}
