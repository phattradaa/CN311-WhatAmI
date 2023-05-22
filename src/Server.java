package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Server extends Thread {
    boolean clientConnect = false;

    // Setting Server//
    public void server(int port, Thread thread) throws IOException {
        boolean gameRun = false;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket player = server.accept();

                if (!clientConnect) {
                    System.out.println("Connect Succesful");

                    InputStream in = player.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                    OutputStream out = player.getOutputStream();
                    PrintWriter PrintWrite = new PrintWriter(out, true);

                    clientConnect = true;
                    PrintWrite.println(clientConnect); // Sent clientConnect to confirm the connecting

                    gameRun = true;

                    while (gameRun) {
                        System.out.println("Thread is " + thread.getName());
                        String path = setPath(thread.getName());

                        while (true) {
                            String randomFilePath = random(path);
                            PrintWrite.println(randomFilePath); // Sent the random file path
                            System.out.println("The picture is : " + name(randomFilePath));
                            String input = buffer.readLine();
                            System.out.println("player answer : " + input);
                            boolean isCorrect = checkAnswer(input, name(randomFilePath));
                            if (!isCorrect) {
                                break;
                            } else {
                                randomFilePath = random(path);
                                PrintWrite.println(randomFilePath);
                                System.out.println("The picture is: " + randomFilePath);
                            }
                        }
                        gameRun = false;
                        break;
                    }
                }
            }
        } catch (Exception error) {
            System.out.println("error");
        }
    }

    // Set Path of Data//
    public String setPath(String room) {
        String path = "...";
        if (room.equalsIgnoreCase("fruits")) {
            path = "resource/fruits";
        } else if (room.equalsIgnoreCase("vegetables")) {
            path = "resource/vegetables";
        } else if (room.equalsIgnoreCase("animals")) {
            path = "resource/animals";
        } else if (room.equalsIgnoreCase("countries")) {
            path = "resource/countries";
        }
        return path;
    }

    // Random Picture in Path
    public String random(String path) {
        File folder = new File(path);

        // Check if the path is a directory
        if (!folder.isDirectory()) {
             System.out.println("Invalid folder path.");
             return null;
        }

        File[] files = folder.listFiles();

        // Check if the folder is empty
        if (files == null || files.length == 0) {
            System.out.println("Folder is empty.");
            return null;
        }

        Random random = new Random();
        File randomFile = files[random.nextInt(files.length)];

        // Get the absolute path of the randomly selected file
        String randomFilePath = randomFile.getAbsolutePath();

        // Use the randomly selected file for further processing
        return randomFilePath;
    }

    // Check Answer//
    public boolean checkAnswer(String input, String randomPicture) {
        boolean tmp = false;

        if (input.equalsIgnoreCase(randomPicture)) {
            tmp = true;
        } else {
            tmp = false;
        }
        return tmp;
    }

    public String name(String randomPicture) {
        String[] arr = randomPicture.split("\\.");
        System.out.println(arr[0]);
        String[] pathParts = arr[0].split("/");
        String name = pathParts[pathParts.length-1];
        System.out.println(name);
        return name;
    }

    public void run() {
        System.out.println("Room " + Thread.currentThread().getName() + " start");
        if (Thread.currentThread().getName().equals("fruits")) {
            try {
                server(1111, Thread.currentThread());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("vegetables")) {
            try {
                server(2222, Thread.currentThread());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("animals")) {
            try {
                server(3333, Thread.currentThread());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("countries")) {
            try {
                server(4444, Thread.currentThread());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread room1 = new Thread(new Server());
        Thread room2 = new Thread(new Server());
        Thread room3 = new Thread(new Server());
        Thread room4 = new Thread(new Server());

        room1.setName("fruits");
        room2.setName("vegetables");
        room3.setName("animals");
        room4.setName("countries");

        room1.start();
        room2.start();
        room3.start();
        room4.start();
    }
}
