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
    
    private int direction;
    private int width;
    private int height;
    private int speed;
    private Game game;
    private Animation animationBullet;      //to store the animation of the bullet
    
    public Bullet(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        this.animationBullet = new Animation(Assets.bullet,100);
    }
    
     public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
    @Override
    public void tick() {
        // moving bullet depending on flags
        this.animationBullet.tick();
            if (game.getKeyManager().Q) {
                setY(getY() - speed);//UP
                setX(getX() - speed);//Left
            }
            if (game.getKeyManager().A) {
                setY(getY() + speed); //Down
                setX(getX() - speed);//Left
            }
            if (game.getKeyManager().E) {
                setY(getY() - speed);//UP
                setX(getX() + speed);//Right
            }
            if (game.getKeyManager().D) {
                setY(getY() + speed); //Down
                setX(getX() + speed); //Right
            }
            
            // reset x position and y position if colision
            if (getX() + 150 > game.getWidth()) {
                setX(game.getWidth() - 150);
            } else if (getX() < 0) {
                setX(0);
            }
            if (getY() + 150 > game.getHeight()) {
                setY(game.getHeight() - 150);
            } else if (getY() < 0) {
                setY(0);
            }
    }
    
     public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
     
     @Override
    public void render(Graphics g) {
        g.drawImage(animationBullet.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
