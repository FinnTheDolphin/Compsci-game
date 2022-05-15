import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;



/**
 * Here is the GamePanel class, this handles the world basically!
 * Note this game is really bad and would not earn many points.
 * It is just a start
 * @author robert.lancaster
 *
 */
public class GamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;	// I added this to make it shut up about the warning this wasn't there.  It isn't important
	public ArrayList<GameObject> enemies;				// This is a list of enemies or other objects in the game.
	public ArrayList<GameObject> projectiles;			// This is a list of my projectiles in the game.
	private Enemy bossone;
	private Character mainCharacter;	// It is me!!!
	boolean gameRunning;				// Whether the game is running or not.  Could be used to stop the thread or display an end game image.
	public GameThread thread;			// The thread that runs the game.
	
	// These keep track of which buttons are pressed right now.
	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;
	public boolean spacePressed;
	public boolean stopclip;
	public int xShift = 0;			// This is my silly side scrolling integer, it is not needed if you don't have a side scroller
	public Image background;		// This is the background image of the mountains
	private int lastTimeFired = 0;	// This is my way to keep track of the time of the last shot fired, to know if we can fire again.
	private Menu menu;
	public Image menubg;
	public Image charbg; 
	/**
	 * This constructs a Game Panel
	 */
	public GamePanel(){
		this.setFocusable(true);					// Very important so that we can get the focus on the panel and use arrrow keys.
		enemies = new ArrayList<GameObject>();		// Makes the enemy list	
		projectiles = new ArrayList<GameObject>();	// Makes the projectile list	
		this.addKeyListener(this);					// This lets it listen for key inputs
		bossone = new Enemy(1775, 600, 125, 150, this);
		mainCharacter = new Character(20, 400, 75, 100, this);	//This is how I make my main character
		this.setBackground(Color.black);						// I set the background to black, but you might never see it anyway
		background=Toolkit.getDefaultToolkit().createImage("src/photo-1448375240586-882707db888b.jpg"); // This loads the image I will use for a background.
		menubg=Toolkit.getDefaultToolkit().createImage("src/shwag mone.jpg");
		charbg=Toolkit.getDefaultToolkit().createImage("src/chars.jpg");
		
	
	
		
	}
	
	/**
	 * This paints the game.  You should never call this method, the program will do it automatically
	 */
	


	
			 
	public boolean checkforclip() {
		if (stopclip== false)
			return false;
		else if (stopclip== true);
		return true;
	}
	

	
	
	
	
	
	Menu m = new Menu();
	public void paint(Graphics g){
		super.paint(g);
		// The image has to get drawn 3 times for the side scrolling stuff.  And they all have to move when scrolling.
		//g.drawImage(background, xShift%1400-1400, 0, 1400, 700, this);
		//g.drawImage(background, xShift%1400, 0, 1400, 700, this);
		//g.drawImage(background, xShift%1400+1400, 0, 1400, 700, this);
		
		if(state== STATE.GAME){
			g.drawImage(background, 0, 0, 1920, 1080 , this);
			mainCharacter.draw(g);
			if(bossone.alive==true) {
			bossone.draw(g);}
			for(GameObject object:projectiles) {
				object.draw(g);
			}
		}else if (state == STATE.MENU){
			g.drawImage(menubg, 300, 0, 1280,720 , this);
			System.out.println("why is this not working?");
			
			// It would be good to draw an image instead of the things above if the game was over, one if you win, another if you lose
		}
		else if (state == STATE.CHARSELECT) {
			g.drawImage(charbg, 0, 0, 1920, 1080 , this);
			System.out.println("working?");
		}
	}
	
	/**
	 * This is how the game gets animated.  This is called by the Thread every "tick" or time
	 * @param time The current time in the game.
	 */
	public void animate(int time){
		if(upPressed){
			mainCharacter.move(0, -1);
		}
		if(downPressed){
			mainCharacter.move(0, 1);
		}
		if(leftPressed){
			//mainCharacter.isFacingRight=false;
			mainCharacter.move(-1, 0);
		}
		if(rightPressed){
			//mainCharacter.isFacingRight=true;
			mainCharacter.move(1, 0);
		}
		if(spacePressed && time - lastTimeFired > 20){
			lastTimeFired = time;
			mainCharacter.fire();
		}
	
			bossone.animate();
		
			
		
		for(GameObject object:projectiles){
			object.animate();
		}
		removeObjectsifColliding();
	}
	
	/**
	 * This is how I handle collisions between objects.  I determine if their rectangles overlap and then do something if they do.
	 */
	public void removeObjectsifColliding(){
		for(GameObject object:enemies){
			if(mainCharacter.rectangle.intersects(object.rectangle))
				System.out.println("OUCH an enemy hit me!");
			// Something else should probably happen here, like points taken away, hit points, moving back to start, or game over.
		}
		
		for(int projectile = 0; projectile<projectiles.size(); projectile++){
		
				GameObject p = projectiles.get(projectile);
				
				
				if(p.rectangle.intersects(bossone.rectangle)) {
					projectiles.remove(p); 
					
					Enemy.health-=100;
				if(Enemy.health<=0) {
					bossone.alive =false;
				}
					
					
					
					return;
				}
			
		}
	}
	private enum STATE{
		MENU,
		CHARSELECT,
		GAME,
		NULL
	};
	
	private STATE state = STATE.NULL;
	
	public int getState() {
		if (state == STATE.MENU)
			return 1;
		else if (state == STATE.CHARSELECT)
			return 2;
		else if (state == STATE.GAME)
			return 3;
		else { 
		return 0; }
	}
	public void setState(int a) {
		if (a== 1)
			state= STATE.MENU;
		else if (a== 2)
			state= STATE.CHARSELECT;
		else if (a== 3)
			state= STATE.GAME;
		
	}
	/**
	 * This starts a game!
	 */
	musicplayer playa = new musicplayer();
	public void startMenu() {
		 
		state = STATE.MENU;
		if(playa.isfirstplay()== false) {
		playa.stopmusic();}
		System.out.println("menu time baby");
		
		 playa.playmusic("Dark Souls 3 OST Main Theme.wav");
		  JButton b=new JButton();  
		    b.setBounds(0, 0, 1920, 1080);;  
		    b.addActionListener(new ActionListener(){	
				public void actionPerformed(ActionEvent e){ 
				System.out.println("i was pressed");
				characterSelect();
				
				playa.stopmusic();
				playa.playmusic("Elden Ring OST - Roundtable Hold.wav");
			removebutton(b);
				}
			});	
		   
			
		    b.setOpaque(false);
		    b.setContentAreaFilled(false);
		    b.setBorderPainted(false);
		    this.add(b); 
		  
	}
	public void removebutton(JButton ButtonName) {
		this.remove(ButtonName);
	}
	Classes c = new Classes();
	public void characterSelect() {
		
		setState(2);
		JButton one=new JButton(); 
		JButton two=new JButton(); 
		JButton three=new JButton(); 
		JButton	four=new JButton(); 
		JButton five=new JButton(); 
		one.setBounds(92, 97, 275, 500);
		two.setBounds(466, 97,275, 500);
		three.setBounds(797,97, 275,500);
		four.setBounds(1184,97,275, 500);
		five.setBounds(1544, 97,275, 500);
	    one.addActionListener(new ActionListener(){	
					public void actionPerformed(ActionEvent e){ 
				c.setClass(4);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
				four.setVisible(false);
				five.setVisible(false);
				setState(3);
				newGame();
				
					}
				});	
	    two.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){ 
				c.setClass(1);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
				four.setVisible(false);
				five.setVisible(false);
				setState(3);
				newGame();
			}
		});
	    three.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){ 
				c.setClass(2);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
				four.setVisible(false);
				five.setVisible(false);
				setState(3);
				newGame();
			}
		});
	    four.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){ 
				c.setClass(3);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
				four.setVisible(false);
				five.setVisible(false);
				setState(3);
				newGame();
			}
		});
	    five.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e){ 
				c.setClass(5);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
				four.setVisible(false);
				five.setVisible(false);
				setState(3);
				newGame();
			}
		});
			one.setOpaque(false);
		    one.setContentAreaFilled(false);
		    one.setBorderPainted(false);
		    this.add(one); 
		    two.setOpaque(false);
		    two.setContentAreaFilled(false);
		  two.setBorderPainted(false);
		    this.add(two); 
		    three.setOpaque(false);
		    three.setContentAreaFilled(false);
		    three.setBorderPainted(false);
		    this.add(three); 
		    four.setOpaque(false);
		    four.setContentAreaFilled(false);
		    four.setBorderPainted(false);
		    this.add(four); 
		    five.setOpaque(false);
		    five.setContentAreaFilled(false);
		    five.setBorderPainted(false);
		    this.add(five); 
	}
	
	public void newGame(){
		playa.stopmusic();
		 playa.playmusic("Lost Woods (Legend of Zelda_ Ocarina of Time)_ OST Remastered (1).wav");
		
		gameRunning = true;
		thread = new GameThread(this);
		thread.start();
		
		this.requestFocus();	//It is important to request the focus (or click the panel) so the arrow keys work!
	}
	
	/**
	 * This method loads my levels.  Right now, there is just one level! It is Zero!
	 * @param level The level to load
	 */
	public void loadLevel(int level){
		switch(level){
		case 0:
			for(int i=0; i<1000; i++){
			enemies.add(0, new Enemy((int)(Math.random()*100000), (int)(Math.random()*200+300), 150, 150, this));
			}
		}
	}
	
	
	/**
	 * This method turns on the booleans when the buttons are pressed.
	 */
	public void keyPressed(KeyEvent e){
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			leftPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rightPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			spacePressed = true;
		}
	
	}
	
	/**
	 * This method turns off the booleans when the buttons are let go.
	 */
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			leftPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rightPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			spacePressed = false;
		}
	}
	
	/**
	 * This method doesn't do anything, but it is needed for the Interface
	 */
	public void keyTyped(KeyEvent e){}
	
	
	
	
}
