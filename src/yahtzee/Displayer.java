package yahtzee;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Displayer extends Thread{
	
	private Label win;
	private JTextField[] counts;
	private JTextField[] setter;
	private JTextField rollCount;
	private Evaluate evaluate;
	private DiceRoll diceRoll;
	
	public Displayer(Label win, JTextField[] counts, JTextField[] setter, JTextField rollCount, Evaluate evaluate, DiceRoll diceRoll) {
		this.win = win;
		this.counts = counts;
		this.setter = setter;
		this.rollCount = rollCount;
		this.evaluate = evaluate;
		this.diceRoll = diceRoll;
	}
	
	public void run() {
		while(true) {
			// put into the wait line
			evaluate.waitForTurn();
			rollMade();
		}
	}
	
	public void rollMade() {
		//add to roll count
		rollCount.setText((Integer.parseInt(rollCount.getText())+1)+"");
		int[] numberSet = setNumbers();
		documentRoll(numberSet);
	}
	
	public void reset() { // set counters to nil
		win.setVisible(false);
		rollCount.setText(Integer.toString(0));
		
		for(JTextField i : setter) {
			i.setText("");
		}
		for(JTextField i : counts) {
			i.setText(Integer.toString(0));
		}
	}
	
	
	private int[] setNumbers() {
		int[] numbers = {0,0,0,0,0,0};
		 for(int i = 0; i < setter.length; i++) {
			 switch(Integer.parseInt(setter[i].getText())){
				 case 1: numbers[0]++;
				 		 break;
				 case 2: numbers[1]++;
		 		 	 	 break;
				 case 3: numbers[2]++;
		 		 		 break;
				 case 4: numbers[3]++;
		 		 		 break;
				 case 5: numbers[4]++;
		 		 		 break;
				 case 6: numbers[5]++;
		 		 		 break;
			 }
		 }
		return numbers;
	}

	
	private void documentRoll(int[] numberSet) { // calculate what hand was rolled and change counts
		 ArrayList<Integer> rolled = new ArrayList<>();
		 for(int num: numberSet) {
			 if(num > 0) {
				 rolled.add(num);
			 }
		 }
		 
		 int max = Collections.max(rolled);
		 
		 switch(max) {
		 case 5 : //if Yahtzee is rolled
			 	 win.setVisible(true);
		 		 counts[0].setText((Integer.parseInt(counts[0].getText())+1)+"");
		 		 diceRoll.hold();
		 		 break;
		 
		 case 4 : // if 4-of-a-kind is rolled
			     counts[3].setText((Integer.parseInt(counts[3].getText())+1)+"");
		 	   	 break;
		 	   	 
		 case 3 : // if FullHouse or 3-of-a-kind
			 if(Collections.min(rolled)==2) {
				 counts[4].setText((Integer.parseInt(counts[4].getText())+1)+"");
			 }else {
				 counts[5].setText((Integer.parseInt(counts[5].getText())+1)+"");
			 }break;
			 
		 case 2 : // if 2 pair or 1 pair
			 if(rolled.size()==3) {
				 counts[6].setText((Integer.parseInt(counts[6].getText())+1)+"");
			 }else {
				 counts[7].setText((Integer.parseInt(counts[7].getText())+1)+"");
			 }break;
			 
		 case 1: // if High or Low Straight or dead hand
			 int[] newRoll = {Integer.parseInt(setter[0].getText()),
					 		  Integer.parseInt(setter[1].getText()),
					 		  Integer.parseInt(setter[2].getText()),
					 		  Integer.parseInt(setter[3].getText()),
					 		  Integer.parseInt(setter[4].getText()),};
			 Arrays.sort(newRoll);
			 
			 if(newRoll[0] == 2) {
				 counts[1].setText((Integer.parseInt(counts[1].getText())+1)+"");
			 }else if(newRoll[4] == 5) {
				 counts[2].setText((Integer.parseInt(counts[2].getText())+1)+"");
			 }else {
				 counts[8].setText((Integer.parseInt(counts[8].getText())+1)+"");
			 }break;
		 }
		
	}

}
