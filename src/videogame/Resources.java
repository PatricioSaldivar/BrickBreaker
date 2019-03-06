package videogame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PatoSaldivar
 */
public class Resources {
    private Game game;
    public Resources(Game game){
        this.game= game;
    }
    void saveGame(){
        try {
            FileWriter fw = new FileWriter("save.txt");
             
            fw.write(String.valueOf(game.getScore())+"\n");
            fw.write(String.valueOf(game.getPlayer().getX())+"\n");
            fw.write(String.valueOf(game.getPlayer().getY())+"\n");
            fw.write(String.valueOf(game.getBullet().getX())+"\n");
            fw.write(String.valueOf(game.getBullet().getY())+"\n");
            fw.write(String.valueOf(game.getBullet().getVelX())+"\n");
            fw.write(String.valueOf(game.getBullet().getVelY())+"\n");
            for(int i=0; i<game.getCountBricks(); i++){
                fw.write(String.valueOf(game.getBricks().get(i).getX()) +"\n");
                fw.write(String.valueOf(game.getBricks().get(i).getY()) +"\n");
                fw.write(String.valueOf(game.getBricks().get(i).isDead()) +"\n");
            }
            
            fw.close();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       void loadGame(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            
           
            game.setScore(Integer.parseInt(br.readLine()));
            game.getPlayer().setX(Integer.parseInt(br.readLine()));
            game.getPlayer().setY(Integer.parseInt(br.readLine()));
            game.getBullet().setX(Integer.parseInt(br.readLine()));
            game.getBullet().setY(Integer.parseInt(br.readLine()));
            game.getBullet().setVelX(Integer.parseInt(br.readLine()));
            game.getBullet().setVelY(Integer.parseInt(br.readLine()));
            for(int i=0; i<game.getCountBricks(); i++){
                game.getBricks().get(i).setX(Integer.parseInt(br.readLine()));
                game.getBricks().get(i).setY(Integer.parseInt(br.readLine()));
                game.getBricks().get(i).setDead(Boolean.parseBoolean((br.readLine())));
            }
            
            
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
