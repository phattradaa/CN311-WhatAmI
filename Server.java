import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    //Setting Server//
    public void Server(int port1, int port2) throws IOException {
        boolean gameRun = false;
        ArrayList<String> answerList = new ArrayList<String>();
        try {
            ServerSocket server1 = new ServerSocket(port1);
            while (true) {
                Socket player1 = server1.accept();
                ServerSocket server2 = new ServerSocket(port2);
                while (true) {
                    Socket player2 = server2.accept();


                    gameRun = true;
                    while (gameRun) {
                        
                    }


                }
            }
        } catch (Exception error) {
            System.out.println("error");
        }
}

    //Set Datasets//
    public ArrayList dataSets(Thread thread) {
        String path = "...";
        if (Thread.currentThread().getName().equals("fruits")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/fruits.csv";
        } else if (Thread.currentThread().getName().equals("vegetable")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/vegetables.csv";
        } else if (Thread.currentThread().getName().equals("animals")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/animals.csv";
        } else if (Thread.currentThread().getName().equals("countries")) {
            path = "/Users/spy/Desktop/CN311-TheBigBagHave/all_countries.csv";
        } 

        ArrayList<String> list = new ArrayList<String>();
        try { 
                CSVReader reader = new CSVReader(new FileReader(path));
                File file = new File(path);
                BufferedReader data = new BufferedReader(new FileReader(file));
                String line;
                while ((line = data.readLine()) != null) {
                    String[] arr = line.split(",");
                    list.add(arr[1]);
                }
                return list;
            } catch (Exception error) {
                System.out.println("Error from Set Datasets");
                return null;
            }
    }

    //Save Answer//
    public void saveAnswer(String input, ArrayList<String> answerList) {
        answerList.add(input);
    }

    //Check Datasets//
    public boolean checkAnswer(String input, ArrayList<String> answerList, ArrayList<String> datasets) {
        for (String currentAnswer : answerList) {
            if (input.equals(currentAnswer)) {
                return false;
            } else { 
                return true;
            }
        }

        for (String currentData : datasets) {
            if (input.equals(currentData)) {
                return true;
            } else {
                return false;
            }
        }
    }
    

    public void run() {
        System.out.println("Room " + Thread.currentThread().getName() + "start");
        if (Thread.currentThread().getName().equals("fruits")) {
            try {
                Server(1111, 1112);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("vegetable")) {
            try {
                Server(2221, 2222);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("animals")) {
            try {
                Server(3331, 3332);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (Thread.currentThread().getName().equals("countries")) {
            try {
                Server(4441, 4442);
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
}
