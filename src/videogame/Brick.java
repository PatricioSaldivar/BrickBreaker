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
public class Brick extends Item {

    private int width;
    private int height;
    private Game game;
    private int index;
    private boolean dead;
    public Brick(int x, int y, int width, int height, Game game, int index) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.index = index;
        dead= false;
    }

    /**
     *
     * @return
     */


    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
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


    @Override
    public void tick() {
    
    }

    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.brick[index], getX(), getY(), getWidth(), getHeight(), null);
    }
}
