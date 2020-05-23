
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class gold248 {
	private static int[] info;
	private static int[][] dp;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("248.in"));
		PrintWriter out = new PrintWriter(new File("248.out"));
		int N = Integer.parseInt(in.readLine());
		dp = new int[N][N];
		info = new int[N];
		for (int i = 0; i < N; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		recursion(0, N - 1);
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int k = i; k < N; k++) {
				max = Math.max(dp[i][k], max);
			}
		}
		out.print(max);
		out.close();
		in.close();
	}

	private static int recursion(int i, int n) {
		if (i == n)
			return info[i];
		if (dp[i][n] != 0)
			return dp[i][n];
	
		int max = -1;
		for (int len = 0; len <= n - i - 1; len++) {
			int beginning = recursion(i, i + len);
			int end = recursion(i + len + 1, n);
			if (beginning == -1 || end == -1)
				continue;

			if (beginning == end) {
				max = Math.max(max, beginning + 1);
			}
		}
		dp[i][n] = max;
		return max;

	}
}