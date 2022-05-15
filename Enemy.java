import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * This is the Enemy Class.  It handles how the enemies work.
 * @author robert.lancaster
 *
 */
public class Enemy extends GameObject {
	boolean alive = true;
	Image image; 	// The image of thine enemy!
	int xPos; 		// We need to keep track of the x position separately since the enemy's position will be shifted by scrolling
	int yPos;
	public static int health=1000;
	/**
	 * This is how an Enemy is constructed.
	 * @param x The X Position of the Object
	 * @param y The Y Position of the Object
	 * @param w The Width of the Object
	 * @param h The Height of the Object
	 * @param p The GamePanel Reference
	 */
	public Enemy(int x, int y, int w, int h, GamePanel p) {
		super(x, y, w, h, p);
		xPos = x;
		yPos = y;
		image = Toolkit.getDefaultToolkit().getImage("src/enemy.png");
	}

	/**
	 * This is how the Enemy gets animated.  They all move slowly to the left.
	 * They also shift with the side scrolling of the world.
	 */
	public void draw(Graphics g) {
		g.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);


	}
	
	static int flag= 1;
	public void animate(){
		
		
		if(flag %2==0) {
		if(rectangle.y >= 15 && rectangle.y<=900) 
			rectangle.y += 2;
		}
		else if(flag %2==1) {
			if(rectangle.y >= 15 && rectangle.y<=900) 
				rectangle.y -= 2;
			}
		
		if(rectangle.y <= 25||rectangle.y >= 880)
			flag++;
		if(rectangle.y <= 15)
			rectangle.y = 15;
		if(rectangle.y >= 900)
			rectangle.y = 900;
	
	}
	
	/**
	 * This is how the enemies get drawn.
	 */
	

}
