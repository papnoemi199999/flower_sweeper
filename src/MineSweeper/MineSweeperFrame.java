package MineSweeper;

import Menu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import Audio.Sounds;

public class MineSweeperFrame {

    private JFrame mainFrame;
    private MineSweeperBoard board;
    private JButton backButton;
    private JLabel timerLabel;
    private Timer timer;
    private int elapsedTime;
    private BufferedImage backgroundImage;

    public MineSweeperFrame(int rows, int cols) {
        loadBackgroundImage();
        initializeMainFrame(rows, cols);
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("gif/background.jpeg"));
        } catch (IOException e) {
            System.err.println("Error: Unable to load background image.");
            e.printStackTrace();
        }
    }

    private void initializeMainFrame(int rows, int cols) {

        // Setting up the MAINFRAME
        mainFrame = new JFrame("Minesweeper");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1500, 1000);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null);

        //BACKGROUND PANEL
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        mainFrame.add(backgroundPanel, BorderLayout.CENTER);

        board = new MineSweeperBoard(this, timerLabel, rows, cols);

        //GAME BOARD
        JPanel boardWrapper = new JPanel();
        boardWrapper.setPreferredSize(new Dimension(800, 800));
        boardWrapper.setOpaque(false);
        boardWrapper.add(board.getBoardPanel());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(boardWrapper, gbc);

        //TIMER LABEL
        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setOpaque(false);
        controlPanel.add(timerLabel);

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        backgroundPanel.add(controlPanel, gbc);

        //BACK BUTTON
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false); // Make the bottom panel transparent
        backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setPreferredSize(new Dimension(120, 65));
        bottomPanel.add(backButton);
        backButton.setBackground(Color.decode("#FFB6C1"));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DifficultyFrame();
                mainFrame.dispose();
            }
        });


        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        backgroundPanel.add(bottomPanel, gbc);

        //MENU BAR
        MineSweeperMenuBar menuBar = new MineSweeperMenuBar(this, board);
        mainFrame.setJMenuBar(menuBar);

        //TIMER
        timer = new Timer(1000, e -> {
            elapsedTime++;
            timerLabel.setText("Time: " + elapsedTime + "s");
        });
        timer.start();


        mainFrame.setVisible(true);
    }

    public void startTimer() {

        timer.start();
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int time) {
        this.elapsedTime = time;
        timerLabel.setText("Time: " + elapsedTime + "s");
    }

    public void stopElapsedTime() {
        timer.stop();
    }


}
