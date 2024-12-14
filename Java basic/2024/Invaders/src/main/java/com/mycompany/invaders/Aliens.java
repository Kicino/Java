package com.mycompany.invaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;


public class Aliens {
    
    //definovanie premennych
    private Invaders invaders;
    private PlayerMissile playerMissile;
    private Bariers bariers;
    private Player player;
    
    private ArrayList<Alien> listOfAliens;
    private ArrayList<AlienMissile> listOfAliensMissiles;
    private double speed;
    private int multyplayer;
    private boolean setChange;
    private boolean canChange;
    
    private BufferedImage skull1;
    private BufferedImage skull2;
    private BufferedImage missile1;
    private BufferedImage missile2;
    private int imageInt;
    
    private int ticks;
    
    //nastavenie premennych v konstruktore
    public Aliens(Invaders invaders, PlayerMissile playerMissile, Bariers bariers, Player player){
        
        this.invaders = invaders;
        this.playerMissile = playerMissile;
        this.bariers = bariers;
        this.player = player;
        
        this.listOfAliens = new ArrayList<>();
        this.listOfAliensMissiles = new ArrayList<>();
        this.resetAliens();
        
        this.imageInt = 1;
        try {
            this.skull1 = ImageIO.read(new File("./images/skull1.png"));
            this.skull2 = ImageIO.read(new File("./images/skull2.png"));
            this.missile1 = ImageIO.read(new File("./images/alienMissile1.png"));
            this.missile2 = ImageIO.read(new File("./images/alienMissile2.png"));
        } catch (IOException ex) {
            System.err.println("nemam cestu k obrazkom");
        }
    }
    
    //vykreslenie grafiky
    public void render(Graphics g){
        
        g.setColor(Color.red);
        for(int i = 0; i < this.listOfAliens.size(); i++){
            if(this.imageInt == 1){
                g.drawImage(this.skull1, this.listOfAliens.get(i).getX(), this.listOfAliens.get(i).getY(), this.invaders);
            } else {
                g.drawImage(this.skull2, this.listOfAliens.get(i).getX(), this.listOfAliens.get(i).getY(), this.invaders);  
            }
        }
        
        if(!this.listOfAliensMissiles.isEmpty()){
            this.alienMissileRender(g);
        }
        
    }
    
