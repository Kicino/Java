package client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Tato trieda klienta nam umoznuje spolupracovat, posielat a pocuvat spravy zo servera. Pomocou nej sa nam vytvori okno, kde sa mozeme hybat
 * Data posielame na nejaku ip adresu s portom "1234"
 * 
 */
public class Client extends Canvas{
    
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    
    private int x;
    private int y;
    private Color color;
    private int verticalMovement;
    private int horisontalMovement;
    
    private String inputName = "";
    private String inputX = "";
    private String inputY = "";
    
    //vytvorenie a pridanie noveho klienta na soket
    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
        } catch (Exception e) {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        
        //vygenerovanie nahodnych pozicii a farby noveho klienta
        this.x = new Random().nextInt(350);
        this.y = new Random().nextInt(350);
        this.color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        
        //nastavenie okna programu
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(450, 450));
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Top 1 hra");
        frame.add(this);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        this.setBackground(Color.black);
        this.addKeyListener(new keyInput(this));
        this.requestFocus();
    }
    
    //kreslenie gulocok 
    public void paint(Graphics g) {
       
        this.x += this.horisontalMovement;
        this.y += this.verticalMovement;
        
        g.setColor(this.color);
        g.fillOval(this.x, this.y, 25, 25);
        g.setFont(new Font("Arial", 20, 12));
        g.drawString(this.username, this.x - 8, this.y);
        
        if (!inputName.equals("")) {
            
            g.setColor(Color.WHITE);
            g.fillOval(Integer.parseInt(inputX), Integer.parseInt(inputY), 25, 25);
            g.setFont(new Font("Arial", 20, 12));
            g.drawString(inputName, Integer.parseInt(inputX) - 8, Integer.parseInt(inputY));
            
            inputName = "";
            inputX = "";
            inputY = "";
        }
    }
    
    //poslanie spravy na soket
    public void sendMessage() {
        
        try {
            while (socket.isConnected()) {
                String messageToSend = this.username + "|" + this.x + "|" + this.y;
//                System.err.println(messageToSend);
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                Thread.sleep(20);
            }
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    //posluhcnaie sprav zo soketu
    public void listenForMessage() {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroubChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGroubChat = bufferedReader.readLine();
                        System.out.println(msgFromGroubChat);
                        
                        /////////////////////////////////
                        char[] charArray = msgFromGroubChat.toCharArray();
                        int count = 0;

                        for (int i = 0; i <= charArray.length; i++) {

                            if (count == 0) {
                                if (charArray[i] != '|') {
                                    inputName += charArray[i];
                                } else {
                                    count++;
                                    continue;
                                }
                            }

                            if (count == 1) {
                                if (charArray[i] != '|') {
                                    inputX += charArray[i];
                                } else {
                                    count++;
                                    continue;
                                }
                            }

                            if (count == 2) {
                                if (i != charArray.length) {
                                    inputY += charArray[i];
                                } else {
                                    break;
                                }
                            }
                        }
                        repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }
    
    //vypnutie a uyavretie vsetkeho
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        
        try {
            if (bufferedReader != null) {
                
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                
                bufferedWriter.close();
            }
            if (socket != null) {
                
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //main trieda
    public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter yor username for acess groubchat: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();
    }
    
    public void setX(int x) {
        
        this.x += x;
    }
    
    public void setY(int y) {
        
        this.y += y;
    }
    
    public void setVerticalMovement(int i) {
        
        this.verticalMovement = i;
    }
    
    public void setHorisontalMovement(int i) {
        
        this.horisontalMovement = i;
    }
}
