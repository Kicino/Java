package com.mycompany.invaders;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    //definovanie premennych
    private Invaders invaders;
    private Player player;
    private PlayerMissile playerMissile;
    
    //nastavenie premennych v konstruktore
    public MouseInput(Invaders invaders, Player player, PlayerMissile playerMissile) {
        
        this.invaders = invaders;
        this.player = player;
        this.playerMissile = playerMissile;
    }
    
    //zistovanie pozicie mysky a urobenie akcie na zaklade pozicie tuknutia
    @Override
    public void mouseClicked(MouseEvent e) {
        
        switch (this.invaders.getGameState()) {
            
            //akcie ktore sa maju vykonat na obrazovke "MENU"
            case MENU:
                if((e.getX() > this.invaders.getWidth() / 2 - 125) && (e.getX() < this.invaders.getWidth() / 2 - 125 + 250)){
                    if((e.getY() > 400) && (e.getY() < 470)){
                        this.invaders.setGameState("GAME");
                        this.player.reset();
                        this.playerMissile.reset();
                    } else if((e.getY() > 550) && (e.getY() < 620)){
                        this.invaders.setGameState("HELP");
                    } else if ((e.getY() > 700) && (e.getY() < 770)){
                        System.exit(0);
                    }
                }
                break;
            //akcie ktore sa maju vykonat na obrazovke "HELP"
            case HELP:
                if((e.getX() > this.invaders.getWidth() / 2 - 125) && (e.getX() < this.invaders.getWidth() / 2 - 125 + 250)){
                    if((e.getY() > this.invaders.getHeight() - 250) && (e.getY() < this.invaders.getHeight() - 180)){
                        this.invaders.setGameState("MENU");
                    }
                }
                break;
            default:
                break;
        }
       
    }
        
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
