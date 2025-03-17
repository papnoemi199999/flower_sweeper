package Menu;

import Audio.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    private Sounds sounds;
    public MenuFrame() {

        Sounds sounds = Sounds.getInstance();
        sounds.base();

        setTitle("MENU");
        setSize(1500, 1000);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JButton button1 = new JButton("DESCRIPTION");
        JButton button2 = new JButton("START GAME");
        JButton button3 = new JButton("EXIT");

        button1.setFont(new Font("Arial", Font.BOLD, 22));
        button2.setFont(new Font("Arial", Font.BOLD, 22));
        button3.setFont(new Font("Arial", Font.BOLD, 22));

        button1.setPreferredSize(new Dimension(200, 100));
        button2.setPreferredSize(new Dimension(200, 100));
        button3.setPreferredSize(new Dimension(200, 100));
        button1.setBackground(Color.decode("#FFB6C1"));
        button2.setBackground(Color.decode("#FFB6C1"));
        button3.setBackground(Color.decode("#FFB6C1"));

        button1.setMargin(new Insets(10, 20, 10, 20));
        button2.setMargin(new Insets(10, 20, 10, 20));
        button3.setMargin(new Insets(10, 20, 10, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel(new ImageIcon("gif/flowers.gif"));

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.add(imageLabel);

        mainPanel.add(imagePanel, BorderLayout.SOUTH);

        add(mainPanel);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DescriptionFrame();
                dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DifficultyFrame();
                dispose();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

}