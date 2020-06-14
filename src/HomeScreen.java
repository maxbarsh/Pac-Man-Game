import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomeScreen extends JPanel {


	private JTextField nameField;
	private JLabel instructions;

	public HomeScreen(JFrame homeFrame){
		this.setBackground(new Color(19, 27, 135));
		this.setLayout(null);

		JLabel title = new JLabel("Pac-Man");
		title.setFont(new Font("Sans-Serif", Font.BOLD, 60));
		title.setForeground(Color.WHITE);
		title.setBounds(115, 120, 300, 100);
		this.add(title);

		JButton classic = new JButton("Classic Board");
		classic.setBackground(new Color(4, 29, 191));
		classic.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		classic.setForeground(Color.WHITE);
		classic.setOpaque(true);
		classic.setBorderPainted(false);
		classic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random random = new Random();

				if(nameField.getText().length() < 3) {
					int r = random.nextInt(254);
					int g = random.nextInt(254);
					int b = random.nextInt(254);
					instructions.setForeground(new Color(r, g, b));
				} else {
					JFrame gameFrame = new JFrame("PacMan Clone");
					gameFrame.setSize(500, 700);
					gameFrame.setLocationRelativeTo(null);
					gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					gameFrame.setResizable(false);

					Game game = new Game(gameFrame, "maps/ClassicMap.png", nameField.getText());
					gameFrame.add(game);
					gameFrame.pack();
					gameFrame.setVisible(true);

					game.start();
					homeFrame.dispose();
				}
			}
		});
		classic.setBounds(50, 500, 180, 50);
		this.add(classic);

		JButton levelSelect = new JButton("Level Select");
		levelSelect.setBackground(new Color(4, 29, 191));
		levelSelect.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		levelSelect.setForeground(Color.WHITE);
		levelSelect.setOpaque(true);
		levelSelect.setBorderPainted(false);
		levelSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random random = new Random();

				if(nameField.getText().length() < 3) {
					int r = random.nextInt(254);
					int g = random.nextInt(254);
					int b = random.nextInt(254);
					instructions.setForeground(new Color(r, g, b));
				} else {
					MapSelectScreen selectScreen = new MapSelectScreen(homeFrame, nameField.getText());
					homeFrame.setContentPane(selectScreen);
					homeFrame.setVisible(true);
				}
			}
		});
		levelSelect.setBounds(280, 500, 180, 50);
		this.add(levelSelect);

		instructions = new JLabel("Enter a three character ID to save your highscore!");
		instructions.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
		instructions.setForeground(Color.WHITE);
		instructions.setBounds(40, 270, 500, 100);
		this.add(instructions);



		nameField = new JTextField();
		nameField.setText("AAA");
		nameField.setForeground(Color.GRAY);
		nameField.setFont(new Font("Sans-Serif", Font.PLAIN, 25));
		nameField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				nameField.setForeground(Color.BLACK);
				nameField.setText("");
			}

			public void focusLost(FocusEvent e) {}
		});
		nameField.setBounds(220, 350, 65, 35);
		this.add(nameField);




	}
}
