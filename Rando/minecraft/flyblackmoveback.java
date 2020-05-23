package minecraft;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class flyblackmoveback {
	static Robot robot;

	public static void main(String[] args) throws InterruptedException, AWTException {
		int blocks = 200;
		int rows = 2;
		PointerInfo a = MouseInfo.getPointerInfo();
		robot = new Robot();
		robot.mouseMove(960, 531);
		for (int z = 0; z < rows; z++) {
			robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			for (int i = 0; i < blocks / 5; i++) {
				if(i%13==0) {
					robot.mouseWheel(-100);
				}
				press(KeyEvent.VK_S, 900);
				press(KeyEvent.VK_W, 900);

		}
			robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			robot.mouseWheel(-100);
			press(KeyEvent.VK_A, 100);
			press(KeyEvent.VK_W, 20000);


		}

	}

	public static void press(int event, int time) throws InterruptedException {
		robot.keyPress(event);
		TimeUnit.MILLISECONDS.sleep(time);
		robot.keyRelease(event);

	}
}
