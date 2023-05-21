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
    public void Server(int port,Thread thread) throws IOException {
        boolean gameRun = false;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket player = server.accept();

                if (!clientConnect) {
                    System.out.println("Connect Succesful");

                    InputStream input = player.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(input));

                    OutputStream output = player.getOutputStream();

                    PrintWriter PrintWrite = new PrintWriter(output, true);
                    
                    PrintWrite.println(clientConnect);

                    clientConnect = true;
                    gameRun = true;

                    while (gameRun) {
                        String path = path(thread);
                        String randomFilePath = random(path);
                        System.out.println("The picture is : " + randomFilePath);
                    }
                }

            }
        } catch (Exception error) {
            System.out.println("error");
        }
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
    public boolean checkAnswer(String input, File randomPicture) {
        boolean tmp = false;
        String randomPictureName = randomPicture.getName();

        if (input.equals(randomPictureName)) {
            tmp = true;
        } else {
            tmp = false;
        }
        return tmp;
    }

    public String name(File randomPicture) {
        String name = randomPicture.getName();
        for (String i : name) {
            
        }
    }
    
    public void run() {
        System.out.println("Room " + Thread.currentThread().getName() + "start");
        if (Thread.currentThread().getName().equals("fruits")) {
            try {
                Server(1111,Thread.currentThread());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("vegetable")) {
            try {
                Server(2222, Thread.currentThread());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("animals")) {
            try {
                Server(3333, Thread.currentThread());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("countries")) {
            try {
                Server(4444, Thread.currentThread());
            } catch (IOException e) {
                // TODO Auto-generated catch block
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

