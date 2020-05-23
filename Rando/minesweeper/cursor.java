package minesweeper;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

public class cursor {
	public static void main(String[] args) throws InterruptedException, AWTException {
		for(int i=0;i<5;i++) {
			PointerInfo a = MouseInfo.getPointerInfo();

			TimeUnit.SECONDS.sleep(1);
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		System.out.println(x+" "+y);
		}
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

	}
}
