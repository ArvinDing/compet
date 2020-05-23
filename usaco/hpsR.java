import java.io.*;
import java.util.*;

public class hpsR {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new File("hps.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int changes = Integer.parseInt(read.nextToken());
		int[] info = new int[n];
		for (int i = 0; i < n; i++) {
			char curr = in.readLine().charAt(0);
			if (curr == 'H') {
				info[i] = 1;
			} else if (curr == 'P') {
				info[i] = 2;
			}
		}
		int[][][] dp = new int[n + 1][changes + 1][3];
		for (int i = 1; i <= n; i++) {
			for (int k = 0; k <= changes; k++) {
				for (int move = 0; move < 3; move++) {
					dp[i][k][move] = dp[i - 1][k][move] + ((move == info[i - 1]) ? 1 : 0);
					if (k != 0) {
						dp[i][k][move] = Math.max(dp[i][k][move],
								Math.max(dp[i-1][k - 1][(move + 1) % 3] + (((move + 1) % 3 == info[i - 1]) ? 1 : 0),
										dp[i-1][k - 1][(move + 2) % 3] + (((move + 2) % 3 == info[i - 1]) ? 1 : 0)));
					}
				}
			}
		}
		out.println(Math.max(dp[n][changes][0], Math.max(dp[n][changes][1], dp[n][changes][2])));
		out.close();
		in.close();
	}
}
