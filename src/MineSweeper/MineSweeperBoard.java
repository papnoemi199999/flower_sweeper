package MineSweeper;

import Audio.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.stream.IntStream;

public class MineSweeperBoard {

    private JPanel boardPanel;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] flags;
    private int rows;
    private int cols;
    private int mineCount;
    private ImageIcon flagIcon;
    private ImageIcon bombIcon;
    private JLabel timerLabel;
    private MineSweeperFrame frame;
    private boolean victoryAchieved = false;

    private Sounds sounds;

    public MineSweeperBoard(MineSweeperFrame frame, JLabel timerLabel, int rows, int cols) {
        this.timerLabel = timerLabel;
        this.rows = rows;
        this.cols = cols;
        this.frame = frame;
        sounds = new Sounds();


        this.mineCount = (int) (rows * cols * 0.15);
        //this.mineCount = 1;


        flagIcon = ImageLoader.loadImage("gif/flag.png");
        bombIcon = ImageLoader.loadImage("gif/bomb.png");
        boardPanel = new JPanel(new GridLayout(rows, cols));
        boardPanel.setPreferredSize(new Dimension(800, 800));
        resetBoard();
    }

    public void resetBoard() {
        boardPanel.removeAll();
        buttons = new JButton[rows][cols];
        mines = new boolean[rows][cols];
        flags = new boolean[rows][cols];

        //mines
        Random random = new Random();
        for (int i = 0; i < mineCount; i++) {
            int r, c;
            do {
                r = random.nextInt(rows);
                c = random.nextInt(cols);
            } while (mines[r][c]);
            mines[r][c] = true;
        }

        //buttons actions
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JButton button = new JButton();
                button.setIcon(null);

                button.setBackground(Color.PINK);

                int finalR = r;
                int finalC = c;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            handleRightClick(finalR, finalC);
                        } else if (e.getButton() == MouseEvent.BUTTON1) {
                            handleLeftClick(finalR, finalC);
                        }
                    }
                });
                buttons[r][c] = button;
                boardPanel.add(button);
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    //right click - flags
    private void handleRightClick(int r, int c) {
        if (!buttons[r][c].isEnabled()) {
            return;
        }

        if (flags[r][c]) {
            buttons[r][c].setIcon(null);
            flags[r][c] = false;
        } else {
            sounds.placeingEffect();
            buttons[r][c].setIcon(flagIcon);
            flags[r][c] = true;
        }
        checkVictory();
    }

    //left click - exploring
    private void handleLeftClick(int r, int c) {
        if (!buttons[r][c].isEnabled()) {
            return;
        }

        if (flags[r][c]) return;
        if (mines[r][c]) {
            sounds.bombEffect();
            buttons[r][c].setIcon(bombIcon);
            gameOver();
        } else {
            int mineCount = countAdjacentMines(r, c);
            buttons[r][c].setText(mineCount == 0 ? "" : String.valueOf(mineCount));
            buttons[r][c].setFont(new Font("Arial", Font.BOLD, 10));
            buttons[r][c].setBackground(Color.WHITE);
            buttons[r][c].setEnabled(false);
            if (mineCount == 0) {
                revealAdjacent(r, c);
            }
        }
        checkVictory();
    }

//    public int countAdjacentMines(int r, int c) {
//        int count = 0;
//        for (int i = -1; i <= 1; i++) {
//            for (int j = -1; j <= 1; j++) {
//                int nr = r + i;
//                int nc = c + j;
//                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && mines[nr][nc]) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }

    public int countAdjacentMines(int r, int c) {
        return (int) IntStream.rangeClosed(-1, 1)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(-1, 1)
                        .mapToObj(j -> new Point(r + i, c + j)))
                .filter(p -> p.x >= 0 && p.x < rows && p.y >= 0 && p.y < cols && mines[p.x][p.y])
                .count();
    }


    //    private void revealAdjacent(int r, int c) {
//        for (int i = -1; i <= 1; i++) {
//            for (int j = -1; j <= 1; j++) {
//                int nr = r + i;
//                int nc = c + j;
//                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && buttons[nr][nc].isEnabled()) {
//                    handleLeftClick(nr, nc);
//                }
//            }
//        }
//    }
    private void revealAdjacent(int r, int c) {
        IntStream.rangeClosed(-1, 1)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(-1, 1)
                        .mapToObj(j -> new Point(r + i, c + j)))
                .filter(p -> p.x >= 0 && p.x < rows && p.y >= 0 && p.y < cols && buttons[p.x][p.y].isEnabled())
                .forEach(p -> handleLeftClick(p.x, p.y));
    }


    private void gameOver() {
        frame.stopElapsedTime();
        victoryAchieved = true;
        JOptionPane.showMessageDialog(boardPanel, "Game Over!");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mines[r][c]) {
                    buttons[r][c].setIcon(bombIcon);
                }
                buttons[r][c].setEnabled(false);
            }
        }
    }

//    private void checkVictory() {
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < cols; c++) {
//                if (buttons[r][c].isEnabled() && !mines[r][c] && !victoryAchieved) {
//                    return;
//                }
//            }
//        }
//        victory();
//    }

    private void checkVictory() {
        boolean allRevealed = IntStream.range(0, rows)
                .boxed()
                .flatMap(r -> IntStream.range(0, cols)
                        .mapToObj(c -> new Point(r, c)))
                .allMatch(p -> !buttons[p.x][p.y].isEnabled() || mines[p.x][p.y]);

        if (allRevealed) {
            victory();
        }
    }

    private void victory() {
        if (victoryAchieved) {
            return;
        }
        victoryAchieved = true;
        sounds.clapEffect();

        frame.stopElapsedTime();
        JOptionPane.showMessageDialog(boardPanel, "Congratulations, you win!");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                buttons[r][c].setEnabled(false);
            }
        }
    }

    public boolean isVictoryAchieved() {
        return victoryAchieved;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean[][] getMines() {
        return mines;
    }

    public void setMines(boolean[][] mines) {
        this.mines = mines;
    }

    public boolean[][] getFlags() {
        return flags;
    }

    public void setFlags(boolean[][] flags) {
        this.flags = flags;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public Icon getBombIcon() {
        return bombIcon;
    }

    public void setBombIcon(Icon bombIcon) {
        this.bombIcon = (ImageIcon) bombIcon;
    }

    public Icon getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(Icon flagIcon) {
        this.flagIcon = (ImageIcon) flagIcon;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }


}
