import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
/**
 * This is the game thread, it will run the game while it is running and keep telling things to happen.
 * @author robert.lancaster
 *
 */
public class GameThread extends Thread implements KeyListener {
	private Menu menu;
	private GamePanel game; 	// A reference to the GamePanel
	private boolean running; 	// A boolean to determine whether the game should keep running.
	private int time; 			// To keep track of a number for time
	private boolean spacePressed;
	
	/**
	 * This is how you construct the thread.  It needs the GamePanel so it can call its methods.
	 * @param g The GamePanel Object
	 */
	public GameThread(GamePanel g){
		game = g;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			spacePressed = true;
			System.out.println("space was pressed but didnt work");
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			spacePressed = false;}
	}
	
	/**
	 * This is the loop that keeps running and running while the game is going.
	 */
	   

	

	 static JLabel obj1 = new JLabel();
	
	
	
	 
	GamePanel gameP = new GamePanel();
	
	public void run(){
		   
		if (gameP.getState()== 3) {
		running = true;
		while(running){
			time += 1;
			game.animate(time);
			game.repaint();
			try{
				sleep(10);	// This is important so the game takes a pause for 10 milliseconds at least, or it will freeze.
			}catch(Exception e){};}}
		
		while(gameP.getState()== 1) {
			
		}
	
	}
	/**
	 * This is a command to end the game.
	 */
	public void endGame(){
		running = false;
	}

}
