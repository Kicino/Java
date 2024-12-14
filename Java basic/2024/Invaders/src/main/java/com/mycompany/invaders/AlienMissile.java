package com.mycompany.invaders;


public class AlienMissile {

    //definovanie premennych
    private int x;
    private int y;
    private int image;

    //nastavenie premennych v konstruktore
    public AlienMissile(int x, int y) {
        
        this.x = x;
        this.y = y;
        this.image = 0;
    }
    
    //vratenie hodnoty "X" Aliena
    public int getX(){
        
        return this.x;
    }
    
    //vratenie hodnoty "Y" Aliena
    public int getY(){
        
        return this.y;
    }
    
    //vratenie hodnoty "IMAGE" Aliena
    public int getImage(){
        
        return this.image;
    }
    
    //nastavenie hodnoty "X" Aliena
    public void setX(int x){
        
        this.x += x;
    }
    
    //nastavenie hodnoty "Y" Aliena
    public void setY(int y){
        
        this.y += y;
    }
    
    //nastavenie hodnoty "IMAGE" Aliena
    public void setImage(int i){
        
        this.image = i;
    }
}
