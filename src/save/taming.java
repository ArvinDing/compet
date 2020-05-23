package save;

import java.io.*;
import java.util.*;

public class taming {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("taming.in"));
		PrintWriter out = new PrintWriter(new File("taming.out"));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[][][] dp = new int[n][n][n + 1];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				for (int j = 0; j < n + 1; j++) {
					dp[i][k][j] = Integer.MAX_VALUE;
				}
			}
		}
		// position, last breakpoint,breakpoints used
		dp[0][0][1] = (Integer.parseInt(read.nextToken()) == 0) ? 0 : 1;

		for (int i = 1; i < n; i++) {
			int curr = Integer.parseInt(read.nextToken());
			for (int k = 0; k < i; k++) {
				for (int j = 1; j <= k + 1; j++) {
					if (dp[i - 1][k][j] == Integer.MAX_VALUE)
						continue;
					dp[i][i][j + 1] = Math.min(dp[i][i][j + 1], dp[i - 1][k][j] + ((curr == 0) ? 0 : 1));

				}
			}
			for (int k = 0; k < i; k++) {
				for (int j = 1; j <= k + 1; j++) {
					if (dp[i - 1][k][j] == Integer.MAX_VALUE)
						continue;
					dp[i][k][j] = dp[i - 1][k][j] + ((curr == i - k) ? 0 : 1);
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			for (int k = 0; k < n; k++) {
				min = Math.min(dp[n - 1][k][i], min);
			}
			out.println(min);
		}
		out.close();
		in.close();
	}
}
