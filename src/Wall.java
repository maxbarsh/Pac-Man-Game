import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Rectangle {

	
	public Wall(int x, int y) {
		this.setBounds(x, y, 20, 20);
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(19, 27, 135));
		g.fillRect(x, y, width, height);
	}
}
