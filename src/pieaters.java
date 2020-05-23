import java.io.*;
import java.util.*;

public class pieaters {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pieaters.in"));
		PrintWriter out = new PrintWriter(new File("pieaters.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		long[][] dp = new long[n][n];
		int[][][] maxInt = new int[n][n][n];
		// include i, left(inclusive), right(inclusive)
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int weight = Integer.parseInt(read.nextToken());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			for (int k = start; k <= end; k++) {
				maxInt[k][start][end] = weight;
			}
		}
		for (int include = 0; include < n; include++) {
			for (int len = 1; len <= n; len++) {
				for (int a = 0; a + len - 1 < n; a++) {
					if (a > include)
						break;
					if (a + len - 1 < include)
						continue;
				
					if (a <= a + len - 2)
						maxInt[include][a][a + len - 1] = Math.max(maxInt[include][a][a + len - 1],
								maxInt[include][a][a + len - 2]);
					if (a + 1 <= a + len - 1)
						maxInt[include][a][a + len - 1] = Math.max(maxInt[include][a][a + len - 1],
								maxInt[include][a + 1][a + len - 1]);
				}
			}
		}
		for (int len = 1; len <= n; len++) {
			for (int a = 0; a + len - 1 < n; a++) {
				int b = a + len - 1;
				if (a == b)
					dp[a][b] = maxInt[a][a][b];
				for (int i = a + 1; i < b; i++) {
					dp[a][b] = Math.max(dp[a][b], dp[a][i] + dp[i + 1][b]);
				}
				for (int i = a + 1; i < b; i++) {
					dp[a][b] = Math.max(dp[a][b], dp[a][i - 1] + dp[i + 1][b] + maxInt[i][a][b]);
				}
				if (a + 1 <= b)
					dp[a][b] = Math.max(dp[a][b], dp[a + 1][b] + maxInt[a][a][b]);
				if (a <= b - 1)
					dp[a][b] = Math.max(dp[a][b], dp[a][b - 1] + maxInt[b][a][b]);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(dp[i][k] + " ");
			}
			System.out.println();
		}
		out.println(dp[0][n - 1]);
		out.close();
		in.close();
	}
}
