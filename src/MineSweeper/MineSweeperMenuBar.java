package MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MineSweeperMenuBar extends JMenuBar {

    private JMenu gameMenu;
    private JMenuItem newGameItem;
    private JMenuItem saveItem;
    private JMenuItem loadItem;
    private MineSweeperBoard board;
    private MineSweeperFrame frame;

    public MineSweeperMenuBar(MineSweeperFrame frame, MineSweeperBoard board) {
        this.board = board;
        this.frame = frame;

        gameMenu = new JMenu("Game");

        newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> newGame());
        gameMenu.add(newGameItem);

        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> saveGame());
        gameMenu.add(saveItem);

        loadItem = new JMenuItem("Load");
        loadItem.addActionListener(e -> loadGame());
        gameMenu.add(loadItem);

        this.add(gameMenu);
    }

    //NEW GAME
    public void newGame() {
        board.resetBoard();
        frame.startTimer();
        frame.setElapsedTime(0);
    }

    //SAVING THE GAMES CURRENT STATE
    private void saveGame() {
        if (board.isVictoryAchieved()) return;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose location to save the game");
        int userSelection = fileChooser.showSaveDialog(null);

        //rows & cols
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(board.getRows() + "," + board.getCols());
                writer.newLine();

                //mines
                boolean[][] mines = board.getMines();
                for (int r = 0; r < board.getRows(); r++) {
                    for (int c = 0; c < board.getCols(); c++) {
                        writer.write(mines[r][c] ? "1" : "0");
                    }
                    writer.newLine();
                }

                //flags
                boolean[][] flags = board.getFlags();
                for (int r = 0; r < board.getRows(); r++) {
                    for (int c = 0; c < board.getCols(); c++) {
                        writer.write(flags[r][c] ? "1" : "0");
                    }
                    writer.newLine();
                }

                //enabled or not
                JButton[][] buttons = board.getButtons();
                for (int r = 0; r < board.getRows(); r++) {
                    for (int c = 0; c < board.getCols(); c++) {
                        writer.write(buttons[r][c].isEnabled() ? "1" : "0");
                    }
                    writer.newLine();
                }

                //time
                writer.write(String.valueOf(frame.getElapsedTime()));
                writer.newLine();

                JOptionPane.showMessageDialog(null, "Game saved successfully to " + file.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving game: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //LOADING THE GAME
    private void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a saved game to load");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                //rows & cols
                String[] dimensions = reader.readLine().split(",");
                int rows = Integer.parseInt(dimensions[0]);
                int cols = Integer.parseInt(dimensions[1]);

                //checking if dimensions match
                if (rows != board.getRows() || cols != board.getCols()) {
                    JOptionPane.showMessageDialog(null, "Saved game dimensions do not match the current board size.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //mines
                boolean[][] mines = new boolean[rows][cols];
                for (int r = 0; r < rows; r++) {
                    String line = reader.readLine();
                    for (int c = 0; c < cols; c++) {
                        mines[r][c] = line.charAt(c) == '1';
                    }
                }
                board.setMines(mines);

                //flags
                boolean[][] flags = new boolean[rows][cols];
                for (int r = 0; r < rows; r++) {
                    String line = reader.readLine();
                    for (int c = 0; c < cols; c++) {
                        flags[r][c] = line.charAt(c) == '1';
                    }
                }
                board.setFlags(flags);

                //enabled and text
                JButton[][] buttons = board.getButtons();
                for (int r = 0; r < rows; r++) {
                    String line = reader.readLine();
                    for (int c = 0; c < cols; c++) {
                        boolean enabled = line.charAt(c) == '1';
                        buttons[r][c].setEnabled(enabled);
                        buttons[r][c].setText("");
                        buttons[r][c].setIcon(null);
                        if (!enabled) {
                            buttons[r][c].setFont(new Font("Arial", Font.BOLD, 10));
                            buttons[r][c].setBackground(Color.WHITE);
                            int count = board.countAdjacentMines(r, c);
                            buttons[r][c].setText(count == 0 ? "" : String.valueOf(count));
                        }
                    }
                }
                //flag icons
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        if (flags[r][c]) {
                            buttons[r][c].setIcon(board.getFlagIcon());
                        }
                    }
                }

                //time
                int elapsedTime = Integer.parseInt(reader.readLine());
                frame.setElapsedTime(elapsedTime);


                JOptionPane.showMessageDialog(null, "Game loaded successfully from " + file.getAbsolutePath());
            } catch (IOException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error loading game: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
