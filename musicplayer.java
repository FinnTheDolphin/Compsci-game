import java.io.*;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

import javax.sound.sampled.*;
import javax.swing.*;
import java.lang.Object;
import maryb.player.Player;



// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class musicplayer   {

	private Clip clip;
	 boolean firstplay= true;
//GamePanel game = new GamePanel();
   public void playmusic(String filepath) {
	   
	 
     try {
    	
         // Open an audio input stream.
     
    	
    	   URL url = this.getClass().getClassLoader().getResource(filepath);
    	   AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
    	   clip = AudioSystem.getClip();
         // Get a sound clip resource.
         
         // Open audio clip and load samples from the audio input stream.
    	
         clip.open(audioIn);
         clip.loop(-1);
      
    	  
    		  
    		   
    	    firstplay = false;
       
       
        
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      } 
}
   public void playmusiconce(String filepath) {
	   
		 
	     try {
	    	
	         // Open an audio input stream.
	     
	    	
	    	   URL url = this.getClass().getClassLoader().getResource(filepath);
	    	   AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	    	   clip = AudioSystem.getClip();
	         // Get a sound clip resource.
	         
	         // Open audio clip and load samples from the audio input stream.
	    	
	         clip.open(audioIn);
	         clip.start();
	      
	    	  
	    		  
	    		   
	    	  
	       
	       
	        
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      } 
	}
   public void stopmusic() {
	   // Open audio clip and load samples from the audio input stream.

	   clip.stop(); 
	   

}
   
public boolean isfirstplay() {
	if (firstplay == true)
		return true;
	else if (firstplay == false)
		return false;
	else
	{return true;}
}





  
}