package view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundHandler {
	
	
    public static void runMusic(String path, int i){
        try{
        	AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
	        Clip clip = AudioSystem.getClip();
	        
    		if (i == 1) {
	            clip.open(inputStream);
	            clip.loop(0);
	            clip.start();
    		}
    		
    		if (i == 0) {
    			clip.stop();		
        	}
            
        }  
        
        
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
