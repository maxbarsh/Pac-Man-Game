import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private final int HEIGHT, WIDTH;
	private final int INTERVAL = 20;
	
	private boolean _isRunning = false;
	
	private Thread _thread;
	
	public String _map;
	public String playerID;
	
	public static PacMan player;
	public static Level level;
	
	public ArrayList<Cookie> eaten = new ArrayList<>();
	
	public  long score;
	public static int lives;
	
	public JFrame _frame;
	
	public Game(JFrame frame, String map, String name) {
		_frame = frame;
		playerID = name.substring(0, 3).toUpperCase();
		WIDTH = _frame.getWidth();
		HEIGHT = _frame.getHeight();
		Dimension d = new Dimension(this.WIDTH, this.HEIGHT);
		this.setSize(d);
		
		_map = map;
		
		player = new PacMan(this.WIDTH / 2, this.HEIGHT / 2);
		level = new Level(_map, 0);
		
		score = 0;
		lives = 2;
		
		this.addKeyListener(this);
	}
	
	public synchronized void start() {
		if(_isRunning) {
			return;
		}
		
		_isRunning = true;
		_thread = new Thread(this);
		_thread.start();
	}
	
	public synchronized void stop() {
		if(!_isRunning) {
			return;
		}
		
		_isRunning = false;
		try {
			_thread.join();
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		requestFocus();
		
		int fps = 0;
		double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetFPS = 60.0;
		double delta = 0.0;
		double nanoSeconds = 1000000000 / targetFPS;
		
		
		
		while(_isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSeconds;
			lastTime = now;
			
			//set the limit to 60 frames per second
			while(delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
//				System.out.println(fps);
				fps = 0;
				timer += 1000;
			}	
		}
		stop();	
	}
	
	
	public void tick() {
		player.tick();
		level.tick();
		
		//update the score for each cookie eaten
		for(Cookie c : Level.cookies) {
			if(player.intersects(c)) {
				eaten.add(c);
				Level.cookies.remove(c);
				score += 10;
				break;
				
			}
		}
		
		//if all the cookies have been eaten on the screen... win round, go to next round
		if(Level.cookies.size() == 0) {  
			player = new PacMan(0,0);
			level = new Level(_map, Level.roundNum + 1);
			eaten.clear();
			return;
			
		}
		
		//lose a life if a ghost catches pac man
		for(Ghost g : Level.ghosts) {  
			if(player.intersects(g)) {
				level = new Level(_map, Level.roundNum);
				
				for(Cookie c : eaten) {
					Level.cookies.remove(c);
				}
				
				if(lives > 0) {
					lives--;
				} else {					
					JFrame gameOverFrame = new JFrame("Game Over");
					gameOverFrame.setSize(500, 700);
					gameOverFrame.setLocationRelativeTo(null);
					gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					gameOverFrame.setResizable(false);
					
					GameOver gameOver = new GameOver(_map, gameOverFrame, this);
					gameOverFrame.setContentPane(gameOver);
					gameOverFrame.setVisible(true);
					
					_frame.dispose();
					stop();
				}
			}
		}
		
	}

	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.WIDTH, this.HEIGHT - (5*this.INTERVAL));
		
		g.setColor(new Color(19, 27, 135));
		g.fillRect(0, this.HEIGHT - (5*this.INTERVAL), this.WIDTH, 5*this.INTERVAL);
		
		g.setColor(Color.WHITE);
		
		g.drawString("Level: "+(Level.roundNum + 1), 0, this.HEIGHT - (4*this.INTERVAL));
		g.drawString("Lives: " + lives, 0, this.HEIGHT - (3*this.INTERVAL));
		g.drawString("Score: " + score, 0, this.HEIGHT - (2*this.INTERVAL));		
		
		player.render(g);
		level.render(g);
		
		g.dispose();
		bs.show();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
	}
}
