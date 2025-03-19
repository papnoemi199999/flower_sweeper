package Menu;

import javax.swing.*;
import java.awt.*;

public class DescriptionFrame extends JFrame {
    public DescriptionFrame() {
        setTitle("Minesweeper – Game Rules");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 22));
        textArea.setText("Minesweeper – Game Rules\n\n" +
                "Grid: The game grid is a square layout with flowers.\n" +
                "Cells: Clicking on cells reveals them; empty cells also uncover adjacent cells.\n" +
                "Numbers: Revealed cells show numbers indicating how many flowers are in the surrounding cells.\n" +
                "Flag: Players can mark suspected flower locations with flags.\n" +
                "End of Game: The game ends if a flower is clicked (loss) or if all mines are successfully avoided and the grid is revealed (win).\n" +
                "Objective: Explore the grid without hitting flowers, using logical deductions to navigate.");

        textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textArea, BorderLayout.CENTER);
        textPanel.setPreferredSize(new Dimension(1500, 600));
        mainPanel.add(textPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setBackground(Color.decode("#FFB6C1"));
        backButton.setPreferredSize(new Dimension(200, 100));
        backButton.setMargin(new Insets(10, 20, 10, 20));

        backButton.addActionListener(e -> {
            new MenuFrame();
            dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

}
