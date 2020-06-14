import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameOver extends JPanel {


	public GameOver(String map, JFrame frame, Game game) {
		this.setBackground(new Color(19, 27, 135));
		this.setLayout(null);

		long[] scoresArray = new long[9];
		String[] nameArray = new String[9];


		JLabel gameOver = new JLabel("Game Over");
		gameOver.setFont(new Font("Sans-Serif", Font.BOLD, 50));
		gameOver.setForeground(Color.WHITE);
		gameOver.setBounds(110, 60, 300, 100);
		this.add(gameOver);

		JLabel highscores = new JLabel("Highscores");
		highscores.setFont(new Font("Sans-Serif", Font.PLAIN, 35));
		highscores.setForeground(Color.WHITE);
		highscores.setBounds(158, 160, 190, 100);
		this.add(highscores);

		JTextArea scores = new JTextArea();
		scores.setEditable(false);
		scores.setLineWrap(true);
		scores.setBackground(new Color(19, 27, 135));
		scores.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
		scores.setForeground(Color.WHITE);

		JTextArea names = new JTextArea();
		names.setEditable(false);
		names.setLineWrap(true);
		names.setBackground(new Color(19, 27, 135));
		names.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
		names.setForeground(Color.WHITE);


		try {
			Scanner in = new Scanner(this.getClass().getClassLoader().getResourceAsStream("scores/HighScores.txt"));  //read from the Rules text file 
			int count = 0;
			while(in.hasNext()) {
				String name = in.next();
				String score = in.next();

				scoresArray[count] = Long.parseLong(score);
				nameArray[count] = name;
				count++;


			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		for(int i = 0; i < scoresArray.length - 1; i++) {
			if(game.score <= scoresArray[i] && game.score >= scoresArray[i+1]) {
				//insert score/ name and push lower scores/ names down
				for(int j = scoresArray.length - 1; j > i + 1; j--) {
					scoresArray[j] = scoresArray[j-1];
					nameArray[j] = nameArray[j-1];
				}
				scoresArray[i+1] = game.score;
				nameArray[i+1] = game.playerID;
				break;
			}
		}

		String nameDisplayString = "";
		String scoreDisplayString = "";
//		String writeString = "";

		//File file = new File("resources/scores/HighScores.txt");

//		try {
//			OutputStream os = new FileOutputStream(file);
//			PrintWriter out = new PrintWriter(os, true);

			for(int i = 0; i < scoresArray.length; i++) {
				nameDisplayString += "" + (i+1) + ". " + nameArray[i] + "\n";
				scoreDisplayString += "" + scoresArray[i] + "\n";
//				writeString += nameArray[i] + "    " + scoresArray[i] + "\n";
			}

//			out.print(writeString);
//			out.flush();
//			out.close();
//
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}


		names.setText(nameDisplayString);
		names.setBounds(100, 240, 200, 200);
		this.add(names);

		scores.setText(scoreDisplayString);
		scores.setBounds(360, 240, 200, 200);
		this.add(scores);


		JButton newGame = new JButton("Quit");
		newGame.setBackground(new Color(4, 29, 191));
		newGame.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		newGame.setForeground(Color.WHITE);
		newGame.setOpaque(true);
		newGame.setBorderPainted(false);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
//				frame.dispose();
//				
//				JFrame newFrame = new JFrame("PacMan");
//				newFrame.setSize(500, 700);
//				newFrame.setLocationRelativeTo(null);
//				newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				newFrame.setResizable(false);
//				
//				HomeScreen home = new HomeScreen(newFrame);
//				newFrame.setContentPane(home);
//				newFrame.setVisible(true);
			}
		});
		newGame.setBounds(280, 500, 180, 50);
		this.add(newGame);


	}
}
