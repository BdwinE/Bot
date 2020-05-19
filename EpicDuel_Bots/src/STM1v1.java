import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;

//strenth tactical mercenary build
public class STM1v1 {
	static Robot bot;
	static boolean isOver = false;
	static int win = 0;
	static int lost = 0;
	static int round;
	public static void main(String args[]) throws AWTException {
		bot = new Robot();
		System.out.println(bot.getPixelColor(968, 567));
		for(int i=0; i<110; i++) {
			round = 0;
			isOver = false;
			//searchMatch();
			startNPC();
			//System.out.println(bot.getPixelColor(893, 387).equals(new Color(254,227,101)));
			//System.out.println(bot.getPixelColor(968, 567));
			while(isOver==false) {
				checkTurn();//check if it's my turn first
				if(isOver())
					break;
				click(1008, 594);//crippling strike
				strike();
				checkTurn();
				if(isOver())
					break;
				if(round==1) {
					click(879, 529);//corrsive shot
					strike();
				}
				checkTurn();
				if(isOver())
					break;
				click(808, 597);//frenzy
				strike();
			}
			bot.delay(1000);
			click(968, 567);//click continue if received item
			if(win()) 
				win += 1;
			else
				lost += 1;
			System.out.println("Win: " + win + " Lost: " + lost);
			click(1288, 168);//exit battle
		}
	}
	
	public static void checkTurn() {
		while(checkTurnH()==false) {
			bot.delay(1000);
			if(isOver())
				break;
		}
	}
	private static boolean checkTurnH() {//returns true if my turn false if not
		if(bot.getPixelColor(966, 462).equals(new Color(130,55,55))) 
			return true;
		return false;
	}
	public static void strike() {
		bot.delay(1000);
		click(969, 462);
		bot.delay(500);;
		bot.mouseMove(1735, 303);//move mouse out of way
		round++;
	}
	public static void searchMatch() {
		click(955, 722);
	}
	public static boolean isOver() {//check if duel is over
		bot.delay(700);
		if(bot.getPixelColor(1285, 171).equals(new Color(255,255,255)) || bot.getPixelColor(968, 567).equals(new Color(41,41,41))) {
			isOver = true;
			return true;
		}
		return false;
	}
	public static void click(int x, int y) {
		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.delay(700);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		bot.delay(500);
		isOver();
	}
	
	public static boolean win() {
		if(bot.getPixelColor(893, 387).equals(new Color(254,227,101)))
			return true;
		return false;
	}
	public static void startNPC() {//theon of odsoius
		click(824, 447);
		click(746, 278);
	}
}
