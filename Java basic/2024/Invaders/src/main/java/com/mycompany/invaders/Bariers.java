package com.mycompany.invaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bariers {
    
    //definovanie premennych
    private Invaders invaders;
    
    //nastavenie premennych v konstruktore
    public Bariers(Invaders invaders){
        
        this.invaders = invaders;
    }
    
    //vykreslenie grafiky
    public void render(Graphics g){
        
        drawBarier(g, (this.invaders.getWidth() / 5 * 1) - 55);
        drawBarier(g, (this.invaders.getWidth() / 5 * 2) - 55);
        drawBarier(g, (this.invaders.getWidth() / 5 * 3) - 55);
        drawBarier(g, (this.invaders.getWidth() / 5 * 4) - 55);
    }
    
    //podrobne vyhreslenie barier na pozicie prepocitane na velkost obrazovky
    private void drawBarier(Graphics g, int x){
        
        g.setColor(Color.green);
        int[] polygonGunX = {x + 10, x + 100, x + 110, x + 110, x + 95, x + 90, x + 20, x + 15, x, x};
        int[] polygonGunY = {this.invaders.getHeight() - 144, this.invaders.getHeight() - 144, this.invaders.getHeight() - 134, this.invaders.getHeight() - 124, this. invaders.getHeight() - 124, this.invaders.getHeight() - 129, this.invaders.getHeight() - 129, this.invaders.getHeight() - 124, this.invaders.getHeight() - 124, this.invaders.getHeight() - 134};
        g.fillPolygon(polygonGunX,polygonGunY, 10);
    }
    
    //vratenie velkosti "HITBOXU" bariery
    public Rectangle getRecnatge(int x){
        return new Rectangle(x, this.invaders.getHeight() - 144, 110, 10);
    }
}
