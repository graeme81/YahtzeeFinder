package yahtzee;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board implements ActionListener{
	
	private static Displayer displayer;
	private static Evaluate evaluate;
	private static DiceRoll diceRoll;
	private Button roll, auto, reset;
	private static Label win;
	private static JTextField[] counts = new JTextField[9];
	private static JTextField[] setter = new JTextField[5];
	private static JTextField rollCount = new JTextField();
	
	public static void main(String[] args) {
		
//		Board board = new Board();
		new Board();
		evaluate = new Evaluate();
		diceRoll = new DiceRoll(setter, evaluate, win);
		displayer = new Displayer(win, counts, setter, rollCount, evaluate , diceRoll);
		displayer.start();
		diceRoll.start();

	}	
		public Board() {
		
		JFrame f = new JFrame();
		f.setVisible(true);
		f.setSize(400 , 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		f.setTitle("Yahtzee - Perfect Roll");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);		
		f.add(panel);
		
		Label title = new Label("YAHTZEE FINDER");
		title.setBounds(70, 30, 260, 50);
		title.setBackground(Color.GREEN);
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("Serif", Font.ITALIC, 24));
		panel.add(title);
				
		for (int i = 0; i < 5; i++) {
			setter[i] = new JTextField();
			
			setter[i].setFont(new Font("Serif", Font.BOLD, 40));
			setter[i].setHorizontalAlignment(JTextField.CENTER);
			setter[i].setBounds(50*i + 80, 100, 40, 40);
			panel.add(setter[i]);
		}
		
		win = new Label("YAHTZEE");
		win.setBounds(100, 150, 200, 40);
		win.setBackground(Color.ORANGE);
		win.setAlignment(Label.CENTER);
		win.setFont(new Font("Serif", Font.BOLD, 36));
		win.setVisible(false);
		panel.add(win);
		
		roll = new Button("ROLL");
		roll.setBounds(80, 210, 80, 30);
		panel.add(roll);
		roll.addActionListener(this);
		
		auto = new Button("AUTO");
		auto.setBounds(80, 250, 80, 30);
		panel.add(auto);
		auto.addActionListener(this);
		
		reset = new Button("RESET");
		reset.setBounds(80, 290, 80, 30);
		panel.add(reset);
		reset.addActionListener(this);
		
		Label[] hands = new Label[9];
		
		String[] names = {"Yahtzee", 
						  "High Staright", 
						  "Low Straight", 
						  "4-Of-A-Kind", 
						  "Full House", 
						  "3-Of-A-Kind",
						  "2 Pairs",
						  "Pair",
						  "Dead"};
		
		 for (int i = 0; i < hands.length; i++) {
			 hands[i] = new Label(names[i]);
			 hands[i].setBounds(200, 210 + (20*i), 80, 20);
			 panel.add(hands[i]);
			 
			 counts[i] = new JTextField();
			 counts[i].setName("lbl"+names[i]);
			 counts[i].setBounds(290, 210 + (20*i), 45, 18);
			 panel.add(counts[i]);
			 counts[i].setHorizontalAlignment(JTextField.CENTER);
			 counts[i].setText(Integer.toString(0));
			 
		 }
		 
		 Label rolls = new Label("ROLLS:");
		 rolls.setBounds(130, 405, 70, 30);
		 rolls.setFont(new Font("Serif", Font.PLAIN, 20));
		 panel.add(rolls);
		 
		 rollCount.setBounds(200, 405, 90, 30);
		 panel.add(rollCount);
		 rollCount.setFont(new Font("Serif", Font.PLAIN, 22));
		 rollCount.setHorizontalAlignment(JTextField.CENTER);
		 rollCount.setText(Integer.toString(0));
		 
	}
	
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == roll)  {
			win.setVisible(false);
			diceRoll.attempt();
			displayer.rollMade();
		}
		
		if(event.getSource() == auto)  {
			diceRoll.go();
		}
		
		if(event.getSource() == reset) { 
			displayer.reset();
		}
	}
}
