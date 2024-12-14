package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;


public class SnakeBlocks {
    
    public static int SnakeLength = 1;
    private static int x;
    private static int y;
    public static LinkedList<Integer> list_of_x = new LinkedList<>();
    public static LinkedList<Integer> list_of_y = new LinkedList<>();
    
    private static void addBlock() {
        
        list_of_x.add(list_of_x.get(list_of_x.size() -1));
        list_of_y.add(list_of_y.get(list_of_y.size() -1));
        reBlock();
    }
    
    public SnakeBlocks() {
        
        Random r = new Random();
        int i = 0;
        int u = 0;
        //x
        while(i < 5){
          i = r.nextInt(75);  
        }
        r = new Random();
        x = i * 23;
        //y
        while(u < 5){
          u = r.nextInt(40);  
        }
        y = u * 23;
        
        list_of_x.add(x);
        list_of_y.add(y);
    }
    
    
    public static void render(Graphics2D g){
        
        g.setColor(new Color(0,255,0));
        g.fillRect(list_of_x.get(0), list_of_y.get(0), 23, 23);
        g.setColor(new Color(124,180,0));
        for(int i = 1; i < list_of_x.size(); i++){
            g.fillRect(list_of_x.get(i), list_of_y.get(i), 23, 23);
        }
    }
    
    public static void tick(){
        
        reBlock();
        
        if(list_of_x.get(0) == Apple.appleX && list_of_y.get(0) == Apple.appleY){
            SnakeLength++;
            Apple.redraw();
            Apple.isFine = true;
            addBlock();
        }
        
        switch(Snake.move){
            case "UP":
                list_of_y.set(0, list_of_y.get(0) - 23);
                break;
            case "DOWN":
                list_of_y.set(0, list_of_y.get(0) + 23);
                break;
            case "RIGHT":
                list_of_x.set(0, list_of_x.get(0) + 23);
                break;
            case "LEFT":
                list_of_x.set(0, list_of_x.get(0) - 23);
                break;
        }
        
        if((list_of_x.get(0) < 0) || (list_of_x.get(0) > Snake.width) || (list_of_y.get(0) < 0) || (list_of_y.get(0) > Snake.height)){
            dead();
        }
        
        for(int i = 1; i < list_of_x.size(); i++) {
            if((list_of_y.get(0) == list_of_y.get(i) && (list_of_x.get(0) == list_of_x.get(i)))){
                System.err.println("asd");
            }
        }
    }
    
    private static void reBlock(){
        for (int i = list_of_x.size() -1; i > 0; i--) {
            list_of_x.set(i, list_of_x.get(i-1));
            list_of_y.set(i, list_of_y.get(i-1));
        }
    }
    
    
    
    private static void dead(){
        SnakeLength = 1;
        list_of_x.clear();
        list_of_y.clear();
        Snake.snakeBlocks = new SnakeBlocks();
        Snake.startGame();
    }
}
