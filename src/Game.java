package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

public class Game extends JFrame {
    public Game(int port, String room) throws IOException {
        try {
            while (true) {
                Socket player = new Socket("localhost", port);
                System.out.println("Connect Succesful");
                break;
            }
        } catch (Exception error) {
            System.out.println(error);
        }
    }
    
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        String room = args[1];
        try {
            Game x = new Game(port, room);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}