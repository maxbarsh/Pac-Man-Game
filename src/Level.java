import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Level {

	
	private int width;
	private int height;
	
	public static Wall[][] walls;
	public static ArrayList<Cookie> cookies;
	public static ArrayList<Ghost> ghosts; 
	public static int roundNum;
	
	public final int PATH = 0;
	public final int WALL = 1;
	public final int PACMAN = 2;
	public final int GHOST = 3;
	
	public Level(String path, int r) {
		
		cookies = new ArrayList<>();
		ghosts = new ArrayList<>();
		roundNum = r;
				
				
		try {
			BufferedImage map = ImageIO.read(this.getClass().getResource(path));
			this.width = map.getWidth();
			this.height = map.getHeight();
			
			int[] pixels = new int[width*height];
			
			walls = new Wall[width][height];
			
			map.getRGB(0, 0, width, height, pixels, 0, width);
			
			for(int i = 0; i < width; i++) {
				for(int j = 0; j < height; j++) {
					int val = pixels[i + (j*width)];
					
					if(val == 0xFF000000) {  //wall
						walls[i][j] = new Wall(i*20, j*20);
					} else if(val == 0xFFF1FF26) {  //pacman
						Game.player.x = i * 20;
						Game.player.y = j * 20; 
					} else if(val == 0xFFFF1212) {  //red ghost
						ghosts.add(new Ghost(i*20, j*20, new Color(0xFFFF1212)));
					} else if(val == 0xFFFFBB33) {  //orange ghost
						ghosts.add(new Ghost(i*20, j*20, new Color(0xFFFFBB33)));
					} else if(val == 0xFF40ECFF) {  //cyan ghost
						ghosts.add(new Ghost(i*20, j*20, new Color(0xFF40ECFF)));
					} else if(val == 0xFFFF2ECE) {  //pink ghost
						ghosts.add(new Ghost(i*20, j*20, new Color(0xFFFF2ECE)));
					} else {  //path... place a cookie (dot) here
						cookies.add(new Cookie(i*20, j*20));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void tick() {
		for(Ghost ghost : ghosts) {
			ghost.tick();
		}
	}
	
	
	
	public void render(Graphics g) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(walls[i][j] != null) {
					walls[i][j].render(g);
				}
			}
		}
		
		for(Cookie cookie : cookies) {
			cookie.render(g);
		}
		
		for(Ghost ghost : ghosts) {
			ghost.render(g);
		}
	}
}
