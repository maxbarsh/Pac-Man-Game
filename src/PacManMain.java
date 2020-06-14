import javax.swing.JFrame;

public class PacManMain {
	public static void main(String[] args) {
		JFrame frame = new JFrame("PacMan");
		frame.setSize(500, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		HomeScreen home = new HomeScreen(frame);
		frame.setContentPane(home);
		frame.setVisible(true);
	}
	
}
