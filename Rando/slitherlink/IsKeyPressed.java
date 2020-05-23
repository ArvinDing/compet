package slitherlink;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class IsKeyPressed {
	private static volatile boolean qPressed = false;

	public static boolean isQPressed() {
		synchronized (IsKeyPressed.class) {
			return qPressed;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent ke) {
				synchronized (IsKeyPressed.class) {
					switch (ke.getID()) {
					case KeyEvent.KEY_PRESSED:
						if (ke.getKeyCode() == KeyEvent.VK_Q) {
							qPressed = true;
						}
						break;

					case KeyEvent.KEY_RELEASED:
						if (ke.getKeyCode() == KeyEvent.VK_Q) {
							qPressed = false;
						}
						break;
					}
					return false;
				}
			}

		});
		for (int i = 0; i < 10; i++) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(qPressed);
		}
	}
}