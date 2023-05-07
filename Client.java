import java.net.Socket;
import java.io.IOException;
// import java.util.concurrent.ConcurrentHashMap;

public class Client {
    public static void main(String[] args) throws IOException {
        String[] rooms = {"room1", "room2", "room3", "room4"}; // select room
        Thread[] threads = new Thread[4]; // create thread for the room
        // ConcurrentHashMap<String, Socket> connectedRooms = new ConcurrentHashMap<>(); // save information about selected room and users who joined the room
        
         for (int i = 0; i < threads.length; i++) {
            final int index = i; // save index for thread
            threads[i] = new Thread(() -> { // loop thraed
                try {
                    Socket socket = new Socket("localhost", 1000 + index); // connect to server (using 1000 + index of thread as port)
                    String roomNum = rooms[index];
                    // if (connectedRooms.putIfAbsent(roomNum, socket) != null) {
                    //     // This room is full of users. unable to connect
                    //     socket.close();
                    //     System.err.println("Room " + roomNum + " is full");
                    //     return;
                    // }
                    System.out.println("Connected to " + roomNum + " on port " + socket.getPort());
                } catch (IOException e) {
                    System.err.println("Failed to connect to " + rooms[index] + " : " + e.getMessage());
                }
            });
        }
        
        for (Thread thread : threads) {
            thread.start(); // start thread
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted : " + e.getMessage());
            }
        }
    }
}
