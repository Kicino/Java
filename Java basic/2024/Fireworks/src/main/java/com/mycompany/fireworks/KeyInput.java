package com.mycompany.fireworks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //skoncenie programu pri stlaceni klavesy "ESC"
        if(e.getKeyCode() == 27){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
