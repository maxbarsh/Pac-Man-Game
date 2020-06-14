import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cookie extends Rectangle {

	//right now it is a 2x2 white box inside a 20x20 black box, if pac man comes in contact w/ black box he eats cookie, change 20 to 2 and 2 to height/width in render to fix this
	
	
	public Cookie(int x, int y) {
		this.setBounds(x, y, 20, 20);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x+9, y+9, 2, 2);
	}
}
