package slitherlink;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class fiveby5 {
	static int[][] hWalls;
	static int[][] vWalls;
	static int[][] info;
	static boolean change;
	static Robot robot;
	static boolean[][] visitedP;
	static int[][] cnt;
	static int total = 0;
	static int done = 0;
	// static int[][] hasToGo; optimzation

	public static void main(String[] args) throws Exception {
		// hasToGo- 1- up, 2- right, 3-down, 4 -left
		robot = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		BufferedImage game = robot.createScreenCapture(new Rectangle(791, 376, 427, 427));
		File outputfile = new File("image.jpg");
		ImageIO.write(game, "jpg", outputfile);
		BufferedImage[] save = new BufferedImage[5];

		save[0] = ImageIO.read(new File("0S.png"));
		save[1] = ImageIO.read(new File("1S.png"));
		save[2] = ImageIO.read(new File("2S.png"));
		save[3] = ImageIO.read(new File("3S.png"));
		save[4] = ImageIO.read(new File("-1S.png"));
		// -1 is no
		// 1 is yes
		// 0 is idk
		hWalls = new int[6][5];
		// up to down, left to right
		vWalls = new int[5][6];
		// up to down, left to right
		info = new int[5][5];
		// hasToGo = new int[6][6];
		game = robot.createScreenCapture(new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight()));
		// game = ImageIO.read(new File("screenshot.png"));
		
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {

				BufferedImage temp = game.getSubimage(791 + k * 72, 377 + i * 72, 73, 73);
				int z = 0;
				for (; z < 5; z++) {
					if (compareImages(temp, save[z])) {
						break;
					}
				}
				info[i][k] = z;
				if (info[i][k] == 0) {
					hWalls[i][k] = -1;
					hWalls[i][k] = -1;
					vWalls[i][k] = -1;
					vWalls[i][k] = -1;

				}
				if (0 < info[i][k] && info[i][k] <= 3)
					total++;

			}
		}

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (info[i][k] == 0) {
					hWalls[i][k] = -1;
					hWalls[i + 1][k] = -1;
					vWalls[i][k] = -1;
					vWalls[i][k + 1] = -1;
				}
			}
		}
		int saveI = -1;
		int saveK = -1;
		outer: for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (info[i][k] == 1 || info[i][k] == 3) {
					saveI = i;
					saveK = k;
					break outer;
				}
			}
		}
		// vWall or hWall
		// position in array
		// value to set
		int[][] sides = new int[4][3];
		int[][] points = new int[4][2];
		sides[0] = new int[] { 0, saveI, saveK };// topH
		sides[1] = new int[] { 1, saveI, saveK + 1 };// rightV
		sides[2] = new int[] { 0, saveI + 1, saveK };// botH
		sides[3] = new int[] { 1, saveI, saveK };// leftV
		points[0] = new int[] { saveI, saveK };
		points[1] = new int[] { saveI, saveK + 1 };
		points[2] = new int[] { saveI + 1, saveK + 1 };
		points[3] = new int[] { saveI + 1, saveK };
		visitedP = new boolean[6][6];
		cnt = new int[5][5];
		if (info[saveI][saveK] == 3) {

			for (int notInclude = 0; notInclude < 4; notInclude++) {
				boolean broke = false;
				for (int i = 0; i < notInclude; i++) {
					if (!set(sides[i]))
						broke = true;
				}
				for (int i = notInclude + 1; i < 4; i++) {
					if (!set(sides[i]))
						broke = true;
				}
				if (!broke) {
					int[] end = points[(notInclude + 1) % 4];
					visitedP[end[0]][end[1]] = false;
					if (solve(points[notInclude], end)) {
						System.out.println("yay");
						break;
					}
				}
				for (int i = 0; i < notInclude; i++) {
					unSet(sides[i]);
				}
				for (int i = notInclude + 1; i < 4; i++) {
					unSet(sides[i]);
				}

			}
		} else {
			for (int include = 0; include < 4; include++) {
				boolean broke = false;
				if (!set(sides[include]))
					broke = true;
				cnt[saveI][saveK] = 1;
				int[] end = points[(include + 1) % 4];
				visitedP[end[0]][end[1]] = false;
				if (!broke)
					if (solve(points[include], points[(include + 1) % 4])) {
						break;
					}
				unSet(sides[include]);

			}
		}

		click();
		// System.out.println("yay");
	}

	static boolean set(int[] input) {
		int i = input[1];
		int k = input[2];
		if (input[0] == 0) {
			hWalls[i][k] = 1;
			if (i != 5) {
				cnt[i][k]++;
				if (cnt[i][k] == info[i][k])
					done++;
			}
			if (i != 0) {
				cnt[i - 1][k]++;
				if (cnt[i - 1][k] == info[i - 1][k])
					done++;
			}
			visitedP[i][k] = true;
			visitedP[i][k + 1] = true;
			if ((i != 5 && cnt[i][k] > info[i][k]) || (i != 0 && cnt[i - 1][k] > info[i - 1][k])) {
				return false;
			}
		} else {
			vWalls[i][k] = 1;
			if (k != 5) {
				cnt[i][k]++;
				if (cnt[i][k] == info[i][k])
					done++;
			}
			if (k != 0) {
				cnt[i][k - 1]++;
				if (cnt[i][k - 1] == info[i][k - 1])
					done++;
			}
			visitedP[i][k] = true;
			visitedP[i + 1][k] = true;
			if ((k != 5 && cnt[i][k] > info[i][k]) || (k != 0 && cnt[i][k - 1] > info[i][k - 1])) {
				return false;
			}
		}
		return true;
	}

	static void unSet(int[] input) {
		int i = input[1];
		int k = input[2];
		if (input[0] == 0) {
			hWalls[i][k] = 0;

			if (i != 0) {
				cnt[i - 1][k]--;
				if (cnt[i - 1][k] == info[i - 1][k] - 1)
					done--;
			}
			if (i != 5) {
				cnt[i][k]--;
				if (cnt[i][k] == info[i][k] - 1)
					done--;
			}
			visitedP[i][k] = false;
			visitedP[i][k + 1] = false;
		} else {
			vWalls[i][k] = 0;
			if (k != 5) {
				cnt[i][k]--;
				if (cnt[i][k] == info[i][k] - 1)
					done--;
			}
			if (k != 0) {
				cnt[i][k - 1]--;
				if (cnt[i][k - 1] == info[i][k - 1] - 1)
					done--;
			}
			visitedP[i][k] = false;
			visitedP[i + 1][k] = false;
		}
	}

	static boolean checkL(int i, int k) {

		if (i == 5 || cnt[i][k - 1] + 1 <= info[i][k - 1]) {
			if (i == 0 || cnt[i - 1][k - 1] + 1 <= info[i - 1][k - 1]) {
				if (!visitedP[i][k - 1])
					return true;
			}
		}
		return false;
	}

	static boolean checkU(int i, int k) {
		if (k == 5 || cnt[i - 1][k] + 1 <= info[i - 1][k]) {
			if (k == 0 || cnt[i - 1][k - 1] + 1 <= info[i - 1][k - 1]) {
				if (!visitedP[i - 1][k])
					return true;
			}
		}
		return false;

	}

	static boolean checkD(int i, int k) {
		if (k == 5 || cnt[i][k] + 1 <= info[i][k]) {
			if (k == 0 || cnt[i][k - 1] + 1 <= info[i][k - 1])
				if (!visitedP[i + 1][k])
					return true;
		}
		return false;
	}

	static boolean checkR(int i, int k) {
		if (i == 5 || cnt[i][k] + 1 <= info[i][k]) {
			if (i == 0 || cnt[i - 1][k] + 1 <= info[i - 1][k])
				if (!visitedP[i][k + 1])
					return true;
		}
		return false;
	}

	static boolean solve(int[] curr, int[] end) {
		int i = curr[0];
		int k = curr[1];
		if (i == end[0] && k == end[1])
			return done == total;
		if (k != 0 && checkL(i, k)) {
			set(new int[] { 0, i, k - 1 });
			curr[1]--;
			if (solve(curr, end))
				return true;
			visitedP[curr[0]][curr[1]] = false;
			curr[1]++;
			unSet(new int[] { 0, i, k - 1 });
		}
		if (k != 5 && checkR(i, k)) {
			set(new int[] { 0, i, k });
			curr[1]++;
			if (solve(curr, end))
				return true;
			visitedP[curr[0]][curr[1]] = false;
			curr[1]--;
			unSet(new int[] { 0, i, k });
		}
		if (i != 0 && checkU(i, k)) {
			set(new int[] { 1, i - 1, k });
			curr[0]--;
			if (solve(curr, end))
				return true;
			visitedP[curr[0]][curr[1]] = false;
			curr[0]++;
			unSet(new int[] { 1, i - 1, k });
		}

		if (i != 5 && checkD(i, k)) {
			set(new int[] { 1, i, k });
			curr[0]++;
			if (solve(curr, end))
				return true;
			visitedP[curr[0]][curr[1]] = false;
			curr[0]--;
			unSet(new int[] { 1, i, k });
		}
		return false;
	}

	static void click() throws Exception {
		for (int i = 0; i < 6; i++) {
			for (int k = 0; k < 5; k++) {
				System.out.print(hWalls[i][k]);
			}
			System.out.println();
		}
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 6; k++) {
				if (vWalls[i][k] == 1) {
					robot.mouseMove(793 + k * 72, 408 + i * 72);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					TimeUnit.MILLISECONDS.sleep(10);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				} else if (vWalls[i][k] == -1) {
					robot.mouseMove(793 + k * 72, 408 + i * 72);
					robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
					TimeUnit.MILLISECONDS.sleep(10);
					robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int k = 0; k < 5; k++) {
				if (hWalls[i][k] == 1) {
					robot.mouseMove(822 + k * 72, 375 + i * 72);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					TimeUnit.MILLISECONDS.sleep(10);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				} else if (hWalls[i][k] == -1) {
					robot.mouseMove(822 + k * 72, 375 + i * 72);
					robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
					TimeUnit.MILLISECONDS.sleep(10);
					robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
				}
			}
		}
	}

	static void check() {
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				int temp = 0;
				temp += (hWalls[i][k] == -1) ? 0 : 1;
				temp += (hWalls[i + 1][k] == -1) ? 0 : 1;
				temp += (vWalls[i][k] == -1) ? 0 : 1;
				temp += (vWalls[i][k + 1] == -1) ? 0 : 1;
				if (hWalls[i][k] == 0 || hWalls[i + 1][k] == 0 || vWalls[i][k] == 0 || vWalls[i][k + 1] == 0)
					if (temp == info[i][k]) {
						change = true;
						if (hWalls[i][k] != -1)
							hWalls[i][k] = 1;
						if (hWalls[i + 1][k] != -1)
							hWalls[i + 1][k] = 1;
						if (vWalls[i][k] != -1)
							vWalls[i][k] = 1;
						if (vWalls[i][k + 1] != -1)
							vWalls[i][k + 1] = 1;
					}
			}
		}

	}

	public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) throws IOException {
		// The images must be the same size.
		int width = imgA.getWidth();
		int height = imgA.getHeight();
		// Loop over every pixel.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Compare the pixels for equality.
				Color a = new Color(imgA.getRGB(x, y));
				Color b = new Color(imgB.getRGB(x, y));
				if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
					return false;
				}
			}
		}

		return true;
	}
}
