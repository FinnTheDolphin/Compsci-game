import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * This is my Projectile Class
 * @author robert.lancaster
 *
 */
public class Projectile extends GameObject {

	Image facingRight;
	Image facingLeft;
	int Vx;
	int Vy;
	
	/**
	 * This is how a Projectile is constructed.
	 * @param x The X Position of the Object
	 * @param y The Y Position of the Object
	 * @param w The Width of the Object
	 * @param h The Height of the Object
	 * @param p The GamePanel Reference
	 */
	public Projectile(int x, int y, int w,int h, GamePanel p, int Vx,int Vy, boolean facing) {
		super(x, y, w, h, p);
		facingRight = Toolkit.getDefaultToolkit().getImage("src/crossr.png");
		facingLeft = Toolkit.getDefaultToolkit().getImage("src/crossr.png");
		this.Vx = Vx;
		this.Vy = Vy;
		isFacingRight=facing;
	}

	/**
	 * This is how the Projectile gets drawn, it is based on which way the Projectile is going.
	 */
	public void draw(Graphics g) {
		if(isFacingRight)
			g.drawImage(facingRight, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
		else
			g.drawImage(facingLeft, rectangle.x ,rectangle.y, rectangle.width, rectangle.height, game);
	}
	
	/**
	 * This moves the projectile at the given velocity in the X and Y directions
	 */
	public void animate(){
		rectangle.x += Vx;
		rectangle.y += Vy;
	}

}
