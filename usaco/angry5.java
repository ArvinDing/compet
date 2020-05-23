
import java.io.*;
import java.util.*;

public class angry5 {
	private static int[][] dp;
	private static int[] info;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new File("angry.out"));
		n = Integer.parseInt(in.readLine());
		info = new int[n];
		double print = Integer.MAX_VALUE;
		dp = new int[n][2];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(info);
		int index = 0;
		for (int i = 1; i < n; i++) {
			while (info[i] - info[index] > 1 + dp[index][0]) {
				if (index == i - 1)
					break;
				index++;
			}

			dp[i][0] = Math.max(info[i] - info[i - 1], 1 + dp[index][0]);
		}
		index = n - 1;
		for (int i = n - 2; i >= 0; i--) {
			while (info[index] - info[i] > 1 + dp[index][1]) {
				if (index == i + 1)
					break;
				index--;
			}

			dp[i][1] = Math.max(info[i + 1] - info[i], 1 + dp[index][1]);
		}

		int start = 0;
		int end = n - 1;
		while (start < end) {
			print = Math.min(Math.max((info[end] - info[start]) / 2d, 1 + Math.max(dp[end][1], dp[start][0])), print);

			if (dp[start + 1][0] < dp[end - 1][1]) {
				start++;
			} else {
				end--;
			}

		}

		out.printf("%.1f", print);
		out.close();
		in.close();
	}

}