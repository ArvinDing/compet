package minecraft;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class craft {
	static Robot robot;
	private static int enchanted = 70;
	private static int goal = 240;

	public static void main(String[] args) throws AWTException, InterruptedException {

		robot = new Robot();

		// focus into the game
		robot.mouseMove(50, 50);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		// character looking at table
		while (enchanted <= goal) {
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			TimeUnit.MILLISECONDS.sleep(200);
			robot.mouseMove(960, 60);
			TimeUnit.MILLISECONDS.sleep(200);
			buy();

			TimeUnit.MILLISECONDS.sleep(200);
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);

			
			TimeUnit.MILLISECONDS.sleep(200);
			robot.mouseMove(960, 1080);
			TimeUnit.MILLISECONDS.sleep(200);

			robot.keyPress(KeyEvent.VK_9);
			robot.keyRelease(KeyEvent.VK_9);
			TimeUnit.MILLISECONDS.sleep(200);
			left();
			robot.mouseMove(950, 468);
			TimeUnit.MILLISECONDS.sleep(200);
			left();
			craftE();
		}
	}

	public static void left() throws InterruptedException {
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		TimeUnit.MILLISECONDS.sleep(200);
	}

	public static void buy() throws InterruptedException {
		int[] potatoIcon = new int[] { 930, 395 };
		int[] stack64 = new int[] { 1025, 425 };
		// openshop

		left();
		robot.mouseMove(potatoIcon[0], potatoIcon[1]);
		TimeUnit.MILLISECONDS.sleep(200);
		left();
		robot.mouseMove(stack64[0], stack64[1]);
		TimeUnit.MILLISECONDS.sleep(200);

		for (int i = 0; i < 31; i++) {
			TimeUnit.MILLISECONDS.sleep(400);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}

	}

	public static void craftE() throws InterruptedException {
		int cnt = 0;
		int[] craftOut = new int[] { 1030, 430 };
		robot.keyPress(KeyEvent.VK_SHIFT);
		for (int i = 1; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == 8 && j == 3)
					continue;
//				if (enchanted != 0 && (enchanted / 64) >= i * 4 + j) {
//					continue;
//				}

				// TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(i + " " + j);

				robot.mouseMove(810 + 36 * i, 610 + 36 * j);
				TimeUnit.MILLISECONDS.sleep(100);

				// TimeUnit.MILLISECONDS.sleep(1000);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				cnt++;
				TimeUnit.MILLISECONDS.sleep(100);
				if (cnt == 5) {
					cnt = 0;
					TimeUnit.MILLISECONDS.sleep(100);
					robot.mouseMove(craftOut[0], craftOut[1]);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					enchanted += 2;
				}
			}

		}
		upS();
	}

	public static void downS() throws InterruptedException {
		robot.keyPress(KeyEvent.VK_SHIFT);
	}

	public static void upS() throws InterruptedException {

		robot.keyRelease(KeyEvent.VK_SHIFT);
	}

}
