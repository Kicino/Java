package com.mycompany.invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
    
    //definovanie premennych
    private Invaders invaders;
    
    private String text;
    private String textWriten;
    private Rectangle backButton;
    
    //nastavenie premennych v konstruktore
    public Help(Invaders invaders){
        
        this.invaders = invaders;
        this.text = "In this game you play as a rocket in space, your role is to destroy all of your enemies. GOOD LUCK! ";
        this.textWriten = " ";
        this.backButton = new Rectangle(this.invaders.getWidth() / 2 - 125, this.invaders.getHeight() - 250, 250, 70);
    }
    
    //vykreslenie grafiky
    public void render(Graphics g){
        
        Graphics2D g2D = (Graphics2D) g;
        
        Font fnt0 = new Font("monospaced", Font.BOLD, 80);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("HELP", this.invaders.getWidth() / 2 - 95, 200);
        
        Font ftn1 = new Font ("monospaced", Font.BOLD, 20);
        g.setFont(ftn1);
        g.drawString(this.textWriten, this.invaders.getWidth() / 2 - 600, this.invaders.getHeight() - 450);
        
        Font ftn2 = new Font ("monospaced", Font.BOLD, 50);
        g.setFont(ftn2);
        g.drawString("Back", this.backButton.x + 62, this.backButton.y + 48);
        g2D.draw(this.backButton);
    }
    
    //prepocitavanie logiky HELP
    public void tick(){
        
        String text = "";
        if(this.textWriten.length() != this.text.length()){
            for(int i = this.textWriten.length() - 1; i < this.textWriten.length(); i++){
                text += this.text.charAt(i);
            }
            this.textWriten += text;
        }
    }
}

