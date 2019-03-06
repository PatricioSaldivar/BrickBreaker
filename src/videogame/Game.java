/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author Patricio y Diego
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
    private ArrayList<Brick> bricks;                // to use the bricks
    private KeyManager keyManager;  // to manage the keyboard
    private int score;                 // to manage score
    private boolean win;            // to manage if the game is ended and you win
    private int countBricks;        // To store the total of bricks
    private Resources resources;    //To load and save the information of the game

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
        resources= new Resources(this);

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
    /**
     * To get the score of the game
     *
     * @return an <code>int</code> value with the score
     */
    public int getScore() {
        return score;
    }
    /**
     * Set the score
     * @param score <b>score</b> value with the score
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * To get the bullet object
     *
     * @return an <code>bullet</code> value with the Bullet
     */
    public Bullet getBullet() {
        return bullet;
    }
    /**
     * To get the count of bricks
     *
     * @return an <code>int</code> value with brick count
     */
    public int getCountBricks() {
        return countBricks;
    }
    /**
     * Set the brick counter
     * @param countBricks <b>countBrciks</b> value with the brick counter
     */
    public void setCountBricks(int countBricks) {
        this.countBricks = countBricks;
    }
     /**
     * To get the bricks object
     *
     * @return an <code>ArrayList</code> value with bricks object
     */
    public ArrayList<Brick> getBricks() {
        return bricks;
    }
    

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2 - 75, getHeight() - 50, 1, 150, 50, this);
        bullet = new Bullet(player.getX() + (player.getWidth() / 2), player.getY() - 20, 20, 20, this);
        int iPosX;
        int iPosY;
        int iRen;
        int iCol;
        int iInd;
        win = false;
        // creating my bricks list
        bricks = new ArrayList<Brick>();
        score = 0;
        keyManager.setStart(false);
        // Se escoje una mitad con direccion izquierda y la otra a la derecha
        for (int i = 1; i < getWidth() / 60 ; i++) {
            for (int j = 1; j < (getHeight() * 3 / 5) / 30 ; j++) {
                iInd = i % 4;
                iPosX = i * 60;
                iPosY = j * 30;
                countBricks = countBricks + 1;
                bricks.add(new Brick(iPosX, iPosY, 60, 30, this, iInd));
            }
        }

        display.getJframe().addKeyListener(keyManager);

    }

    private void reset() {
        //Restart every value and object in the game 
        player.setX(getWidth() / 2 - 75);
        player.setY(getHeight() - 50);
        bullet.setX(player.getX() + (player.getWidth() / 2));
        bullet.setY(player.getY() - 20);
        bullet.setVelY(-3);
        bullet.setEndGame(false);
        int iPosX;
        int iPosY;
        int iRen;
        int iCol;
        int iInd;
        // creating my bricks list
        bricks = new ArrayList<Brick>();
        score = 0;
        keyManager.setStart(false);
        countBricks = 0;
        // Se escoje una mitad con direccion izquierda y la otra a la derecha
        for (int i = 1; i < getWidth() / 60; i++) {
            for (int j = 1; j < (getHeight() * 3 / 5) / 30; j++) {
                iInd = i % 4;
                iPosX = i * 60;
                iPosY = j * 30;
                countBricks = countBricks + 1;
                bricks.add(new Brick(iPosX, iPosY, 60, 30, this, iInd));
            }
        }
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
        if ((win || bullet.isEndGame()) && keyManager.start) {
            this.reset();
            win = false;
        } else {
            keyManager.tick();
            if(keyManager.save){
                resources.saveGame();
            }
            if(keyManager.load){
                resources.loadGame();
            }

            // avancing player and bricks and check collisions 
            if (!keyManager.pause && keyManager.start) {
                player.tick();
                bullet.tick();
                //Set direction of bullet depending on which part of the player the bullet hits
                if (player.intersecta(bullet)) {
                    bullet.setVelY(-Math.abs(bullet.getVelY()));
                    if ((bullet.getX() < (player.getX() + player.getWidth() / 2))) {
                        bullet.setVelX(-Math.abs(bullet.getVelX()));
                    } else {
                        bullet.setVelX(Math.abs(bullet.getVelX()));
                    }
                }
                //creates bricks      
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    brick.tick();
                    //checks if bullet intersects brick
                    if (bullet.intersecta(brick) && !brick.isDead()) {
                        //change direction of bullet depending where it hits in X
                        if ((bullet.getX() < (brick.getX() + brick.getWidth() / 2))) {
                            bullet.setVelX(-Math.abs(bullet.getVelX()));
                        } else {
                            bullet.setVelX(Math.abs(bullet.getVelX()));
                        }
                        //change direction of bullet depending where it hits in Y
                        if ((bullet.getY() > (brick.getY() + brick.getHeight() / 2))) {
                            bullet.setVelY(Math.abs(bullet.getVelY()));
                        } else {
                            bullet.setVelY(-Math.abs(bullet.getVelY()));
                        }
                        //Add points to score
                        this.setScore(this.getScore() + 10);
                        //Plays sound everytime bullet hits brick
                        Assets.bounce.play();
                        brick.setDead(true);
                        

                    }
                }

                // If there are no more bricks in the game (When yo get 650 points), active bullet.EndGame to show Game Over image
                if (this.getScore() == countBricks * 10) {
                    win = true;
                    keyManager.setStart(false);
                    Assets.applause.play();
                }
                if(bullet.isEndGame()){
                    keyManager.setStart(false);
                    Assets.boo.play();
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

        } else {
            g = bs.getDrawGraphics();

            //Shows Game Over image and stop  the game if the bullet get to the bottom of the display
            if (bullet.isEndGame()) {
                g.drawImage(Assets.gameOver, 0, 0, width, height, null);
                //Set font color to white for the text of Lifes Left:
                g.setColor(Color.white);
                g.drawString("Press any button to restart", getWidth() / 2 - 90, getHeight() - 50);
            //Shows You Win image and stop the game if there are not more bricks in the game
            } else if (win) {
                g.drawImage(Assets.youWin, 0, 0, width, height, null);
                g.setColor(Color.white);
                g.drawString("Press any button to restart", getWidth() / 2 - 90, getHeight() - 50);
            //If the game hasn´t end, the game is painted
            } else {
                g.drawImage(Assets.background, 0, 0, width, height, null);
                player.render(g);
                bullet.render(g);
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if(!brick.isDead()){
                    brick.render(g);
                    }
                }
                //Set font color to white for the text of Lifes Left:
                g.setColor(Color.white);
                g.drawString("Score:" + this.getScore(), getWidth() - 100, getHeight() - 20);
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
