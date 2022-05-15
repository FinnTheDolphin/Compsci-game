import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * This is the main character in the game.  It is ME!
 * @author robert.lancaster
 *
 */
public class Character extends GameObject {

	
	Classes c = new Classes();
	Image knight;
	Image rouge;
	Image sorcerer;
	Image pyromancer;
	Image deprived;
	
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
		
		knight = Toolkit.getDefaultToolkit().getImage("src/character-removebg-preview.png");
		rouge = Toolkit.getDefaultToolkit().getImage("src/jamal-removebg-preview.png");
		sorcerer=Toolkit.getDefaultToolkit().getImage("src/Elden-Ring-Alberichs-Set.png");
		pyromancer=Toolkit.getDefaultToolkit().getImage("src/Ds3-firelink-armor-set-removebg-preview.png");
		deprived=Toolkit.getDefaultToolkit().getImage("src/Screen_Shot_2016-03-15_at_11.54.11_AM-removebg-preview.png");
	}
	
	/**
	 * This is how the Main Character gets drawn.  He is drawn differently if facing right or left.
	 */
	public void draw(Graphics g){
		System.out.println(c.getClassNumber());
		if(c.getClassNumber()==3)
			g.drawImage(knight, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
		
		else if (c.getClassNumber()==1)
			g.drawImage(rouge, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
		else if (c.getClassNumber()==2)
			g.drawImage(sorcerer, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
		else if (c.getClassNumber()==4)
			g.drawImage(pyromancer, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
		else if (c.getClassNumber()==5)
			g.drawImage(deprived, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);
		else {g.drawImage(knight, rectangle.x, rectangle.y, rectangle.width, rectangle.height, game);}
	}
	
	/**
	 * This is how the Main Character fires projectiles.
	 */
	public void fire(){
		int x;
		
			x = 10;
		
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
		
		int spdy= y * (Classes.speed);
		int spdx= x * (Classes.speed);
		if(rectangle.x >= 15 && rectangle.y<=1815) 
			rectangle.x += spdx;
		if(rectangle.x <= 15)
			rectangle.x = 15;
		if(rectangle.x >= 1815)
			rectangle.x = 1815;
		//System.out.println(x+","+y+","+rectangle.y);
		if(rectangle.y >= 15 && rectangle.y<=900) 
			rectangle.y += spdy;
		if(rectangle.y <= 15)
			rectangle.y = 15;
		if(rectangle.y >= 900)
			rectangle.y = 900;
	}
	
	/**
	 * This animates the Character.  It really just makes sure that he stays in the allowed zone.
	 */
	public void animate(){
		if(rectangle.y < 1080)
			rectangle.y += 4;
	}
}
