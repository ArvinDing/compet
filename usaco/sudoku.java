import java.io.*;
import java.util.*;

public class sudoku {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int[][] info = new int[9][9];
			for (int o = 0; o < 9; o++) {
				for (int p = 0; p < 9; p++) {
					info[o][p] = Integer.parseInt(read.nextToken());
				}
			}

			outer: for (int o = 0; o < 9; o++) {
				for (int p = 0; p < 9; p++) {
					if (info[o][p] == 0) {
						info = recursion(o, p, info);
						break outer;
					}
				}
			}
			System.out.print(info[0][0]);
			for (int o = 0; o < 9; o++) {
				for (int p = (o == 0) ? 1 : 0; p < 9; p++) {
					System.out.print(" " + info[o][p]);
				}
			}
			if (i != test - 1)
				System.out.println();
		}
		in.close();
	}

	private static int[][] recursion(int y, int x, int[][] info) {
		boolean[] possible = new boolean[10];
		Arrays.fill(possible, true);
		for (int i = 0; i < 9; i++) {

			possible[info[y][i]] = false;
			possible[info[i][x]] = false;
		}

		for (int i = y - (y % 3); i < y - (y % 3) + 3; i++) {
			for (int k = x - (x % 3); k < x - (x % 3) + 3; k++) {
				possible[info[i][k]] = false;
			}
		}
		for (int i = 1; i <= 9; i++) {
			if (possible[i]) {
				info[y][x] = i;
				outer: {
					int o = y;
					int p = x;
					while (o < 9) {
						if (info[o][p] == 0) {
							int[][] temp = recursion(o, p, info);
							if (temp != null) {
								return temp;
							}
							break outer;
						}
						p++;
						if (p == 9) {
							o++;
							p = 0;
						}

					}
					return info;
				}
				info[y][x] = 0;
			}
		}
		return null;

	}
}
