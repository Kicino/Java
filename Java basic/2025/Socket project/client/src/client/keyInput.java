package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author PC
 */
public class keyInput implements KeyListener{

    private Client client;
    
    public keyInput(Client client) {
        
        this.client = client;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    //jednoduche ovladanie X a Y suradnic - gulicka sa posuva, pokial si tlacitka stlacene
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                this.client.setHorisontalMovement(-5);
                break;
            case 38:
                this.client.setVerticalMovement(-5);
                break;
            case 39:
                this.client.setHorisontalMovement(5);
                break;
            case 40:
                this.client.setVerticalMovement(5);
                break;
        }
        this.client.repaint();
    }

    //vypnutie posuvania gulicky pri nedrzani tlacitok
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                this.client.setHorisontalMovement(0);
                break;
            case 38:
                this.client.setVerticalMovement(0);
                break;
            case 39:
                this.client.setHorisontalMovement(0);
                break;
            case 40:
                this.client.setVerticalMovement(0);
                break;
        }
    }
    
}
