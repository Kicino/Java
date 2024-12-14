package com.mycompany.invaders;

public class Alien {
    
    //definovanie premennych
    private int x;
    private int y;
    
    //nastavenie premennych v konstruktore
    public Alien(int x, int y){
        
        this.x = x;
        this.y = y;
    }
    
    //vratenie hodnoty "X" Aliena
    public int getX(){
        
        return this.x;
    }
    
    //vratenie hodnoty "Y" Aliena
    public int getY(){
        
        return this.y;
    }
    
    //nastavenie hodnoty "X" Aliena
    public void setX(int x){
        
        this.x += x;
    }
    
    //nastavenie hodnoty "Y" Aliena
    public void setY(int y){
        
        this.y += y;
    } 
}
