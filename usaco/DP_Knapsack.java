
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class DP_Knapsack {

	public static long maxValue(int[] wa, int[] va, int w) {
		int[][] dp = new int[va.length+1][w + 1];
		
		for (int i = 1; i < va.length+1; i++) {
			for (int k = 0; k < w + 1; k++) {

				if (k - wa[i-1] >= 0) {
					dp[i][k] = Math.max(dp[i - 1][k], dp[i-1][k - wa[i-1]] + va[i-1]);
				} else {
					dp[i][k] = dp[i - 1][k];
				}

			}
		}
		return dp[va.length ][w];
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			String[] line = in.readLine().split(" ");
			int n = Integer.parseInt(line[0]), W = Integer.parseInt(line[1]);
			int[] wa = new int[n], va = new int[n];
			for (int i = 0; i < n; i++) {
				line = in.readLine().split(" ");
				wa[i] = Integer.parseInt(line[0]);
				va[i] = Integer.parseInt(line[1]);
			}
			System.out.println(maxValue(wa, va, W));
		}
	}
}
