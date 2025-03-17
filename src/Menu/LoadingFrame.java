package Menu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoadingFrame extends JFrame {
    private JProgressBar progressBar;

    public LoadingFrame() {
        setTitle("Loading...");
        setSize(1500, 1000);
        setBackground(Color.decode("#FFFFFF"));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JLabel gifLabel = new JLabel();
        ImageIcon loadingGif = new ImageIcon("gif/LoadingBomb.gif");
        gifLabel.setIcon(loadingGif);
        gifLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gifLabel, BorderLayout.CENTER);


        JPanel progressBarPanel = new JPanel();
        progressBarPanel.setLayout(new BorderLayout());

        progressBarPanel.setPreferredSize(new Dimension(getWidth(), 50));
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBarPanel.add(progressBar, BorderLayout.CENTER);

        add(progressBarPanel, BorderLayout.SOUTH);


        simulateLoading();
    }

    private void simulateLoading() {
        Timer timer = new Timer(10, e -> {
            int value = progressBar.getValue();
            if (value < 100) {
                progressBar.setForeground(Color.pink);
                progressBar.setBorder(new LineBorder(Color.BLACK, 2));
                progressBar.setValue(value + 1);

            } else {
                ((Timer) e.getSource()).stop();
                dispose();
            }
        });
        timer.start();
    }

}