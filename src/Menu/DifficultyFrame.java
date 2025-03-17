package Menu;

import MineSweeper.MineSweeperFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyFrame extends JFrame {
    public DifficultyFrame() {
        setTitle("Choose Difficulty");
        setSize(1500, 1000);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        JLabel instructionLabel = new JLabel("Please choose a difficulty");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(instructionLabel);
        mainPanel.add(topPanel);

        JPanel middlePanel = new JPanel(new GridLayout(1, 3, 40, 10));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton easyButton = new JButton("EASY");
        JButton mediumButton = new JButton("MEDIUM");
        JButton hardButton = new JButton("HARD");

        easyButton.setFont(new Font("Arial", Font.BOLD, 18));
        mediumButton.setFont(new Font("Arial", Font.BOLD, 18));
        hardButton.setFont(new Font("Arial", Font.BOLD, 18));

        easyButton.setPreferredSize(new Dimension(150, 80));
        mediumButton.setPreferredSize(new Dimension(150, 80));
        hardButton.setPreferredSize(new Dimension(150, 80));

        easyButton.setBackground(Color.decode("#FFB6C1"));
        mediumButton.setBackground(Color.decode("#FFB6C1"));
        hardButton.setBackground(Color.decode("#FFB6C1"));

        easyButton.setMargin(new Insets(10, 20, 10, 20));
        mediumButton.setMargin(new Insets(10, 20, 10, 20));
        hardButton.setMargin(new Insets(10, 20, 10, 20));

        middlePanel.add(easyButton);
        middlePanel.add(mediumButton);
        middlePanel.add(hardButton);
        mainPanel.add(middlePanel);

        JPanel centerPanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("gif/logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(image.getWidth(null) / 2, image.getHeight(null) / 2, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        centerPanel.add(imageLabel);
        mainPanel.add(centerPanel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setPreferredSize(new Dimension(200, 100));
        backButton.setBackground(Color.decode("#FFB6C1"));
        backButton.setMargin(new Insets(10, 20, 10, 20));
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel);

        add(mainPanel);

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MineSweeperFrame(5, 5);
                dispose();
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MineSweeperFrame(10, 10);
                dispose();
            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MineSweeperFrame(20, 20);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame();
                dispose();
            }
        });

        setVisible(true);
    }
}
