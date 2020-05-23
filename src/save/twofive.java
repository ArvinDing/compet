package save;

/*
ID: dingarv1
LANG: JAVA
TASK: twofive
*/

import java.util.*;
import java.io.*;

public class twofive {
	static int[] r, c;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new File("twofive.out"));
		r = new int[25];
		c = new int[25];
		used = new boolean[25];
		if (in.readLine().equals("N")) {
			int target = Integer.parseInt(in.readLine()) - 1;
			int index = 0;
			char[][] ans = new char[5][5];
			for (int i = 0; i < 5; i++) {
				for (int k = 0; k < 5; k++) {
					for (int curr = 0; curr < 25; curr++) {
						if (used[curr])
							continue;
						used[curr] = true;
						r[curr] = i;
						c[curr] = k;
						ans[i][k] = (char) (curr + 65);
						int save = skipped(curr);
						if (index + save > target) {
							break;
						}
						used[curr] = false;
						index += save;
					}
				}
			}
			for (int i = 0; i < 5; i++) {
				for (int k = 0; k < 5; k++) {
					out.print(ans[i][k]);
				}
			}
			out.println();

		} else {
			String input = in.readLine();
			char[] ans = input.toCharArray();
			int index = 0;
			for (int i = 0; i < 5; i++) {
				for (int k = 0; k < 5; k++) {
					for (int curr = 0; curr < 25; curr++) {
						if (used[curr])
							continue;
						used[curr] = true;
						r[curr] = i;
						c[curr] = k;
						int save = skipped(curr);
						if (ans[i * 5 + k] == curr + 65) {
							break;
						}
						used[curr] = false;
						index += save;
					}
				}
			}
			out.println(index + 1);
		}
		in.close();
		out.close();
	}

	private static int skipped(int test) {
		int[][][][][] dp = new int[6][6][6][6][6];
		dp[5][5][5][5][5] = 1;
		for (int i = 5; i >= 0; i--) {
			for (int k = i; k >= 0; k--) {
				for (int j = k; j >= 0; j--) {
					for (int z = j; z >= 0; z--) {
						for (int p = z; p >= 0; p--) {

							int curr = i + k + j + z + p;
							if (p == 5)
								continue;
							if (used[curr]) {
								int[] temp = new int[] { i, k, j, z, p };
								if ((r[curr] == 0 || temp[r[curr]] < temp[r[curr] - 1]) && temp[r[curr]] == c[curr]) {
									temp[r[curr]]++;
									dp[i][k][j][z][p] += dp[temp[0]][temp[1]][temp[2]][temp[3]][temp[4]];
								}
							} else {
								dp[i][k][j][z][p] = dp[i][k][j][z][p + 1];
								if (i != 5)
									dp[i][k][j][z][p] += dp[i + 1][k][j][z][p];
								if (k != 5 && k < i)
									dp[i][k][j][z][p] += dp[i][k + 1][j][z][p];
								if (j != 5 && j < k)
									dp[i][k][j][z][p] += dp[i][k][j + 1][z][p];
								if (z != 5 && z < j)
									dp[i][k][j][z][p] += dp[i][k][j][z + 1][p];
							}
						}
					}
				}
			}
		}
		return dp[0][0][0][0][0];
	}

}
