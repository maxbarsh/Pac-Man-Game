import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PacMan extends Rectangle {

	public boolean up, down, left, right;
	private int speed = 3;
		
	public PacMan(int x, int y) {
		this.setBounds(x,y,18,18);
	}
	
	
	public void tick() {
		if(up && canMove(x, y - speed)) {
			y -= speed;
		}
		
		if(down && canMove(x, y + speed)) {
			y += speed;
		}
		
		if(left && canMove(x - speed, y)) {
			x -= speed;
		}
		
		if(right && canMove(x + speed, y)) {
			x += speed;
		}		
	}
	
	private boolean canMove(int nextX, int nextY) {
		Rectangle next = new Rectangle(nextX, nextY, width, height);
		
		for(int i = 0; i < Level.walls.length; i++) {
			for(int j = 0; j < Level.walls[0].length; j++) {
				if(Level.walls[i][j] != null) {
					if(next.intersects(Level.walls[i][j])) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	}	
}
