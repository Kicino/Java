package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Apple {
    
    public static int appleX;
    public static int appleY;
    public static boolean isFine = true;
    private static Random r = new Random();
    
    public static void render(Graphics2D g){
        
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, 23, 23);
    }
    
    public static void redraw(){
        
        while(isFine){
            int x = r.nextInt(82);
            int y = r.nextInt(46);

            appleX = x * 23;
            appleY = y * 23;
            for(int i = 0; i < SnakeBlocks.list_of_x.size(); i++) {
                if((appleX != SnakeBlocks.list_of_x.get(i)) && (appleY != SnakeBlocks.list_of_y.get(i))){
                    isFine = false;
                }
            }
        }
    }
}
