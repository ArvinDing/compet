import java.io.*;
import java.util.*;

public class sprinklers2 {
	static final long mod = 1000000007;
	static boolean[][] info;
	static int n;
	static long[] dp;
	static int maxN = 202;

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
		long sum = 0;
		int pivotStart = Math.min(2 * n - 1, free);
		dp = new long[maxN * maxN * maxN * 2 * 2];
		Arrays.fill(dp, -1);
		long ways = power(2, free - (pivotStart));
		for (int pivots = pivotStart; pivots >= 1; pivots--) {
			long downF = waysPivots(-1, 1, pivots, 0);
			long rightF = waysPivots(0, 0, pivots, 1);
			// .out.println(downF + " " + rightF);
			sum = (sum + ((downF + rightF) % mod) * ways % mod) % mod;
			ways = ways * 2 % mod;
		}
		out.println(sum);
		out.close();
		in.close();
	}

	static int convert(int x, int y, int pivots, int direc) {
		return x * maxN * 2 * (maxN - 1) * 2 + y * 2 * (maxN - 1) * 2 + pivots * 2 + direc;
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

	static long waysPivots(int x, int y, int pivots, int direction) {
		// direction 0 is down, 1 is right
		if (x >= n || y >= n + 1 || pivots < 0)
			return 0;
		
		if (x == n - 1 && y == n) {
			return (pivots == 0) ? 1 : 0;
		}
		
		if (dp[convert(x + 1, y, pivots, direction)] != -1)
			return dp[convert(x + 1, y, pivots, direction)];
		
		long down = 0;
		long right = 0;
		if (direction == 0) {
			down = waysPivots(x, y + 1, pivots, 0);
			if (x + 1 < n && y - 1 >= 0 && info[x + 1][y - 1]) {
				right = waysPivots(x + 1, y, pivots - 1, 1);
			}
		} else {
			right = waysPivots(x + 1, y, pivots, 1);
			if (x != -1 && y != n && info[x][y]) {
				down = waysPivots(x, y + 1, pivots - 1, 0);
			}
		}

		dp[convert(x + 1, y, pivots, direction)] = ((down + right) % mod);
		return dp[convert(x + 1, y, pivots, direction)];
	}
}
