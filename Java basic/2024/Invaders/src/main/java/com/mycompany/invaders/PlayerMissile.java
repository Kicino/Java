package com.mycompany.invaders;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class PlayerMissile {
    
    //definovanie premennych
    private int x; 
    private int y;
    private boolean isFired;
    private BufferedImage missile;
    
    private Bariers bariers;
    private Invaders invaders;
    
    //nastavenie premennych v konstruktore
    public PlayerMissile(int x, int y, Invaders invaders,  Bariers bariers){
        
        this.x = x;
        this.y = y;
        try {
            this.missile = ImageIO.read(new File("./images/playerMissile.png"));
        } catch (IOException ex) {
            System.err.println("neviem nacitat obrazky");
        }
        
        this.bariers = bariers;
        this.invaders = invaders;
    }
    
    //vykrslenie grafiky
    public void render(Graphics g){
        
        g.drawImage(this.missile, this.x, this.y, this.invaders);
    }
    
    //prepocitavanie logiky "PLAYERMISSILE"
    public void tick(){
        
        if(this.y <= 100){
            this.x = -50;
            this.y = -50;
            this.isFired = false;
        } else {
            this.y -= 10;
            for(int i = 1; i <= 4; i++){
                if(this.getRecnatge(this.x, this.y).intersects((this.bariers.getRecnatge((this.invaders.getWidth() / 5 * i) - 55)))){
                    this.x = -50;
                    this.y = -50;
                    this.isFired = false;
                }
            }
        }
    }
    
    
    //nastavenie hodnoty "X" PlayerMissile
    public void setX(int x){
        
        this.x = x;
    }
    
     //nastavenie hodnoty "Y" PlayerMissile
    public void setY(int y){
        
        this.y = y;
    }
    
    //vratenie hodnoty "X" PlayerMissile
    public int getX(){
        
        return this.x;
    }
    
    //vratenie hodnoty "Y" PlayerMissile
    public int getY(){
        
        return this.y;
    }
    
    //vratenie hodnoty "ISFIRED" PlayerMissile
    public boolean getIsFired(){
        
        return this.isFired;
    }
    
    //nastavenie hodnoty "ISFIRED" PlayerMissile na TRUE
    public void setIsFired(){
        
       this.isFired = true;
    }
    
    //nastavenie hodnoty "ISFIRED" PlayerMissile na FALSE
    public void unsetIsFired(){
        
       this.isFired = false;
    }
    
    //vratenie velkosti "HITBOXU" PlayerMissile
    public Rectangle getRecnatge(int x, int y){
        return new Rectangle(x, y, 7, 15);
    }
    
    //vyresetovanie na zakladne nastavenia 
    public void reset(){
        
        this.x = -50;
        this.y = -50;
    }
}
