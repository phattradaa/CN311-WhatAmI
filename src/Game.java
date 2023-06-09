package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
    private boolean gameRunning = true;
    int score = 0;
    JFrame frame;
        
    public Game(int port, String room){
        boolean gameRun = false;
        try {
            // Setting Server //
            Socket player = new Socket("localhost", port);
            System.out.println("Connect Successful");
            player.setSoTimeout(100);
            
            OutputStream out = player.getOutputStream();
            PrintWriter printWrite = new PrintWriter(out, true);
            InputStream in = player.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // Accept boolean clientConnec from server //
            boolean clientConnect = Boolean.parseBoolean(reader.readLine());
            System.out.println("Connected to the server: " + clientConnect);
            // Accept random picture path from server // 
            String path = reader.readLine();
            System.out.println(path);
            // create GUI //
            createGUI(room, path);
            // wait to accept the click signal // 
            ansTextField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Accept the answer from text field // 
                    String input = ansTextField.getText();
                    // Sent the answer to server //
                    printWrite.println(input);
                    //System.out.println(input);
                    boolean isCorrect = true;
                    try {
                        // Server will sent isCheck from answer //
                        isCorrect = Boolean.parseBoolean(reader.readLine());
                        if (!isCorrect) {
                            System.out.println("You Lost");
                            System.out.println("Your score is:" + score);
                            setOption(score,player);
                            score = 0;
                            player.close();
                            gameRunning = false;
                            //player.close();
                        } else {
                            String newpath = reader.readLine();
                            reset(newpath);
                            score++;
                        }
                    } catch (IOException e1) {
                        System.out.println("Error" + e1.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            // If the room is full , you cannot go to this room.
            System.out.println("Room is full ");
            setFullRoom();
            frame.setVisible(false);
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
        frame = new JFrame("WHAT AM I");
        frame.setSize(700, 500);

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

        // set image for the game
        panel2.add(setImage(randomFilePath));

        // --------------- Panel 3 ---------------
        JPanel panel3 = new JPanel();
        panel3.setBackground(bgColor);
        panel3.setBounds(0, 380, 700, 120);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // answer label in panel 3
        JLabel ansLabel = new JLabel("Answer");
        ansLabel.setFont(font);
        ansLabel.setBounds(0, 0, 200, 40);
        panel3.add(ansLabel);

        // text field to get the answer from user in panel 3
        ansTextField = new JTextField();
        ansTextField.setPreferredSize(new Dimension(200, 50));
        ansTextField.setHorizontalAlignment(SwingConstants.CENTER);
        ansTextField.setFont(font);
        panel3.add(ansTextField);

        // submit button in panel 3
        JButton submitButton = new JButton("submit");
        submitButton.setFont(bttnFont);
        submitButton.setBackground(bttnColor);
        submitButton.setPreferredSize(new Dimension(100, 40));
        panel3.add(submitButton);

        // button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ansTextField.postActionEvent();
            }
        });

        // add panel 1, panel 2, panel 3 to frame
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);

    }

    // Set Image when your answer is correct
    public JLabel setImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        // panel2.add(imageLabel);
        return imageLabel;
    }

    //reset image and text field when your answer is correct
    public void reset(String path) {
        ansTextField.setText("");
        JLabel imageLabel = setImage(path);
        JPanel panel2 = (JPanel) ansTextField.getParent().getParent().getComponent(1);
        panel2.removeAll();
        panel2.add(imageLabel);
        panel2.revalidate();
        panel2.repaint();
        setImage(path);
    }

    // set JOption when the game is over
    public void setOption(int score,Socket player) throws IOException {

        // set icon for JOption
        ImageIcon icon = new ImageIcon(
                "/Users/spy/Desktop/CN311-TheBigBagHave/resource/goodjob.png");
        Image resizedIcon = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        icon.setImage(resizedIcon);

        // set message for JOption
        String message = "<html><body><p style='font-family: Gameplay; font-size: 15px;'>Good job! Your score is "
                + score + "<br>Do you want to play again?</p></body></html>";
        
        // get the option value as a number to check the if else condition
        int option = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, icon);
        System.out.println(option);

        // if else condition to check if user want to play again or not ( yes = 0, no = 1 )
        if (option == 0) {
            player.close();
            frame.dispose();
            Client.clientPage();
        } else {
            frame.dispose();
        }
    }
    
    // Set GUI when this room is full //
    public void setFullRoom() {
        String message = "<html><body><p style='font-family: Gameplay; font-size: 15px;'>This room is full</p></body></html>";
        JOptionPane.showConfirmDialog(null, message, null, JOptionPane.OK_OPTION,
                JOptionPane.ERROR_MESSAGE);
        // System.out.println(option);
        frame.dispose();
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
