import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * This is the main character in the game.  It is ME!
 * @author robert.lancaster
 *
 */
public class Character extends GameObject {

	Image knightfacingRight;
	Image knightfacingLeft;
	
	/**
	 * This is how a Main Character gets constructed
	 * @param x The X Position of the Object
	 * @param y The Y Position of the Object
	 * @param w The Width of the Object
	 * @param h The Height of the Object
	 * @param p The GamePanel Reference
	 */
	public Character(int x, int y, int w, int h, GamePanel p){
		super(x, y, w, h, p);
		isFacingRight = true;
		knightfacingRight = Toolkit.getDefaultToolkit().getImage("src/character.png");
		knightfacingLeft = Toolkit.getDefaultToolkit().getImage("src/characterleft.png");
	}
	
	/**
	 * This is how the Main Character gets drawn.  He is drawn differently if facing right or left.
	 */
	public void draw(Graphics g){
		if(isFacingRight)
			g.drawImage(knightfacingRight, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
		else
			g.drawImage(knightfacingLeft, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
	}
	
	/**
	 * This is how the Main Character fires projectiles.
	 */
	public void fire(){
		int x;
		if(isFacingRight)
			x = 10;
		else
			x = -10;
		int y = 0;
		System.out.println("FIRE!");
		game.projectiles.add(0, new Projectile(rectangle.x, rectangle.y, 100, 25, game, x, y, isFacingRight));
	}
	
	/**
	 * This is how the Main Character gets moved.  Note that the Y direction is fairly normal in that it just gets changed by y amount
	 * In the x direction though, if you move the character X far, it doesn't move the character, it shifts the whole game instead.
	 * In the y direction, his movement is restricted to the bottom of the screen.
	 * @param x The shift of the character in the X direction
	 * @param y The shift of the character in the Y direction
	 */
	public void move(int x,int y){
		game.xShift -= x;
		//System.out.println(x+","+y+","+rectangle.y);
		if(rectangle.y >= 400 && ( rectangle.y < 600 || y < 0 ) )
			rectangle.y += y;
		if(rectangle.y < 400)
			rectangle.y = 400;
	}
	
	/**
	 * This animates the Character.  It really just makes sure that he stays in the allowed zone.
	 */
	public void animate(){
		if(rectangle.y < 400)
			rectangle.y += 4;
	}
}
