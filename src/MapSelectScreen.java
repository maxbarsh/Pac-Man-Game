import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapSelectScreen extends JPanel {
	
	public MapSelectScreen(JFrame homeFrame, String name) {
		this.setBackground(new Color(19, 27, 135));
		this.setLayout(null);
		
		JLabel title = new JLabel("Select a Map");
		title.setFont(new Font("Sans-Serif", Font.BOLD, 45));
		title.setForeground(Color.WHITE);
		title.setBounds(115, 20, 400, 100);
		this.add(title);
		
		
		//------ Classic Map ---------
		
		ImageIcon classicMap = new ImageIcon(getClass().getResource("maps/ClassicMap.png"));
		Image img = classicMap.getImage();  //transform it
		Image newImg = img.getScaledInstance(113, 150,  java.awt.Image.SCALE_SMOOTH);
		classicMap = new ImageIcon(newImg);
		
		JButton classicMapButton = new JButton(classicMap);
		classicMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame("maps/ClassicMap.png", name);
				homeFrame.dispose();
			}
		});
		classicMapButton.setBounds(50, 150, 113, 150);
		this.add(classicMapButton);
		
		
		JButton classicButton = new JButton("Classic");
		classicButton.setBackground(new Color(4, 29, 191));
		classicButton.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		classicButton.setForeground(Color.WHITE);
		classicButton.setOpaque(true);
		classicButton.setBorderPainted(false);
		classicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame("maps/ClassicMap.png", name);
				homeFrame.dispose();
			}
		});
		classicButton.setBounds(50, 305, 113, 30);
		this.add(classicButton);
		
		//------- new maps here ------------
		
		ImageIcon hireMap = new ImageIcon(getClass().getResource("maps/HireMeMap.png"));
		Image imgHire = classicMap.getImage();  //transform it
		Image newImgHire = imgHire.getScaledInstance(113, 150,  java.awt.Image.SCALE_SMOOTH);
		hireMap = new ImageIcon(newImgHire);
		
		JButton hireMapButton = new JButton(hireMap);
		hireMapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame("maps/HireMeMap.png", name);
				homeFrame.dispose();
			}
		});
		hireMapButton.setBounds(180, 150, 113, 150);
		this.add(hireMapButton);
		
		
		JButton hireButton = new JButton("Hire Me");
		hireButton.setBackground(new Color(4, 29, 191));
		hireButton.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		hireButton.setForeground(Color.WHITE);
		hireButton.setOpaque(true);
		hireButton.setBorderPainted(false);
		hireButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame("maps/HireMeMap.png", name);
				homeFrame.dispose();
			}
		});
		hireButton.setBounds(180, 305, 113, 30);
		this.add(hireButton);
	}
	
	
	
	public void startGame(String map, String name) {
		JFrame gameFrame = new JFrame("PacMan Clone");
		gameFrame.setSize(500, 700);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		
		Game game = new Game(gameFrame, map, name);
		gameFrame.add(game);
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		game.start();
	}

}
