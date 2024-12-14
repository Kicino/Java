package com.mycompany.invaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Invaders extends Canvas implements Runnable{

    //definovanie premennych objektov
    private static Invaders invaders;
    private Player player;
    private PlayerMissile playerMissile;
    private Bariers bariers;
    private Aliens aliens;
    private Menu menu;
    private Help help;
    private DeathScreen deathScreen;
    private GameState gameState;
    
    //definovanie premennych na chod vlakna
    private boolean running = false;
    private Thread thread;
    
    //definovanie premennych na vykreslenie okna
    private static JFrame frame;
    private int width;
    private int height;
    
    //definovanie premennych
    private int updates;
                
    public static void main(String[] args) {
        
        invaders = new Invaders();
        //priradenie velkosti obrazoviek do premennych
        Toolkit tk = Toolkit.getDefaultToolkit();
        invaders.width = (int) tk.getScreenSize().getWidth();
        invaders.height = (int) tk.getScreenSize().getHeight();
        
        //nastavenie okna a jeho funkcii
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.setResizable(false);
        invaders.setPreferredSize(new Dimension(invaders.width, invaders.height)); 
        frame.setTitle("Space Invaders");
        ImageIcon icon = new ImageIcon("./images/icon.png");
        frame.setIconImage(icon.getImage());
        frame.add(invaders);
        frame.pack();
        
        invaders.start();
    }
    
    //inicializovanie premennych a potrebnych veci na chod hry
    private void init(){
        
        //priradenie objektov do premennych so spravami z hravlej triedy
        this.bariers = new Bariers(this);
        this.player = new Player(this);
        this.playerMissile = new PlayerMissile(-50, -50, this, this.bariers);
        this.aliens = new Aliens(this, this.playerMissile, this.bariers, this.player);
        
        this.menu = new Menu(this);
        this.deathScreen = new DeathScreen(this, this.player);
        this.help = new Help(this);
        
        this.gameState = GameState.MENU;

        //pridanie listener na mysku a klavestnicu
        this.addMouseListener(new MouseInput(this, this.player, this.playerMissile));
        this.addKeyListener(new KeyInput(this, this.player, this.playerMissile));
        requestFocus();
    }
    
    //nastavenie vlakna a zapnutie hlavneho gameloop-u
    private synchronized void start(){
        if (this.running){
            return; 
        }
        
        this.running = true;
        this.thread = new Thread(this);
        this.thread.setName("main thread");
        this.thread.start();
    }
    
    //vypnutie hlavneho gameloop-u
    private synchronized void stop(){
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
    
    //nastavenie a chod hlavneho gameloop-u
    @Override
    public void run() {
        
        init();
        long lastTime = System.nanoTime();
        final double amoutOfTicks = 120.0;
        double ns = 1000000000 / amoutOfTicks;
        double delta = 0;
        this.updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        //hlavny gameloop
        while(this.running){
            
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                this.updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//                System.out.println(updates  + " Ticks, fps " + frames);
                this.updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    
    //prepocitavanie logiky hry
    private void tick(){
        
        switch(this.gameState){
            case HELP:
                this.help.tick();
                break;
            case GAME:
                this.playerMissile.tick();
                this.aliens.tick();
                break;
            case DEATHSCREEN:
                this.deathScreen.tick();
                break;
            default:
                break;
        }
    }
    
    //vykreslovanie hry
    private void render(){
        
        BufferStrategy bs = getBufferStrategy();
        
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();

        //pouzivanie grafiky pod premennou "g"
        g.setColor(Color.black);
        g.fillRect(0, 0, this.width, this.height);
        
        switch(this.gameState){
            
            case MENU:
                this.menu.render(g);
                break;
            
            case HELP:
                this.help.render(g);
                break;
            case GAME:
                this.playerMissile.render(g);
                this.player.render(g);
                this.bariers.render(g);
                this.aliens.render(g);
                break;
            case DEATHSCREEN:
                this.deathScreen.render(g);
                break;
            default:
                g.setColor(Color.black);
                g.fillRect(0, 0, this.width, this.height);
                break;
        }
        g.dispose();
        bs.show();
    }
    
    //vratenie dlzky obrazovky
    public int getWidth(){
        
        return this.width;
    }
    
    //vratenie vysky obrazovky
    public int getHeight(){
        
        return this.height;
    }
    
    //nastavenie GameState
    public void setGameState(String state){
        
        if(state.equals("GAME")){
            this.gameState = GameState.GAME;
        } else if(state.equals("HELP")){
            this.gameState = GameState.HELP;
        } else if(state.equals("MENU")){
            this.gameState = GameState.MENU;
        } else if(state.equals("DEATHSCREEN")){
            this.gameState = GameState.DEATHSCREEN;
        }
    }
    
    //vratenie GameState
    public GameState getGameState(){
        
        return this.gameState;
    }
}