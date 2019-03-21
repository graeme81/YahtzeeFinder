package yahtzee;

public class Evaluate {

	private boolean turn = false;
	
	public synchronized void waitForTurn() {
		
		while(!turn) {
			try{
				wait();
			}catch(InterruptedException e) {
				System.err.println("Exception in Evaluate");
			}	
		}
		turn = false;
	}
	
	public synchronized void changeTurn() {
		
		turn = true;
		notify();
	}

}