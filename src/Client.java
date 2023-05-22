package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Client {

    public static Font titleFont = new Font("Gameplay", Font.BOLD, 30);
    public static Font font = new Font("Gameplay", Font.BOLD, 14);
    public static Font bttnFont = new Font("Gameplay", Font.BOLD, 20);
    public static Color bttnColor = Color.decode("#64a2eb");
    public static Color bgColor = Color.decode("#f2ece3");
    static Game game;
    public static void main(String[] args) {
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
        frame.setLayout(null);

        // --------------- Panel 1 ---------------
        JPanel panel1 = new JPanel();
        panel1.setBackground(bgColor);
        panel1.setBounds(0, 0, 700, 80);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

        // set title in panel 1
        JLabel label = new JLabel("WHAT AM I ?");
        label.setFont(titleFont);
        panel1.add(label);

        // --------------- Panel 2 ---------------
        JPanel panel2 = new JPanel();
        panel2.setBackground(bgColor);
        panel2.setBounds(0, 80, 700, 420);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

        // set button in panel 2
        JButton fruitsButton = new JButton("FRUITS");
        fruitsButton.setPreferredSize(new Dimension(220, 150));
        fruitsButton.setBackground(bttnColor);
        fruitsButton.setFont(bttnFont);

        JButton vegetablesButton = new JButton("VEGETABLES");
        vegetablesButton.setPreferredSize(new Dimension(220, 150));
        vegetablesButton.setBackground(bttnColor);
        vegetablesButton.setFont(bttnFont);

        JButton animalsButton = new JButton("ANIMALS");
        animalsButton.setPreferredSize(new Dimension(220, 150));
        animalsButton.setBackground(bttnColor);
        animalsButton.setFont(bttnFont);

        JButton countriesButton = new JButton("COUNTRIES");
        countriesButton.setPreferredSize(new Dimension(220, 150));
        countriesButton.setBackground(bttnColor);
        countriesButton.setFont(bttnFont);

        panel2.add(fruitsButton);
        panel2.add(vegetablesButton);
        panel2.add(animalsButton);
        panel2.add(countriesButton);

        frame.add(panel1);
        frame.add(panel2);
        frame.setVisible(true);

        // --------------- Button action --------------
        fruitsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game = new Game(1111, "fruits");
                frame.dispose();
            }
        });
        vegetablesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game = new Game(2222, "vegetables");
                frame.dispose();
            }
        });
        animalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game = new Game(3333, "Animals");
                frame.dispose();
            }
        });
        countriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game = new Game(4444, "Countries");
                frame.dispose();
            }
        });

    }
}