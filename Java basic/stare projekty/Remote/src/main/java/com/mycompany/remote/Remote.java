package com.mycompany.remote;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;


public class Remote {
    
    Point p = MouseInfo.getPointerInfo().getLocation();
    
    public void connect(String portname) throws AWTException{

        Robot bot = new Robot();
        SerialPort port = new SerialPort("COM5");
        
        try {
            port.openPort();
            port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            port.addEventListener((SerialPortEvent event)-> {
                if(event.isRXCHAR()){
                    try {
                        String s = port.readString();
                        System.out.println(s);
                        /*if(s.equals("45") || s.equals("46")  || s.equals("47")){
                            System.exit(0);
                        } else if(s.equals("44")){
                            //left arrow
                            bot.keyPress(KeyEvent.VK_LEFT);
                        } else if(s.equals("40")){
                            //right arrow
                            bot.keyPress(KeyEvent.VK_RIGHT);
                        } else if(s.equals("43")){
                            //right arrow
                            bot.keyPress(KeyEvent.VK_SPACE);
                        } else if(s.equals("7")){
                            //tvuk dole
                            bot.keyPress(KeyEvent.VK_DOWN);
                        } else if(s.equals("15")){
                            //yvuk hore
                            bot.keyPress(KeyEvent.VK_UP);
                        } else if(s.equals("9")){
                            //fullscreen
                            bot.keyPress(KeyEvent.VK_F);
                        } else if(s.equals("18")){
                            //miska do hora
                            p = MouseInfo.getPointerInfo().getLocation();
                            bot.mouseMove((int) p.getX(),(int) p.getY() - 10);
                        } else if(s.equals("5a")){
                            //miska do prava
                            p = MouseInfo.getPointerInfo().getLocation();
                            bot.mouseMove((int) p.getX() + 10,(int) p.getY());
                        } else if(s.equals("8")){
                            //miska do lava
                            p = MouseInfo.getPointerInfo().getLocation();
                            bot.mouseMove((int) p.getX() - 10,(int) p.getY());
                        } else if(s.equals("52")){
                            //miska dole
                            p = MouseInfo.getPointerInfo().getLocation();
                            bot.mouseMove((int) p.getX(),(int) p.getY() + 10);
                        } else if(s.equals("1c")){
                            //click
                            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        }*/
                    } catch (SerialPortException x) {
                        System.err.println(x);
                    }
                }
                
            });
            
        } catch (SerialPortException ex) {
            Logger.getLogger(Remote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) throws AWTException {
        
        try{
            String portList[] = SerialPortList.getPortNames();

            /*for(int i = 0; i < portList.length; i++){
                System.out.println(portList[i]);
            }*/
            Remote obj = new Remote();
            obj.connect(portList[0]);
        } catch(Exception  e){
            try {
              FileWriter myWriter = new FileWriter("filename.txt");
              myWriter.write(e.getMessage());
              myWriter.close();
            } catch (Exception i) {
            }
        }
    }
}