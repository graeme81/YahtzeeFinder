package yahtzee;

import java.awt.*;
import javax.swing.*;

public class DiceRoll extends Thread{
	
	private JTextField[] roll;
	private Evaluate evaluate;
	private boolean starter = false;
	private Label win;
	
	public DiceRoll(JTextField[] roll, Evaluate evaluate, Label win) {
		this.roll = roll;
		this.evaluate = evaluate;
		this.win = win;
	}
	
	public void run() {
		int stay = 0;
		while(true) {
			stay++;
			System.out.println("Stay = " + stay);   
			while(starter) {
				
				win.setVisible(false);
				attempt();	
				evaluate.changeTurn();
				try {
					sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void go() {
		starter = true;
		
	}
	public void hold() {
		starter = false;
	}
	
	public void attempt() {
		for(JTextField die : roll) {
			int x = (int) ((Math.random()*6)+1);
			die.setText(Integer.toString(x));
		}
	}
}