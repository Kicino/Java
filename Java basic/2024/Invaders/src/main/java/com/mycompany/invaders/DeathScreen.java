package com.mycompany.invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DeathScreen {
    
    //definovanie premennych
    private Invaders invaders;
    private Player player;
    
    private int ticksToMenu;
    
    //nastavenie premennych v konstruktore
    public DeathScreen(Invaders invaders, Player player){
        
        this.invaders = invaders;
        this.player = player;
        
        this.ticksToMenu = 360;
    }
    
    //vykreslenie grafiky
    public void render(Graphics g){  
        
        Font fnt0 = new Font("monospaced", Font.BOLD, 80);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("MISSION ACCOMPLISHED", this.invaders.getWidth() / 2 - 465, 200);
        Font fnt1 = new Font("monospaced", Font.BOLD, 55);
        g.setFont(fnt1);
        g.drawString("SCORE: " + this.player.getScore(), this.invaders.getWidth() / 2 - 150, 500);
    }
    
    //prepocitavanie logiky DEATHSCREEN
    public void tick(){
        
       this.ticksToMenu--;
       if(this.ticksToMenu == 0){
           this.ticksToMenu = 360;
           this.invaders.setGameState("MENU");
       }
    }
}


