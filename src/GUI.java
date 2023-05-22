package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class GUI {
    public static Font titleFont = new Font("Gameplay", Font.BOLD, 30);
    public static Font font = new Font("Gameplay", Font.BOLD, 20);
    public static Font bttnFont = new Font("Gameplay", Font.BOLD, 14);
    public static Color bttnColor = Color.decode("#64a2eb");
    public static Color bgColor = Color.decode("#f2ece3");
    JTextField ansTextField;
    
    public GUI(String room, String randomFilePath) {
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
        JLabel label = new JLabel("room");
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
        submitButton.addActionListener(e -> {
            String answer = ansTextField.getText();
        });

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setVisible(true);
    }

    private JLabel setImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        // panel2.add(imageLabel);
        return imageLabel;
    }

    public JTextField getAnswerField() {
        return ansTextField;
    }

}
