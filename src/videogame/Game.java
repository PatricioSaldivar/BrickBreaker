/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private Bullet bullet;          // to use a bullet
    private LinkedList<Brick> bricks;                // to use the bricks
    private KeyManager keyManager;  // to manage the keyboard
    private int score;                 // to manage score

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        // creating my bricks list
        bricks = new LinkedList<Brick>();
        score = 0;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }    

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2 - 75, getHeight() - 50, 1, 150, 50, this);
        bullet = new Bullet( player.getX()+(player.getWidth()/2) , player.getY()-20, 20, 20, this);
        int iPosX;
        int iPosY;
        int iRen;
        int iCol;
        int iInd;
        // Se escoje una mitad con direccion izquierda y la otra a la derecha
        for (int i = 0; i < getWidth()/60; i++) {
            for (int j = 0; j < (getHeight()*3/5)/30; j= j+2) {
                iInd = i % 4;
                iPosX = i * 60;
                iPosY = j* 30;
                bricks.add(new Brick(iPosX, iPosY, 60, 30, this, iInd));
            }
        }

        display.getJframe().addKeyListener(keyManager);


    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {
        keyManager.tick();
        // avancing player and bricks and check collisions 
        if (!keyManager.pause && keyManager.start) {
            player.tick();
            bullet.tick();
            //Set direction of bullet depending on which part of the player the bullet hits
            if(player.intersecta(bullet)){
                bullet.setVelY(-bullet.getVelY());
            if((bullet.getX() > (player.getX() - player.getWidth() / 2)) && (bullet.getX() < (player.getX() + player.getWidth() / 2)) && (bullet.getY() < player.getY()) && (bullet.getY() > (player.getY() - player.getHeight() /2))){
                bullet.setVelX(-Math.abs(bullet.getVelX()));
             } else {
                 bullet.setVelX(Math.abs(bullet.getVelX()));
             }
            }

            // If there are no more bricks in the game (When yo get 650 points), active bullet.EndGame to show Game Over image
            if(this.getScore() == 650){
                bullet.setEndGame(true);
            }        
            for (int i = 0; i < bricks.size(); i++) {
                Brick brick = bricks.get(i);
                brick.tick();
                if (bullet.intersecta(brick)) {
                    bullet.setVelY(-bullet.getVelY());
                    this.setScore(this.getScore() + 10);
                    Assets.applause.play();
                    brick.setY(-60);
   
                }
            }
        }
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);

        }
        else
        {   
            g = bs.getDrawGraphics();
           
            //Shows Game Over image and stops games if the bullet get to the bottom of the display of you win
            if(bullet.isEndGame()){
                g.drawImage(Assets.gameOver,0,0,width,height,null);
            } else {
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            bullet.render(g);
            for (int i = 0; i < bricks.size(); i++) {
                Brick brick = bricks.get(i);
                brick.render(g);
            }
            //Set font color to white for the text of Lifes Left:
            g.setColor(Color.white);

            g.drawString("Score:"+this.getScore(), getWidth()-100, getHeight()-20);
            }
                bs.show();
            
                g.dispose();
            
        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
