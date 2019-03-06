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
public class PowerUp extends Item {

    private int width;
    private int height;
    private Game game;
    private int playerWidth;
    private int timer;
    

    public PowerUp(int x, int y, Game game) {
        super(x, y);
        width = 30;
        height = 30;
        this.game = game;
        playerWidth= game.getPlayer().getWidth()* 4 / 3;
        timer=0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getPlayerWidth() {
        return playerWidth;
    }
    

    @Override
    public void tick() {   
        y=y+1; 
    }

    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean intersecta(Object obj) {
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }

    @Override
    public void render(Graphics g) {
    g.drawImage(Assets.powerUp, getX(), getY(), getWidth(), getHeight(), null);
    }

}
