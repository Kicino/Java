package simpleonlinegame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Trieda server nam vytvara server na pocitaci, kde vieme posielat data a  tato  klasa  ich preposiela dalsim  uzivatelom.
 * Tato trieda  nam vytvori triedu clientHandler kde spracuvavame data od pouzivatela
 */
public class Server {
    
    private ServerSocket serverSocket;
    
    //nastavenie socketu na posielanie a prijmanie dat
    public Server(ServerSocket serverSocket) {
        
        this.serverSocket = serverSocket;
    }
    
    //zapnutie serveru
    public void startServer() {
        
        try {
            
            while (!serverSocket.isClosed()) {                
                
                //caka kym sa niekto pripoji na soket
                Socket socket = serverSocket.accept();
                System.out.println("A new client has conected!");
                ClientHandler clientHandler = new ClientHandler(socket);
                
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //vypnutie soketu na posielanie sprav
    public void closeServerSocket() {
        
        try {
            
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
