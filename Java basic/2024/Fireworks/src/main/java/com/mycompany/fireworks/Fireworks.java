package com.mycompany.fireworks;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Fireworks extends Canvas implements Runnable{

    //zadefinovanie premennych pre triedu fireworks
    private static Fireworks fireworks;
    
    private boolean running = false;
    private Thread thread;
    private int width;
    private int height;
    
    private static JFrame frame;
    
    public static ArrayList<Rocket> listOfRockets;
    public static ArrayList<Particles> listOfParticles;
    
    public static void main(String[] args) {
        
        fireworks = new Fireworks();
        fireworks.listOfRockets = new ArrayList<>();
        fireworks.listOfParticles = new ArrayList<>();
        
        //ziskanie velkosti obrazovky
        Toolkit tk = Toolkit.getDefaultToolkit();
        fireworks.width = (int) tk.getScreenSize().getWidth();
        fireworks.height = (int) tk.getScreenSize().getHeight();
        
        //nastavenie a zobarzenie okna
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Fireworks");
        frame.add(fireworks);
        
        fireworks.setPreferredSize(new Dimension(fireworks.width, fireworks.height));        
        frame.pack();
        
        fireworks.start();
    }
     
    public void init(){

        requestFocus();
        
        //priradenie čítačov na myšku a klávestnicu
        this.fireworks.addMouseListener(new MouseInput(this));
        this.fireworks.addKeyListener(new KeyInput());
    }
    
    private synchronized void start(){
        //zapnutie vlákna na opakovanie cyklu
        if (this.running){
            return; 
        }
        
        this.running = true;
        this.thread = new Thread(this);
        this.thread.setName("main thread");
        this.thread.start();
    }
    
    private synchronized void stop(){
        //vypnutie vlákna na ukončenie cyklu
        if (!this.running){
            return;
        }
        this.running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void run() {
        
        //nastavenie pocty tikov na opakovanie v cykluse
        init();
        long lastTime = System.nanoTime();
        final double amoutOfTicks = 60.0;
        double ns = 1000000000 / amoutOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(this.running){
            //hlavný cyklus na opakovanie programu
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//                System.out.println(updates  + " Ticks, fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        //prepocitavanie premmenych a informácii
        Rocket.tick();
        Particles.tick();
    }
    
    private void render(){
        //vykreslovanie objektov
        BufferStrategy bs = getBufferStrategy();
        
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        ////graphics
        
        g.setColor(Color.black);
        g.fillRect(0, 0, this.fireworks.width, this.fireworks.height);
        
        Rocket.render(g);
        Particles.render(g);
        
        //graphics
        g.dispose();
        bs.show();
    }
}

    