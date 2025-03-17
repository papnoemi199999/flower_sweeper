package Audio;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Sounds {
    private Clip clip;
    private int value = 0;
    private static Sounds instance;

    private boolean isPlaying = false;

    public Sounds() {}

    public static Sounds getInstance() {
        if (instance == null) {
            instance = new Sounds();
        }
        return instance;
    }

    public void base() {
        if (isPlaying) {
            return;
        }

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Base2.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            isPlaying = false;
        }
    }
    public void bombEffect() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Explosion.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }
    public void clapEffect() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Applause.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }
    public void placeingEffect() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Place.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(6.0f);

            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        } catch (IllegalArgumentException e) {
            System.out.println("Volume control not supported.");
        }
    }

}
