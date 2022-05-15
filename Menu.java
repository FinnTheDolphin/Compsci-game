import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class Menu {
	GamePanel game;	
public Image menubg;



public void paint(Graphics g) {
	
		menubg=Toolkit.getDefaultToolkit().createImage("src/swaggy bones.jpg");
		g.drawImage(menubg, 1400, 0, 1400, 700, game);
		
	}

		
}
 