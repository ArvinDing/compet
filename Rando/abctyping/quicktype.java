package abctyping;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class quicktype {
	public static void main(String[] args) throws Exception {
		Robot r = new Robot();
		char[] save = "zyxwvutsrqponmlkjihgfedcba".toUpperCase().toCharArray();
		TimeUnit.SECONDS.sleep(1);
		for (int i = 0; i < 26; i++) {
			String code = "VK_" + save[i];
			Field f = KeyEvent.class.getField(code);
			int keyEvent = f.getInt(null);
			TimeUnit.MILLISECONDS.sleep(100);

			r.keyPress(keyEvent);
			r.keyRelease(keyEvent);
		}
	}
}
