import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.*;

public class Client {

    public static Font titleFont = new Font("Gameplay", Font.BOLD, 25);
    public static Font font = new Font("Gameplay", Font.BOLD, 14);
    public static Font bttnFont = new Font("Gameplay", Font.BOLD, 10);
    public static Color bttnColor = Color.decode("#64a2eb");
    public static Color bgColor = Color.decode("#f2ece3");

    public static void main(String[] args) {

        // set look and feel ให้ mac เห็นสี GUI เป็นปกติ
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setBounds(0, 0, 700, 500);

        JFrame frame = new JFrame("The Big Bag Have...");
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(panel);

        JLabel title = new JLabel("THE BIG BAG HAVE...");
        title.setFont(titleFont);
        title.setForeground(Color.BLACK);
        title.setBounds(200, 30, 300, 25);
        panel.add(title);

        JLabel desct = new JLabel("Description");
        desct.setFont(font);
        desct.setBounds(50, 120, 150, 14);
        panel.add(desct);

        // สร้าง TextArea
        JTextArea textArea = new JTextArea();
        textArea.setText(
                "You can enter the player's number that you wanna play \nFruits Player1 : 1111     Fruits Player2 : 1112 \nVegetables Player1 : 2221     Vegetables Player2 : 2222 \nAnimals Player1 : 3331     Animals Player2 : 3332 \nCountries Player1 : 4441     Countries Player2 : 4442 \nPlease Enter the room's number that you want ");
        textArea.setFont(bttnFont);

        textArea.setBounds(200, 100, 450, 200);
        textArea.setEditable(false); // ตั้งค่าให้ไม่สามารถแก้ไขข้อความได้
        panel.add(textArea);

        JLabel label = new JLabel("Enter The Number");
        label.setFont(font);
        label.setBounds(70, 410, 180, 14);
        panel.add(label);

        JTextField inputField = new JTextField();
        inputField.setFont(font);
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.setBounds(280, 390, 150, 50);
        panel.add(inputField);

        JButton bttn = new JButton("SUBMIT");
        bttn.setFont(bttnFont);
        bttn.setBackground(bttnColor);
        bttn.setBounds(480, 395, 100, 40);
        panel.add(bttn);

        bttn.addActionListener(e -> {
            String userInput = inputField.getText();
            sendToServer(userInput);
        });
    }

    private static void sendToServer(String userInput) {
        try {
            Socket socket = new Socket("IP Server", port);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(userInput);
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}