package MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

    public static ImageIcon loadImage(String path) {
        try {
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                Image scaledImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);  // Larger size
                return new ImageIcon(scaledImage);
            } else {
                System.out.println("Failed to load image from path: " + path);
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        return null;
    }

}
