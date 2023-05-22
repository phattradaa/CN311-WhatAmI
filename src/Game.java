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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

public class Game {
    private JTextField answerField;
    public static Font titleFont = new Font("Gameplay", Font.BOLD, 30);
    public static Font font = new Font("Gameplay", Font.BOLD, 20);
    public static Font bttnFont = new Font("Gameplay", Font.BOLD, 14);
    public static Color bttnColor = Color.decode("#64a2eb");
    public static Color bgColor = Color.decode("#f2ece3");
    JTextField ansTextField;
    String answer;
    PrintWriter PrintWrite;
    int count = 0;
    BufferedReader buffer;
        
    public Game(int port, String room){
        boolean gameRun = false;
        try {
            Socket player = new Socket("localhost", port);
            while (true) {
                System.out.println("Connect Succesful");

                player.setSoTimeout(500);
                InputStream in = player.getInputStream();
                buffer = new BufferedReader(new InputStreamReader(in));
                OutputStream out = player.getOutputStream();
                PrintWriter PrintWrite = new PrintWriter(out, true);

                boolean clientConnect = Boolean.parseBoolean(buffer.readLine()); // Accept the confirm clientConnect
                                                                                 // from server
                System.out.println("Connected to the server: " + clientConnect);

                // gameRun = true;
                if (clientConnect) {
                    String randomFilePath = buffer.readLine(); // Accept the random file path from server
                    // System.out.println(randomFilePath);
                    createGUI(room, randomFilePath);
                    // JTextField answerField = createGUI.getAnswerField();
                    // while (gameRun) {
                    // String answer = answerField.getText();
                    // PrintWrite.println(answer);
                    // boolean check = Boolean.parseBoolean(buffer.readLine());
                    // //if (check == true);
                }
                break;
            }

        } catch (Exception error) {
            System.out.println(error);
        }
    }

    
    public void createGUI(String room, String randomFilePath) {
        // set look and feel ให้ mac เห็นสี GUI เป็นปกติ
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --------------- Frame ---------------
        JFrame frame = new JFrame("WHAT AM I");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // --------------- Panel 1 ---------------
        JPanel panel1 = new JPanel();
        panel1.setBackground(bgColor);
        panel1.setBounds(0, 0, 700, 80);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

        // set title in panel 1
        JLabel label = new JLabel(room);
        label.setFont(titleFont);
        panel1.add(label);

        // --------------- Panel 2 ---------------
        JPanel panel2 = new JPanel();
        panel2.setBackground(bgColor);
        panel2.setBounds(0, 80, 700, 300);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));

        // set image icon
        panel2.add(setImage(randomFilePath));

        // --------------- Panel 3 ---------------
        JPanel panel3 = new JPanel();
        panel3.setBackground(bgColor);
        panel3.setBounds(0, 380, 700, 120);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // label in panel 3
        JLabel ansLabel = new JLabel("Answer");
        ansLabel.setFont(font);
        ansLabel.setBounds(0, 0, 200, 40);
        panel3.add(ansLabel);

        // text field in panel 3
        ansTextField = new JTextField();
        ansTextField.setPreferredSize(new Dimension(200, 50));
        ansTextField.setHorizontalAlignment(SwingConstants.CENTER);
        ansTextField.setFont(font);
        panel3.add(ansTextField);

        // button in panel 3
        JButton submitButton = new JButton("submit");
        submitButton.setFont(bttnFont);
        submitButton.setBackground(bttnColor);
        submitButton.setPreferredSize(new Dimension(100, 40));
        panel3.add(submitButton);

        submitButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
                answer = ansTextField.getText();
                System.out.println(answer);
                ansTextField.setText("");
                PrintWrite.println(answer);
            }
        });

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);

        try {
            String response = buffer.readLine();
            boolean isCorrect = Boolean.parseBoolean(response);
            System.out.println("Server response: " + isCorrect);
            // Process the boolean response from the server as needed
            // For example, display a message or update the GUI accordingly
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JLabel setImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        // panel2.add(imageLabel);
        return imageLabel;
    }

    
    public void sentAnswer(String answer) {
        //PrintWrite.println(answer);
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        String room = args[1];
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Game x = new Game(port, room);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
}
}
