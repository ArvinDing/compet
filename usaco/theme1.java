
/*
ID: dingarv1
LANG: JAVA
TASK: theme
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class theme1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("theme.in"));
		PrintWriter out = new PrintWriter(new File("theme.out"));
		int N = Integer.parseInt(in.readLine());
		int[] info = new int[N];
		for (int i = 0; i <= (N - 1) / 20; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < 20; k++) {
				if (!read.hasMoreTokens()) {
					break;
				}
				info[i * 20 + k] = Integer.parseInt(read.nextToken());
			}
		}
		in.close();
		int length = 1;
		int[][] dp = new int[N][N];
		int theme = 0;
		for (int i = N; i >= 0; i--) {
			for (int k = N - 1; k > i + length; k--) {

				if ((i + 1 < N && k + 1 < N) && info[i + 1] - info[i] == info[k + 1] - info[k]
						&& k != i + dp[i + 1][k + 1]) {
					dp[i][k] = 1 + dp[i + 1][k + 1];
				} else {
					dp[i][k] = 1;
				}
				theme = Math.max(theme, dp[i][k]);
				length = dp[i][k];
			}
		}

		if (theme >= 5) {
			out.println(theme);
		} else {
			out.println(0);
		}
	
		out.close();

	}
}