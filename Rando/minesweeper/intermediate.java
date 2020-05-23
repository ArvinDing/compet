package minesweeper;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class intermediate {
	
	public static void main(String[] args) throws Exception {
		  
		Robot robot = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		robot.mouseMove(0, 0);
		BufferedImage[] save = new BufferedImage[10];
		save[0] = ImageIO.read(new File("blank.png"));
		save[1] = ImageIO.read(new File("1.png"));
		save[2] = ImageIO.read(new File("2.png"));
		save[3] = ImageIO.read(new File("3.png"));
		save[4] = ImageIO.read(new File("4.png"));
		save[5] = ImageIO.read(new File("5.png"));
		save[6] = ImageIO.read(new File("6.png"));
		save[7] = ImageIO.read(new File("7.png"));
		save[8] = ImageIO.read(new File("8.png"));
		save[9] = ImageIO.read(new File("flag.png"));
		int flagged = 0;
		boolean change = true;
		while (change && flagged != 40) {
			change = false;
			BufferedImage game = robot
					.createScreenCapture(new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight()));
			int[][] gameBoard = new int[16][16];
			for (int i = 0; i < 16; i++) {
				for (int k = 0; k < 16; k++) {
					BufferedImage temp = game.getSubimage(551 + 16 * k, 147 + 16 * i, 16, 16);
					int z = 0;
					for (; z < 10; z++) {
						if (compareImages(temp, save[z])) {
							break;
						}
					}
					gameBoard[i][k] = z;
				}
			}
			for (int i = 0; i < 16; i++) {
				for (int k = 0; k < 16; k++) {
					int cnt = 0;
					if (1 <= gameBoard[i][k] && gameBoard[i][k] <= 8) {
						boolean onlyFlag = true;
						for (int iC = -1; iC <= 1; iC++) {
							for (int kC = -1; kC <= 1; kC++) {
								if ((0 <= i + iC && i + iC < 16) && (0 <= k + kC && k + kC < 16)) {
									if (gameBoard[i + iC][k + kC] == 0 || gameBoard[i + iC][k + kC] == 9) {
										if (gameBoard[i + iC][k + kC] == 0)
											onlyFlag = false;
										cnt++;
									}
								}
							}
						}
						if (!onlyFlag && gameBoard[i][k] == cnt) {
							for (int iC = -1; iC <= 1; iC++) {
								for (int kC = -1; kC <= 1; kC++) {
									if ((0 <= i + iC && i + iC < 16) && (0 <= k + kC && k + kC < 16)) {
										if (gameBoard[i + iC][k + kC] == 0) {
											gameBoard[i + iC][k + kC] = 9;
											robot.mouseMove(551 + 16 * (k + kC) + 8, 147 + 16 * (i + iC) + 8);
											robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
											TimeUnit.MILLISECONDS.sleep(10);
											robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
											change = true;
											flagged++;
										}
									}
								}
							}
						}
					}
				}
			}
			for (int i = 0; i < 16; i++) {
				for (int k = 0; k < 16; k++) {
					int cnt = 0;
					if (1 <= gameBoard[i][k] && gameBoard[i][k] <= 8) {
						boolean reveal = false;
						for (int iC = -1; iC <= 1; iC++) {
							for (int kC = -1; kC <= 1; kC++) {
								if ((0 <= i + iC && i + iC < 16) && (0 <= k + kC && k + kC < 16)) {
									if (gameBoard[i + iC][k + kC] == 9) {
										cnt++;
									} else if (gameBoard[i + iC][k + kC] == 0) {
										reveal = true;
									}
								}
							}
						}
						if (reveal && cnt == gameBoard[i][k]) {
							for (int iC = -1; iC <= 1; iC++) {
								for (int kC = -1; kC <= 1; kC++) {
									if ((0 <= i + iC && i + iC < 16) && (0 <= k + kC && k + kC < 16)) {
										if (gameBoard[i + iC][k + kC] == 0) {
											robot.mouseMove(551 + 16 * (k + kC) + 8, 147 + 16 * (i + iC) + 8);
											robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
											robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
											change = true;
										}
									}
								}
							}
						}
					}
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
