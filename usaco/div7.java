
import java.io.*;
import java.util.*;

public class div7 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("div7.in"));
		PrintWriter out = new PrintWriter(new File("div7.out"));
		long[][] dp = new long[50001][7];
		int[][] length = new int[50001][7];
		boolean[][] possible = new boolean[50001][7];
		possible[0][0] = true;
		int max = 0;
		int cows = Integer.parseInt(in.readLine());
		for (int i = 1; i <= cows; i++) {
			int curr = Integer.parseInt(in.readLine());
			for (int k = 0; k <= 6; k++) {
				int previous = (((k - (curr % 7)) + 7) % 7);
				if (possible[i - 1][previous]) {
					dp[i][k] = dp[i - 1][previous] + curr;
					length[i][k] = length[i - 1][previous] + 1;
					possible[i][k] = true;
				}
				if (k == curr % 7) {
					dp[i][k] = Math.max(dp[i][k], curr);
					if (dp[i][k] == curr) {
						length[i][k] = 1;
					}
					possible[i][k] = true;
				}
				if (k == 0 && dp[i][k] != 0) {
					max = Math.max(max, length[i][k]);
				}
			}

		}
		out.println(max);
		in.close();
		out.close();
	}
}