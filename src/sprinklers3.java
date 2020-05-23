import java.io.*;
import java.util.*;

public class sprinklers3 {
	static final long mod = 1000000007;
	static final long inv = 500000004;
	static boolean[][] info;
	static int n;
	static long[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("sprinklers2.in"));
		PrintWriter out = new PrintWriter(new File("sprinklers2.out"));
		n = Integer.parseInt(in.readLine());
		info = new boolean[n][n];
		int free = 0;
		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			for (int k = 0; k < n; k++) {
				info[i][k] = line.charAt(k) == '.';
				if (info[i][k])
					free++;
			}
		}
		dp = new long[n][n][2];
		// 0 is corn,1 is alfafa
		long imp = power(2, free);
		for (int i = 0; i < n; i++) {
			if (info[0][i]) {
				dp[0][i][0] = (imp * inv) % mod;
			}
			if (info[i][0]) {
				dp[i][0][1] = (imp * inv) % mod;
			}
		}
		long[][][] sum = new long[n][n][2];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {

				if (info[i][k]) {
					if (i != 0) {
						dp[i][k][0] = ((sum[i - 1][k][1]) * inv) % mod;
					}
					if (k != 0) {
						dp[i][k][1] = ((sum[i][k - 1][0]) * inv) % mod;
					}
				}
				sum[i][k][1] = (((k == 0) ? 0 : sum[i][k - 1][1]) + dp[i][k][1]) % mod;
				sum[i][k][0] = (((i == 0) ? 0 : sum[i - 1][k][0]) + dp[i][k][0]) % mod;
			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int k = 0; k < n; k++) {
//				System.out.print(dp[i][k][0] + "|" + dp[i][k][1] + " ");
//			}
//			System.out.println();
//		}
		long ans = 0;
		for (int i = 0; i < n; i++) {
			ans = (ans + dp[n - 1][i][1] + dp[i][n - 1][0]) % mod;
		}
		out.println(ans);
		out.close();
		in.close();
	}

	static long power(int base, int pow) {
		if (pow == 0)
			return 1;
		if (pow % 2 == 1) {
			return base * power(base, pow / 2) * power(base, pow / 2) % mod;
		} else {
			return power(base, pow / 2) * power(base, pow / 2) % mod;
		}
	}

}
