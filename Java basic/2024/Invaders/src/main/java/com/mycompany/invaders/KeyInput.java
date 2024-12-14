package com.mycompany.invaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
       
    //definovanie premennych
    private Invaders invaders;
    private Player player;
    private PlayerMissile playerMissile;
    
    //nastavenie premennych v konstruktore
    public KeyInput(Invaders invaders, Player player, PlayerMissile playerMissile) {
        
        this.invaders = invaders;
        this.player = player;
        this.playerMissile = playerMissile;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    //zistovanie tuknutia klavestnice a urobenie akcie na zaklade stlacenej klavesi
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                if(!(this.player.getX() - 150 <= 0)){
                    this.player.setX(this.player.getX() - 8);
                }
                break;
            case 39:
                if(!(this.player.getX() >= this.invaders.getWidth() - 180)){
                    this.player.setX(this.player.getX() + 8);    
                }
                break;
            case 32:
                if(!this.playerMissile.getIsFired()){
                    this.playerMissile.setX(this.player.getX() + 29);
                    this.playerMissile.setY(this.invaders.getHeight() - 100);
                    this.playerMissile.setIsFired();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
