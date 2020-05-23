
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class DP_PairChain {
	public static int longestChain(int[][] pairs) {
		// TODO implement this
		Arrays.sort(pairs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int[] dp = new int[pairs.length];
		int max = 0;
		for (int i = 0; i < pairs.length; i++) {
			dp[i] = 1;
			for (int k = i - 1; k >= 0; k--) {
				if (pairs[k][1] < pairs[i][0]) {
					dp[i] = Math.max(dp[k] + 1, dp[i]);
					max = Math.max(dp[i], max);
				}
			}
		}
		return max;
	}

	private static int n;

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.InputStreamReader(System.in))) {

			n = Integer.parseInt(in.readLine());
			int[][] pairs = new int[n][2];
			for (int i = 0; i < n; i++) {
				String[] one = in.readLine().split(" ");
				pairs[i][0] = Integer.parseInt(one[0]);
				pairs[i][1] = Integer.parseInt(one[1]);
			}
			System.out.println(longestChain(pairs));
		}
	}
}