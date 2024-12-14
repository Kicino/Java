package com.mycompany.fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Particles {
    
    //zadefinovalnie premennych triedy
    private int x;
    private int y;
    private Color color;
    private int deathTick;
    private int [][] particle;
    
    public Particles(int x, int y){
        
        /*vytvorenie noveho objedku triedy, do ktoreho priradime suradnice X a Y z objektu 
          rakety, nastavenie nahodnej farby a dlzky zivotnosti. Vygenerovanie pozicii X a Y samostatným
          particlom, ktorych je 260 v poli this.particle
        */
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.deathTick = rand.nextInt(130) + 50;
        this.particle = new int[261][2];
        for(int i = 0; i < 261; i++){
            this.particle[i][0] = x + 2;
            this.particle[i][1] = y + 2;
        }
    }
    
    public static void tick(){
        //prepocitanie pozicii samostatnych particlov v poli this.particle
        if(!Fireworks.listOfParticles.isEmpty()){
            Random rand = new Random();
            for(int i = 0; i < Fireworks.listOfParticles.size(); i++){
                for(int j = 0; j < 261; j++){
                    switch (rand.nextInt(4)) {
                        case 0:
                            Fireworks.listOfParticles.get(i).particle[j][0] += (int) (2 * rand.nextInt(5));
                            Fireworks.listOfParticles.get(i).particle[j][1] += (int) (2 * rand.nextInt(5));
                            break;
                        case 1:
                            Fireworks.listOfParticles.get(i).particle[j][0] += (int) (2 * rand.nextInt(5));
                            Fireworks.listOfParticles.get(i).particle[j][1] -= (int) (2 * rand.nextInt(5));
                            break;    
                        case 2:
                            Fireworks.listOfParticles.get(i).particle[j][0] -= (int) (2 * rand.nextInt(5));
                            Fireworks.listOfParticles.get(i).particle[j][1] += (int) (2 * rand.nextInt(5));
                            break;    
                        case 3:
                            Fireworks.listOfParticles.get(i).particle[j][0] -= (int) (2 * rand.nextInt(5));
                            Fireworks.listOfParticles.get(i).particle[j][1] -= (int) (2 * rand.nextInt(5));
                            break;    
                        default:
                    }
                }
                //prepocitavanie casu k smrti a vymazanie objektu ak sa to dostalo k hodnote this.deathtick
                Fireworks.listOfParticles.get(i).deathTick--;
                if(Fireworks.listOfParticles.get(i).deathTick == 0){
                    Fireworks.listOfParticles.remove(i);
                }
            }
        }
    }
    
    public static void render(Graphics g){
        //vykreslenie samostatnych particlov z pola this.particle na ich samostatnych poziciach X a Y
        if(!Fireworks.listOfParticles.isEmpty()){
            for(int i = 0; i < Fireworks.listOfParticles.size(); i++){
                g.setColor(Fireworks.listOfParticles.get(i).color);
                for(int j = 1; j < Fireworks.listOfParticles.get(i).particle.length; j++){
                    g.fillRect(Fireworks.listOfParticles.get(i).particle[j][0], Fireworks.listOfParticles.get(i).particle[j][1], 4, 4);
                }
            }
        }
    }
}
