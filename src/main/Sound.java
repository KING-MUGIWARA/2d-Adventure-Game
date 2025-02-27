package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    private long clipTimePosition = 0; // Stores position when paused

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/fanfare.wav");
        soundURL[3] = getClass().getResource("/sound/powerup.wav");
        soundURL[4] = getClass().getResource("/sound/unlock.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
        clipTimePosition = 0; // Reset position
    }

    // NEW: Pause the music
    public void pause() {
        if (clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition(); // Save position
            clip.stop();
        }
    }

    // NEW: Resume from the paused position
    public void resume() {
        if (!clip.isRunning()) {
            clip.setMicrosecondPosition(clipTimePosition); // Resume from saved position
            clip.start();
        }
    }
}
