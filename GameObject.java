import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This is the super class for the GameObjects.  It handles things that are true for all the game objects.
 * It also lets you make ArrayLists or arrays of all the game objects.
 * @author robert.lancaster
 *
 */
public abstract class GameObject {

	public Rectangle rectangle;		// This is a rectangle that represents where the GameObject is located in space.
	public boolean isFacingRight;	// This is a boolean for which way the object is facing.
	GamePanel game;					// This a reference to the GamePanel.  It is useful in some child classes sometimes.			
	
	/**
	 * This constructs a GameObject
	 * @param x The X Position of the Object
	 * @param y The Y Position of the Object
	 * @param w The Width of the Object
	 * @param h The Height of the Object
	 * @param p The GamePanel Reference
	 */
	public GameObject(int x, int y, int w, int h, GamePanel p){
		rectangle = new Rectangle(x, y, w, h);
		game = p;
	}
	
	/** 
	 * This is how the child classes will draw themselves.
	 * @param g the Graphics Object
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * This is how the child classes will animate themselves.
	 */
	public abstract void animate();
}
