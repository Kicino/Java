package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == 38 && Snake.move != "DOWN"){
            Snake.move = "UP";
        } else if(e.getKeyCode() == 39 && Snake.move != "LEFT"){
            Snake.move = "RIGHT";
        } else if(e.getKeyCode() == 40 && Snake.move != "UP"){
            Snake.move = "DOWN";
        } else if (e.getKeyCode() == 37 && Snake.move != "RIGHT"){
            Snake.move = "LEFT";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
