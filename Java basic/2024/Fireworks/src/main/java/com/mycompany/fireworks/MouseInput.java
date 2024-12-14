package com.mycompany.fireworks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class MouseInput implements MouseListener{

    //zadefinovanie premennych triedy
    private Fireworks Fireworks;
    
    public MouseInput(Fireworks fireworks){
        //priradenie premennej this.fireworks objekt fireworks
        this.Fireworks = fireworks;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //vygenerovanie novej rakety na pozicii stlačenia myšky a priradenie objektu do pola
        this.Fireworks.listOfRockets.add(new Rocket(e.getX() - 2, e.getY() - 7));
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
