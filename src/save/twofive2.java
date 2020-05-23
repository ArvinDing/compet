package save;

/*
ID: dingarv1
LANG: JAVA
TASK: twofive
*/

import java.util.*;
import java.io.*;

public class twofive2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new File("twofive.out"));
		int[][][][][] dp = new int[6][6][6][6][6];
		dp[5][5][5][5][5] = 1;

		for (int i = 5; i >= 0; i--) {
			for (int k = i; k >= 0; k--) {
				for (int j = k; j >= 0; j--) {
					for (int z = j; z >= 0; z--) {
						for (int p = z; p >= 0; p--) {
							if (p == 5)
								continue;
							dp[i][k][j][z][p] = dp[i][k][j][z][p + 1];
							if (i != 5)
								dp[i][k][j][z][p] += dp[i + 1][k][j][z][p];
							if (k != 5)
								dp[i][k][j][z][p] += dp[i][k + 1][j][z][p];
							if (j != 5)
								dp[i][k][j][z][p] += dp[i][k][j + 1][z][p];
							if (z != 5)
								dp[i][k][j][z][p] += dp[i][k][j][z + 1][p];

						}
					}
				}
			}
		}
		// System.out.println(dp[0][0][0][0][0]);

		if (in.readLine().equals("N")) {
			for (int target = 0; target < 10022; target++) {
				// int target = Integer.parseInt(in.readLine()) - 1;
				char[][] build = new char[5][5];
				int[] ans = new int[5];
				int idx = 0;
				for (int curr = 65; curr <= 89; curr++) {
					if (ans[0] != 5) {
						if (idx + dp[ans[0] + 1][ans[1]][ans[2]][ans[3]][ans[4]] > target) {
							build[0][ans[0]++] = (char) curr;
							continue;
						}
						idx += dp[ans[0] + 1][ans[1]][ans[2]][ans[3]][ans[4]];
					}
					if (ans[1] != 5 && ans[1] + 1 <= ans[0]) {
						if (idx + dp[ans[0]][ans[1] + 1][ans[2]][ans[3]][ans[4]] > target) {
							build[1][ans[1]++] = (char) curr;
							continue;
						}
						idx += dp[ans[0]][ans[1] + 1][ans[2]][ans[3]][ans[4]];
					}
					if (ans[2] != 5 && ans[2] + 1 <= ans[1]) {
						if (idx + dp[ans[0]][ans[1]][ans[2] + 1][ans[3]][ans[4]] > target) {
							build[2][ans[2]++] = (char) curr;
							continue;
						}
						idx += dp[ans[0]][ans[1]][ans[2] + 1][ans[3]][ans[4]];
					}
					if (ans[3] != 5 && ans[3] + 1 <= ans[2]) {
						if (idx + dp[ans[0]][ans[1]][ans[2]][ans[3] + 1][ans[4]] > target) {
							build[3][ans[3]++] = (char) curr;
							continue;
						}
						idx += dp[ans[0]][ans[1]][ans[2]][ans[3] + 1][ans[4]];
					}
					if (ans[4] != 5 && ans[4] + 1 <= ans[3]) {
						if (idx + dp[ans[0]][ans[1]][ans[2]][ans[3]][ans[4] + 1] > target) {
							build[4][ans[4]++] = (char) curr;
							continue;
						}
						idx += dp[ans[0]][ans[1]][ans[2]][ans[3]][ans[4] + 1];
					}
					System.out.println("error");
				}
				for (int i = 0; i < 5; i++) {
					for (int k = 0; k < 5; k++) {
						out.print(build[i][k]);
					}
				}
				out.println();
			}
		} else {
			String info = in.readLine();
			int[] important = new int[25];
			int index = 0;
			for (char a : info.toCharArray()) {
				important[a - 65] = index / 5;
				index++;
			}
			int idx = 0;
			int[] ans = new int[5];
			for (int i = 0; i < 25; i++) {
				if (important[i] == 0) {
					ans[0]++;
					continue;
				}
				if (ans[0] != 5)
					idx += dp[ans[0] + 1][ans[1]][ans[2]][ans[3]][ans[4]];

				if (important[i] == 1) {
					ans[1]++;
					continue;
				}
				if (ans[1] != 5)
					idx += dp[ans[0]][ans[1] + 1][ans[2]][ans[3]][ans[4]];

				if (important[i] == 2) {
					ans[2]++;
					continue;
				}
				if (ans[2] != 5)
					idx += dp[ans[0]][ans[1]][ans[2] + 1][ans[3]][ans[4]];

				if (important[i] == 3) {
					ans[3]++;
					continue;
				}
				if (ans[3] != 5)
					idx += dp[ans[0]][ans[1]][ans[2]][ans[3] + 1][ans[4]];

				if (important[i] == 4) {
					ans[4]++;
					continue;
				}
				if (ans[4] != 5)
					idx += dp[ans[0]][ans[1]][ans[2]][ans[3]][ans[4] + 1];
			}
			out.println(idx + 1);
		}
		in.close();
		out.close();
	}
}
