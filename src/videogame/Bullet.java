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
 * @author yeyog
 */
public class Bullet extends Item {
    
    private int velX;                       //to store the direction in X
    private int velY;                       //to store the direction in Y
    private int width;                      //to store the width of the bullet
    private int height;                     //to store the height of the bullet
    private Game game;                      //to store the Game
    private Animation animationBullet;      //to store the animation of the bullet
    
    public Bullet(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        velX = 5;
        velY = -5;
        this.animationBullet = new Animation(Assets.bullet,100);
    }
    
    /**
     * To get the direction in X of the bullet
     * @return an <code>int</code> value with the direction
     */
    public int getVelX() {
        return velX;
    }
    
    /**
     * To get the direction in Y of the bullet
     * @return an <code>int</code> value with the direction
     */
    public int getVelY() {
        return velY;
    }
    
    /**
     * To get the width of the bullet
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    
     /**
     * To get the height of the bullet
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the direction in X of the bullet
     * @param velX <b>velX</b> value with the direction
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }
    
     /**
     * Set the direction in Y of the bullet
     * @param velY <b>velY</b> value with the direction
     */
    public void setVelY(int velY) {
        this.velY = velY;
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
        //change animation of the bullet
        this.animationBullet.tick();
        
            // change direction of x position and y position if colision
            if (getX() + 50 > game.getWidth()) {
                setVelX(-getVelX());
            } else if (getX() < 0) {
                setVelX(-getVelX());
            }
                    
            if(this.intersecta(game.getPlayer())){
                setVelY(-getVelY());
            }
            
            if (getY() + 50 > game.getHeight()) {
                setY(game.getPlayer().getY()-50);
                setX(game.getPlayer().getX());
                setVelX(getVelX());
                setVelY(-getVelY());
            } else if (getY() < 0) {
                setVelY(-getVelY());
            }
            
            //set direction of movement of the bullet
            setX(getX() + velX);
            setY(getY() + velY);
    }
    
     public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
     
      public boolean intersecta(Object obj) {
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }
    
     @Override
    public void render(Graphics g) {
        g.drawImage(animationBullet.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
