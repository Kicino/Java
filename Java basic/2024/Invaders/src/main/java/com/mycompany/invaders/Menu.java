package com.mycompany.invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
    
    //definovanie premennych
    private Invaders invaders;
    
    private Rectangle playButton;
    private Rectangle helpButton;
    private Rectangle quitButton;
    
    //nastavenie premennych v konstruktore
    public Menu(Invaders invaders){
        
        this.invaders = invaders;
        this.setButtons();
    }
    
    //nastavenie rozmerov a pozicii tlacitok v MENU
    private void setButtons(){
        
        this.playButton = new Rectangle(this.invaders.getWidth() / 2 - 125, 400, 250, 70);
        this.helpButton = new Rectangle(this.invaders.getWidth() / 2 - 125, 550, 250, 70);
        this.quitButton = new Rectangle(this.invaders.getWidth() / 2 - 125, 700, 250, 70);
    }
    
    //vykreslenie grafiky
    public void render(Graphics g){
        
        Graphics2D g2D = (Graphics2D) g;
        
        Font fnt0 = new Font("monospaced", Font.BOLD, 80);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Space Invaders", this.invaders.getWidth() / 2 - 330, 120);
        
        Font ftn1 = new Font ("monospaced", Font.BOLD, 50);
        g.setFont(ftn1);
        g.drawString("Play", this.playButton.x + 62, this.playButton.y + 48);
        g.drawString("Help", this.helpButton.x + 62, this.helpButton.y + 48);
        g.drawString("Quit", this.quitButton.x + 62, this.quitButton.y + 48);
        g2D.draw(this.playButton);
        g2D.draw(this.helpButton);
        g2D.draw(this.quitButton);
    }
}

