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

    //Setting Server//
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

                    PrintWrite.println(clientConnect);

                    clientConnect = true;
                    gameRun = true;

                    while (gameRun) {
                        String path = path(thread);
                        int score = 0;

                        while (true) {
                            String randomFilePath = random(path);
                            System.out.println("The picture is : " + name(randomFilePath));
                            String input = buffer.readLine();
                            System.out.println("player answer: " + input);
                            if (checkAnswer(input, name(randomFilePath)) == false) {
                                PrintWrite.println(checkAnswer(input, name(randomFilePath)));
                                PrintWrite.println("You lost");
                                break;
                            } else {
                                PrintWrite.println(checkAnswer(input, name(randomFilePath)));
                                score++;
                            }
                        }
                        PrintWrite.println("Your score is " + score);
                        gameRun = false;
                    }
                }
            }
        } catch (Exception error) {
            System.out.println("error");
        }
    }

    //Set Path of Data//
    public String path(Thread thread) {
        String path = "...";
        if (Thread.currentThread().getName().equals("fruits")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/resource/fruits";
        } else if (Thread.currentThread().getName().equals("vegetable")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/resource/vegetables";
        } else if (Thread.currentThread().getName().equals("animals")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/resource/animals";
        } else if (Thread.currentThread().getName().equals("countries")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/resource/countries";
        }
        return path;
    }

    //Random Picture in Path
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

    //Check Answer//
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
        String name = arr[0];
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
        } else if (Thread.currentThread().getName().equals("vegetable")) {
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
