
/*
ID: dingarv1
LANG: JAVA
TASK:  ratios
*/

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ratios {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new File("ratios.out"));
		int[] goal = new int[3];
		for (int i = 0; i < 3; i++) {
			goal[i] = in.nextInt();
		}
		int[][] info = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				info[i][k] = in.nextInt();
			}
		}
		int a;
		int b;
		int c;

		int ratio;
		abc: {
			for (int i = 0; i <= 100; i++) {
				for (int o = 0; o <= 100; o++) {
					for (int k = 0; k <= 100; k++) {
						a = i * info[0][0] + o * info[1][0] + k * info[2][0];
						b = i * info[0][1] + o * info[1][1] + k * info[2][1];
						c = i * info[0][2] + o * info[1][2] + k * info[2][2];
						ratio = -1;
						if (goal[0] != 0) {
							ratio = a / goal[0];
							if (a % goal[0] != 0) {
								continue;
							}
						} else {
							if (a != 0) {
								continue;
							}
						}
						if (goal[1] != 0) {
							if (ratio == -1) {
								ratio = b / goal[1];
							}
							if (b % goal[1] != 0 || ratio != b / goal[1]) {
								continue;
							}
						} else {
							if (b != 0) {
								continue;
							}
						}
						if (goal[2] != 0) {
							if (ratio == -1) {
								ratio = c / goal[2];
							}
							if (c % goal[2] != 0 || ratio != c / goal[2]) {
								continue;
							}
						} else {
							if (c != 0) {
								continue;
							}
						}
						if (ratio == 0) {
							continue;
						}
						out.println(i + " " + o + " " + k + " " + ratio);
						break abc;
					}
				}
			}
			out.println("NONE");
		}
		out.close();
		in.close();
	}
}