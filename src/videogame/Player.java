/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Patricio y Diego
 */
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private int speed;
    private Game game;
    private boolean flag;
     /**
     * to create player with every attribute it have
     *
     * @param x to set the x position
     * @param y to set the y position
     * @param direction to set the player direction
     * @param width to set the width of the player
     * @param height to set the height of the player
     * @param Game to set the game where the player is created
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        flag = false;
    }
    /**
     * To get the direction of the player
     * @return an <code>int</code> value with the direction
     */
    public int getDirection() {
        return direction;
    }
   /**
     * To get the width of the player
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    /**
     * To get the height of the player
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
     /**
     * Set the direction of the player
     * @param direction <b>direction</b> value with the direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    /**
     * Set the width of the bullet
     * @param width <b>width</b> value with the width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Set the height of the bullet
     * @param height <b>height</b> value with the height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    @Override
    public void tick() {
        // moving player depending on flags
            if (game.getKeyManager().left) {
                setX(getX() - speed);//Left
                flag=true;
            }
            
            if (game.getKeyManager().right) {
                setX(getX() + speed);//Right
                flag=false;
            }
        
            
            // reset x position and y position if colision
            if (getX() + 150 > game.getWidth()) {
                setX(game.getWidth() - 150);
            } else if (getX() < 0) {
                setX(0);
            }
    }
    
     
    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth() , getHeight());
    }
    
    public boolean intersecta(Object obj) {

        return obj instanceof Bullet && getPerimetro().intersects(((Bullet) obj).getPerimetro());
    }

    @Override
    public void render(Graphics g) {
        //Draw van image depending of the direction of the movement
        if(flag)
        g.drawImage(Assets.vanLeft, getX(), getY(), getWidth(), getHeight(), null);
        else
        g.drawImage(Assets.vanRight, getX(), getY(), getWidth(), getHeight(), null);
            
    }
}