    //prepocitavanie logiky "ALIENS" a "ALIENMISSILE"
    public void tick(){
        
        //ALIENMISSILE
        this.alienMissileTick();
        
        //ALIENS
        //nahodna sanca na zmeneie obrazka ALIENA
        Random rand = new Random();
        if(rand.nextFloat() < 0.008){
            this.changeImage();
        }
        if(!this.listOfAliens.isEmpty()){
            //zistovanie polohy posledneho plus pohyb dole
            if((this.listOfAliens.getLast().getX() + 90 > this.invaders.getWidth() - 70)){
                this.setChange = true;
                this.canChange = true;
                this.multyplayer = -1;
                  this.ticks++;
            } else if((this.listOfAliens.getLast().getX() + 90 > 300) && (this.listOfAliens.getLast().getX() + 90 < this.invaders.getWidth() / 2)){
                this.canChange = true;
            }
            //zistovanie polohy prveho plus pohyb dole
            if((this.listOfAliens.getFirst().getX() < 100)){
                this.setChange = true;
                this.canChange = true;
                this.multyplayer = 1;
                this.ticks++;
            } else if((this.listOfAliens.getLast().getX() + 90 > 300) && (this.listOfAliens.getLast().getX() + 90 < this.invaders.getWidth() / 2)){
                this.canChange = true;
            }
            //zistovanie ci mozu ist nizsie, alebo uz naburadu do barier
            if(this.setChange && this.canChange){
                for(int i = 0; i < this.listOfAliens.size(); i++){
                    this.listOfAliens.get(i).setY(15);
                }
                this.setChange = false;
                this.canChange = false;
                this.speed += 0.05;
            }

            //zistovanie zastrelenia ALIENA
            for(int i = 0; i < this.listOfAliens.size(); i++){
                if((this.getAlienRectangle(i).intersects(this.playerMissile.getRecnatge(this.playerMissile.getX(), this.playerMissile.getY())))){
                    this.player.setScore(50);
                    this.listOfAliens.remove(i);
                    this.playerMissile.setX(-50);
                    this.playerMissile.setY(-50);
                    this.playerMissile.unsetIsFired();
                }
            }
            
            //zistenie ci alieni dosli ku barieram a vytvaranie missiles
            for(int i = 0; i < this.listOfAliens.size(); i++){
                
                //vytvaranie missiles pre alienov
                if(rand.nextFloat() < 0.0006){
                    this.listOfAliensMissiles.add(new AlienMissile(this.listOfAliens.get(i).getX(), this.listOfAliens.get(i).getY()));
                }
                //zistenie ci uz alieni dosli dole ku barieram
                if(this.listOfAliens.get(i).getY() + 50 > this.invaders.getHeight() - 165){
                    this.invaders.setGameState("DEATHSCREEN");
                    this.listOfAliens.clear();
                    this.resetAliens();
                }
                
                //pohyb alienov
                this.listOfAliens.get(i).setX((int) (2 * this.speed * this.multyplayer));
            }
        }
    }
    //prepocitavanie logiky "ALIENMISSILE"
    private void alienMissileTick(){
        
        if(!this.listOfAliens.isEmpty()){
            for(int i = 0; i < this.listOfAliensMissiles.size(); i++){
                
                this.listOfAliensMissiles.get(i).setY(3);
                this.changeMissile();
                //zabitie hraca
                if(!this.listOfAliensMissiles.isEmpty() && this.getMissileRectangle(this.listOfAliensMissiles.get(i).getX(), this.listOfAliensMissiles.get(i).getY()).intersects(this.player.getRectangle())){
                    this.listOfAliensMissiles.remove(i);
                    this.player.setLives(-1);
                    if(this.player.getLives() == 0){
                        this.invaders.setGameState("DEATHSCREEN");
                        this.resetAliens();                        
                    }

                }
                //vymazanie missiles ak su nizsie ako je vyska obrayovky
                if(!this.listOfAliensMissiles.isEmpty()){
                    if(this.listOfAliensMissiles.get(i).getY() > this.invaders.getHeight()){
                        this.listOfAliensMissiles.remove(i);
                    } else{
                        //zistovanie a vymazavanie missiles od alienov co narazili do barier
                        for(int j = 1; j <= 4; j++){
                            if(!this.listOfAliensMissiles.isEmpty() && this.getMissileRectangle(this.listOfAliensMissiles.get(i).getX(), this.listOfAliensMissiles.get(i).getY()).intersects(this.bariers.getRecnatge((this.invaders.getWidth() / 5 * j) - 55))){
                                this.listOfAliensMissiles.remove(i);
                                break;
                            }
                        } 
                    }
                }
            }
        } else {
            this.resetAliens(); 
        }        
    }
    
    //vykreslenie grafiky
    private void alienMissileRender(Graphics g){
        
        for(int i = 0; i < this.listOfAliensMissiles.size(); i++){
            if(this.listOfAliensMissiles.get(i).getImage() == 0){
                g.drawImage(this.missile1, this.listOfAliensMissiles.get(i).getX(), this.listOfAliensMissiles.get(i).getY(), this.invaders);
            } else {
                g.drawImage(this.missile2, this.listOfAliensMissiles.get(i).getX(), this.listOfAliensMissiles.get(i).getY(), this.invaders);
            }
        }
    }
    
    //zmenenie obrazka ALIENOV
    public void changeImage(){
        
        if(this.imageInt == 1){
            this.imageInt = 0;
        } else {
            this.imageInt = 1;
        }
    }
    
    //zmenenie obrazka ALIENMISSILES
    public void changeMissile(){
        
        for(int i = 0; i < this.listOfAliensMissiles.size(); i++){
            if(this.listOfAliensMissiles.get(i).getImage() == 1){
                this.listOfAliensMissiles.get(i).setImage(0);
            } else if(this.listOfAliensMissiles.get(i).getImage() == 0){
                this.listOfAliensMissiles.get(i).setImage(1);
            }
        }
    }
    
    //vratenie velkosti "HITBOXU" Aliena
    public Rectangle getAlienRectangle(int i){
        
        return new Rectangle(this.listOfAliens.get(i).getX() + 5, this.listOfAliens.get(i).getY(), 45, 30);
    }
    
    //vratenie velkosti "HITBOXU" AlienMissile
    public Rectangle getMissileRectangle(int x, int y){
        
        return new Rectangle(x, y, 8, 26);
    }
    
    //nastavenie povodnych nastaveni hry
    public void resetAliens(){
        
        this.listOfAliens.clear();
        
        this.speed = 0.5; //0.5;
        this.multyplayer = 1;
        this.setChange = false;
        this.canChange = true;
        //pridanie alienov do pola na zaklade velkosti obrazovky
        for(int i = 225; i < this.invaders.getWidth() - 200; i+= 90){
            for(int j = 100; j < 350; j+= 90){
                this.listOfAliens.add(new Alien(i, j));
            }
        }
    }
}
