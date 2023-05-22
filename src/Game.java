package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;

public class Game {
    Game() {


    }

    public Game(int port, String room) throws IOException {

        try {
            Socket player = new Socket("localhost", port);
            while (true) {
                System.out.println("Connect Succesful");
                InputStream in = player.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                OutputStream out = player.getOutputStream();
                PrintWriter PrintWrite = new PrintWriter(out, true);

                boolean clientConnect = Boolean.parseBoolean(buffer.readLine()); // Accept the confirm clientConnect from server
                System.out.println("Connected to the server: " + clientConnect);

                if (clientConnect) {
                    String randomFilePath = buffer.readLine(); // Accept the random file path from server
                    // System.out.println(randomFilePath);
                    GUI createGUI = new GUI(room, randomFilePath);
                }
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
