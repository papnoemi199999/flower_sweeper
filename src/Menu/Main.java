package Menu;

import Audio.Sounds;
import javax.swing.*;
public class Main {

    private MenuFrame menuframe;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoadingFrame loadingFrame = new LoadingFrame();
            loadingFrame.setVisible(true);

            new Timer(100, e -> {
                if (!loadingFrame.isDisplayable()) {
                    ((Timer) e.getSource()).stop();
                    MenuFrame menuFrame = new MenuFrame();
                }
            }).start();
        });
    }
}
