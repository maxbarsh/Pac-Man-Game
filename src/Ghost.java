import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ghost extends Rectangle {

	private Color _color;
	
	public PacMan player;

	private final int DUMB = 0;
	private final int SMART = 1;
	private final int PATHFIND = 2;

	private int state = DUMB;

	private final int UP = 0;
	private final int DOWN = 1;
	private final int LEFT = 2;
	private final int RIGHT = 3;

	private int direction = -1;
	private int previousDirection = -1;

	private int time = 0;
	private int targetTime = 60*20;

	public static int speed = (1 + Level.roundNum);

	private Random randomGenerator;


	public Ghost(int x, int y, Color c) {
		_color = c;
		player = Game.player;
		this.setBounds(x, y, 18, 18);
		randomGenerator = new Random();
		direction = randomGenerator.nextInt(4);
	}

	public void tick() {
		if(state == DUMB) {

			switch(direction) {

				case UP: 
					if(canMove(x, y - speed)) {
						y -= speed;
					} else {
						direction = randomGenerator.nextInt(4);
					}
					break;
		
				case DOWN:
					if(canMove(x, y + speed)) {
						y += speed;
					} else {
						direction = randomGenerator.nextInt(4);
					}
					break;
		
				case LEFT:
					if(canMove(x - speed, y)) {
						x -= speed;
					} else {
						direction = randomGenerator.nextInt(4);
					}
					break;
		
				case RIGHT:
					if(canMove(x + speed, y)) {
						x += speed;
					} else {
						direction = randomGenerator.nextInt(4);
					}
					break;

			}
			time++;

			if(time == targetTime) {
				state = SMART;
				System.out.println("smart");
			}

		} else if(state == SMART) {

			boolean move = false;

			if( (y < player.y) && (canMove(x, y + speed)) ) { 
				y += speed;
				move = true;
				previousDirection = this.DOWN;
			}

			if( (y > player.y) && (canMove(x, y - speed)) ) {
				y -= speed;
				move = true;
				previousDirection = this.UP;
			}


			if( (x > player.x) && (canMove(x - speed, y)) ) {
				x -= speed;
				move = true;
				previousDirection = this.LEFT;
			}

			if( (x < player.x) && (canMove(x + speed, y)) ) { 
				x += speed;
				move = true;
				previousDirection = this.RIGHT; 
			}

			if(x == player.x && y == player.y) {  //ghost killed pac man
				move = true;
			}

			if(!move) {
				state = this.PATHFIND;
			}


		} else if(state == PATHFIND) {
			
			
			switch(previousDirection) {

				case UP:
					if( (x <= player.x) && (canMove(x + speed, y)) ) {  //go right
						x += speed;
						state = this.SMART;
						return;
	
					} else if((x > player.x) && (canMove(x - speed, y)) ){  //go left
							x -= speed;
							state = this.SMART;
							return;
							
					} else { //go down
						if(canMove(x, y + speed)) {
							y += speed;
							return;
						}
					}
	
					if(canMove(x, y - speed)) { //go up
						y -= speed;
					}
	
					break;
	
				case DOWN:
					if( (x <= player.x) && (canMove(x + speed, y)) ) { //go right
							x += speed;
							state = this.SMART;
							return;
							
					} else if( (x > player.x) && (canMove(x - speed, y)) ){ //go left
							x -= speed;
							state = this.SMART;
							return;
							
					} else {  //go up
						if(canMove(x, y - speed)) { 
							y -= speed;
							return;
						}
					}
	
					if(canMove(x, y + speed)) {  //go down
						y += speed;
					}
	
					break;
	
				case LEFT:
					if( (y <= player.y) && (canMove(x, y + speed)) ) {  //go down
							y += speed;
							state = this.SMART;
							return;
							
					} else if( (y > player.y) && (canMove(x, y - speed)) ){  //go up 
							y -= speed;
							state = this.SMART;
							return;
							
					} else {  //go right
						if(canMove(x + speed, y)) {
							x += speed;
							return;
						}
					}
	
					if(canMove(x - speed, y)) {  //go left
						x -= speed;
					}
					
					break;
	
				case RIGHT:
					if( (y <= player.y) && (canMove(x, y + speed)) ) {  //go down
							y += speed;
							state = this.SMART;
							return;
							
					} else if( (y > player.y) && (canMove(x, y - speed)) ) {  //go up 
							y -= speed;
							state = this.SMART;
							return;
							
					} else {  //go left
						if(canMove(x - speed, y)) {
							x -= speed;
							return;
						}
					}
	
					if(canMove(x + speed, y)) { //go right
						x += speed;
					}
	
					break;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(_color);
		g.fillRect(x, y, 20, 20);
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
}
