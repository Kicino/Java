package com.mycompany.fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Rocket {
    
    //zadefinovanie premennych pre triedu
    private int x;
    private int y;
    private int speed;
    private int boomHeight;
    
    public Rocket(int x, int y){
        //vytvorenie objektu raketa s X a Y podla pozicie myšky a nahodne vygenerovanie výšky výbuchu
        this.x = x;
        this.y = y;
        this.speed = (new Random().nextInt(5) + 3);
        this.boomHeight = new Random().nextInt(200) + 100;
    }
    
    public static void tick(){
        //prepocitanie vysky rakety
        if(!Fireworks.listOfRockets.isEmpty()){
            for(int i = 0; i < Fireworks.listOfRockets.size(); i++){
                Fireworks.listOfRockets.get(i).y -= Fireworks.listOfRockets.get(i).speed; 
                //zistovanie ci ma raketa vybuchnut, ak ano, tak ju vymazeme
                if(Fireworks.listOfRockets.get(i).y < Fireworks.listOfRockets.get(i).boomHeight){
                    Fireworks.listOfParticles.add(new Particles(Fireworks.listOfRockets.get(i).x, Fireworks.listOfRockets.get(i).y));
                    Fireworks.listOfRockets.remove(i);
                }
            }
        }
    }
    
    public static void render(Graphics g){
        //vykresnie rakety
        if(!Fireworks.listOfRockets.isEmpty()){
            for(int i = 0; i < Fireworks.listOfRockets.size(); i++){
                g.setColor(Color.red);
                g.fillRect(Fireworks.listOfRockets.get(i).x, Fireworks.listOfRockets.get(i).y, 5, 15);
            }
        }
    }
}
