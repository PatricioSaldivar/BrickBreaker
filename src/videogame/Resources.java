package videogame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patricio y Diego
 */
public class Resources {

    private Game game;
    private int size;
    private LinkedList<PowerUp> powerUps;
    public Resources(Game game) {
        this.game = game;
    }

    void saveGame() {
        try {
            //Creates new file to save the game data
            FileWriter fw = new FileWriter("save.txt");
            //Saves every value of every object in the game
            fw.write(String.valueOf(game.getScore()) + "\n");
            fw.write(String.valueOf(game.getPlayer().getX()) + "\n");
            fw.write(String.valueOf(game.getPlayer().getY()) + "\n");
            fw.write(String.valueOf(game.getBullet().getX()) + "\n");
            fw.write(String.valueOf(game.getBullet().getY()) + "\n");
            fw.write(String.valueOf(game.getBullet().getVelX()) + "\n");
            fw.write(String.valueOf(game.getBullet().getVelY()) + "\n");
            fw.write(String.valueOf(game.getBricksOnGame()) + "\n");
            for (int i = 0; i < game.getCountBricks(); i++) {
                fw.write(String.valueOf(game.getBricks().get(i).getX()) + "\n");
                fw.write(String.valueOf(game.getBricks().get(i).getY()) + "\n");
                fw.write(String.valueOf(game.getBricks().get(i).getIndex()) + "\n");
            }
            fw.write(String.valueOf(game.getPowerUps().size()) + "\n");
            for (int i = 0; i < game.getPowerUps().size(); i++) {
                fw.write(String.valueOf(game.getPowerUps().get(i).getX()) + "\n");
                fw.write(String.valueOf(game.getPowerUps().get(i).getY()) + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loadGame() {
        try {
            //Loads file where every value of the game was saved
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            //Read every value in the file so it can be loaded
            game.setScore(Integer.parseInt(br.readLine()));
            game.getPlayer().setX(Integer.parseInt(br.readLine()));
            game.getPlayer().setY(Integer.parseInt(br.readLine()));
            game.getBullet().setX(Integer.parseInt(br.readLine()));
            game.getBullet().setY(Integer.parseInt(br.readLine()));
            game.getBullet().setVelX(Integer.parseInt(br.readLine()));
            game.getBullet().setVelY(Integer.parseInt(br.readLine()));
            game.setBricksOnGame(Integer.parseInt(br.readLine()));
            for (int i = 0; i < game.getCountBricks(); i++) {
                game.getBricks().get(i).setX(Integer.parseInt(br.readLine()));
                game.getBricks().get(i).setY(Integer.parseInt(br.readLine()));
                game.getBricks().get(i).setIndex(Integer.parseInt(br.readLine()));
            }
            powerUps = new LinkedList<PowerUp>();
            size = Integer.parseInt(br.readLine());
            for (int i = 0; i < size; i++) {
                PowerUp power = new PowerUp(Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine()), game);
                powerUps.add(power);
            }
            game.setPowerUps(powerUps);
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
