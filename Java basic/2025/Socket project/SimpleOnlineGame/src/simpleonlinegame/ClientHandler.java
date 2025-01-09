package simpleonlinegame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Tato trieda nam spracuvava udaje od pouzivatela a preposiela ich dalej.
 * 
 */
class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandler = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    
    private String clientUsername;
    private String username;
    private String x = "500";
    private String y = "500";
    
    //metoda na pridanie pouzivatela na socket
    public ClientHandler(Socket socket) {
        
        try {
            
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandler.add(this);
//            brodcastMessage("SERVER: " + this.clientUsername + " has entered the game!");
        } catch (Exception e) {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //metoda na cistanie zo soketu
    @Override
    public void run() {
        
        String messageFromClient;
        
        while (socket.isConnected()) {
            
            try {
                this.username = "";
                this.x = "";
                this.y = "";
                messageFromClient = bufferedReader.readLine();
                char[] charArray = messageFromClient.toCharArray();
                int count = 0;
                for (int i = 0; i <= charArray.length; i++) {
                    
                    if (count == 0) {
                        if (charArray[i] != '|') {
                            this.username += charArray[i];
                        } else {
                            count++;
                            continue;
                        }
                    }
                    
                    if (count == 1) {
                        if (charArray[i] != '|') {
                            this.x += charArray[i];
                        } else {
                            count++;
                            continue;
                        }
                    }
                    
                    if (count == 2) {
                        if (i != charArray.length) {
                            this.y += charArray[i];
                        } else {
                            break;
                        }
                    }
                }
                brodcastMessage(messageFromClient);
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        } 
    }
    
    //poslanie sprav pre vsetkych
    public void brodcastMessage(String messageToSend) {
        
        for (int i = 0; i < clientHandler.size(); i++){
            try {
                if (!clientHandler.get(i).clientUsername.equals(clientUsername)) {
                    clientHandler.get(i).bufferedWriter.write(messageToSend);
                    clientHandler.get(i).bufferedWriter.newLine();
                    clientHandler.get(i).bufferedWriter.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    
    //odpojenie klienta
    public void removeClientHandler() {
        
        clientHandler.remove(this);
        //brodcastMessage("SERVER: " + this.clientUsername + " has left the caht!");
    }
    
    //odpojenie a vypnutie vsetkeho
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        
        removeClientHandler();
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
}
