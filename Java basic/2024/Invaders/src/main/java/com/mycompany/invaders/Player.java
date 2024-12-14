package com.mycompany.invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player {
    
    //definovanie premennych
    private Invaders invaders;
    private int x;
    private int score;
    private int lives;
    
    private BufferedImage heart;
    
    //nastavenie premennych v konstruktore
    public Player(Invaders invaders){
        
        this.invaders = invaders;
        try {
            this.heart = ImageIO.read(new File("./images/heart.png"));
        } catch (IOException ex) {
            System.err.println("nemam nacitane obrazky");
        }
        
        this.reset();
    }
    
    //vykreslenie grafiky
    public void render(Graphics g){
        
        //vykreslenie score
        Font fnt0 = new Font("monospaced", Font.BOLD, 35);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SCORE: " + this.score, this.invaders.getWidth() - 300, 40);
        
        //vykresneie poctu srdiecok
        g.drawString(Integer.toString(this.lives), 120, 40);
        g.setColor(Color.green);
        for(int i = 1; i < this.lives + 1; i++){
            g.drawImage(this.heart, (i * 50) + 115, 15, this.invaders);
        }
        
        //vykreslenie rakety
        g.fillOval(this.x, this.invaders.getHeight() - 95, 65, 25);
        int[] polygonBodyX = {this.x + 1, this.x + 1, this.x + 20, this.x + 45, this.x + 65, this.x + 65};
        int[] polygonBodyY = {this.invaders.getHeight() - 85, this.invaders.getHeight() - 40, this.invaders.getHeight() - 65, this.invaders.getHeight() - 65, this.invaders.getHeight() - 40, this.invaders.getHeight() - 85};
        g.fillPolygon(polygonBodyX,polygonBodyY, 6);
        
        int[] polygonGunX = {this.x + 20, this.x + 45, this.x + 35, this.x + 30};
        int[] polygonGunY = {this.invaders.getHeight() - 92, this.invaders.getHeight() - 92, this.invaders.getHeight() - 105, this.invaders.getHeight() - 105};
        g.fillPolygon(polygonGunX,polygonGunY, 4);
    }
    
    //vratenie hodnoty "X" Player
    public int getX(){
        
        return this.x;
    }
    
    //nastavenie hodnoty "Y" Player
    public void setX(int x){
        
        this.x = x;
    }
    
    //nastavenie hodnoty "SCORE" Player
    public void setScore(int score){
        
        this.score += score;
    }
    
    //nastavenie hodnoty "LIVES" Player
    public void setLives(int live){
        
        this.lives += live;
    }
    
    //vratenie hodnoty "LIVES" Player
    public int getLives(){
        
        return this.lives;
    }
    
    //vratenie hodnoty "SCORE" Player
    public int getScore(){
        
        return this.score;
    }
    
    //vyresetovanie na zakladne nastavenia 
    public void reset(){
        
        this.x = this.invaders.getWidth() / 2 - 20;
        this.score = 0;
        this.lives = 3;
    }
    
    //vratenie velkosti "HITBOXU" bariery
    public Rectangle getRectangle(){
        
        return new Rectangle(this.x, this.invaders.getHeight() - 95, 65, 60);
    }
}
