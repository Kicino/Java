package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.JFrame;


public class Snake extends Canvas implements Runnable{
    
    
    private static boolean running = false;
    private static Thread thread;
    private static JFrame frame;
    private static Keyboard key = new Keyboard();
    private static Snake snake = new Snake();
    private static Apple apple = new Apple();
    public static SnakeBlocks snakeBlocks = new SnakeBlocks();
    
    public static int width, height;
    public static String move;
    private static String[] moves = {"UP", "DOWN", "LEFT", "RIGHT"};
    
    public static void main(String[] args) {
     
        Toolkit tk = Toolkit.getDefaultToolkit();
            width = (int) tk.getScreenSize().getWidth();
            height = (int) tk.getScreenSize().getHeight();
            
        snake = new Snake();
        snake.setPreferredSize(new Dimension(width, height));
        snake.setBackground(Color.black);
        snake.addKeyListener(key);
        
        frame = new JFrame();
        
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Snake");
        frame.add(snake);
        frame.pack();
        

        startGame();
        snake.start();
    }   
    
    public void init(){

        requestFocus();

    }
    
    private synchronized void start(){
        if (running){
            return; 
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private synchronized void stop(){
        if (!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
    
    
    @Override
    public void run() {
        
        init();
        long lastTime = System.nanoTime();
        final double amoutOfTicks = 36.0;
        double ns = 1000000000 / amoutOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(running){
            
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
                render();
                frames++;
            }
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates  + " Ticks, fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        SnakeBlocks.tick();
    }
    
    private void render(){
        
        BufferStrategy bs = getBufferStrategy();
        
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        ////graphics
            g.setColor(Color.black);
            g.fillRect(0, 0, width, height);
            
            
            g.setColor(new Color(100, 100, 100, 50));
            //x
            for(int i = 0; i <= 83; i++){
                g.drawLine(i*23, 0, i*23, height);
            }
            //y
            for(int i = 0; i <= 46; i++){
                g.drawLine(0, i * 23, width, i * 23);
            }
            
            apple.render((Graphics2D) g);
            snakeBlocks.render((Graphics2D) g);
            
            
        //graphics
        g.dispose();
        bs.show();
    }
    
    public static void startGame(){
        

        Apple.redraw();
        Apple.isFine = true;
        //random moving  on start
        Random r = new Random();
        int i = r.nextInt(3);
        move = moves[i];
    }
}
