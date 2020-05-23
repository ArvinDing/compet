
/*
ID: dingarv1
LANG: JAVA
TASK: ttwo
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class ttwo {

	static char fdirection = 'N';
	static char cdirection = 'N';
	static char[][] info;
	static int fX;
	static int fY;
	static int cX;
	static int cY;

	public static void main(String[] args) throws java.io.IOException {

		BufferedReader in = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new File("ttwo.out"));
		info = new char[12][12];
		for (int i = 0; i < 12; i++) {

			for (int o = 0; o < 12; o++) {
				if (i == 0 || i == 11 || o == 11 || o == 0) {
					info[i][o] = '*';
				}
			}
		}

		for (int i = 1; i < 11; i++) {
			String a = in.readLine();
			for (int k = 1; k < 11; k++) {
				if (a.charAt(k - 1) == 'C') {
					cX = i;
					cY = k;
				}
				if (a.charAt(k - 1) == 'F') {
					fX = i;
					fY = k;
				}
				info[i][k] = a.charAt(k - 1);
			}
		}
		int minute = 0;

		for (int i = 0; i < 160000; i++) {
			// farmer
			if (isBoundary(fX, fY, fdirection)) {
				fdirection = rotate90(fdirection);
			} else {
				moveF('f');
			}

			if (isBoundary(cX, cY, cdirection)) {
				cdirection = rotate90(cdirection);
			} else {
				moveF('c');
			}
			minute++;
			if (fX == cX && fY == cY) {
				break;
			}

		}
		if (minute == 160000) {
			out.println(0);
		} else {
			out.println(minute);
		}
		in.close();
		out.close();
	}

	private static boolean isBoundary(int x, int y, char direction) {
		if (direction == 'N') {
			return info[x-1][y ] == '*';
		} else if (direction == 'S') {
			return info[x+1][y ] == '*';
		} else if (direction == 'W') {
			return info[x ][y-1] == '*';
		} else {
			return info[x ][y+1] == '*';
		}
	}

	private static void moveF(char a) {
		if (a == 'f') {

			if (fdirection == 'N') {
				fX += -1;
			} else if (fdirection == 'S') {
				fX += 1;
			} else if (fdirection == 'W') {
				fY += -1;
			} else {
				fY += 1;
			}
		} else {

			if (cdirection == 'N') {
				cX += -1;
			} else if (cdirection == 'S') {
				cX += 1;
			} else if (cdirection == 'W') {
				cY += -1;
			} else {
				cY += 1;
			}
		}
	}

	private static char rotate90(char direction) {
		if (direction == 'N') {
			return 'E';
		} else if (direction == 'S') {
			return 'W';
		} else if (direction == 'W') {
			return 'N';
		} else {
			return 'S';
		}
	}

}
