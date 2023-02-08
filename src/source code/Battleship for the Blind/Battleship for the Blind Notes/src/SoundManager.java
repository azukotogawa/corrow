import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

public class SoundManager {
    private static Vector soundList;

    public static void INIT() {
        soundList = new Vector();
    }
    
    public static Clip LOAD(String soundPath, String soundFileName) {
        File file = new File(soundPath + File.separator + soundFileName);
        Clip audioClip = null;
        AudioInputStream audioInputStream = null;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (Exception e) {e.printStackTrace();}
            
        if (audioInputStream != null) {
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format, AudioSystem.NOT_SPECIFIED);

            try {
                audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioInputStream);
                audioClip.drain();
                soundList.addElement(audioClip);
            } catch (Exception e) {
                e.printStackTrace();
                audioClip = null;
            }
        }
        
        return audioClip;
    }

    /** Flushes and closes all sound clips, and empties the sound list. */
    public static void SHUT_DOWN() {
        for (int i=0; i<soundList.size(); i++) {
            Clip audioClip = (Clip)soundList.elementAt(i);
            audioClip.stop();
            audioClip.flush();
            audioClip.close();
        }
        
        // Remove all Clips from the sound list.
        soundList.removeAllElements();
        
        // Encourage garbage collection.
        System.gc();
    }
 
    /** Play a sound previously read in using LOAD(). */
    public static void PLAY(Clip clip) {
        if (clip != null) {
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
            clip.loop(0);
            
            // Wait for the sound to finish.
            while (clip.isRunning()) {}
        }
    }
 }
