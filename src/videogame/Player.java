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
 * @author antoniomejorado
 */
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private int speed;
    private Game game;
    private boolean flag;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        flag = false;
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
        if(flag)
        g.drawImage(Assets.vanLeft, getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(Assets.vanRight, getX(), getY(), getWidth(), getHeight(), null);
            
    }
}
