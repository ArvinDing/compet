
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

public class DP_CoinPathCount {

	public static long pathCount(int[] cvA, int target) {
		int[][] dp = new int[cvA.length + 1][target + 1];
		for (int i = 0; i < cvA.length; i++) {
			dp[i][0] = 1;
		}

		for (int i = 0; i < cvA.length; i++) {
			for (int k = 1; k <= target; k++) {
				if (i == 0) {
					if (k % cvA[i] == 0) {
						dp[i][k] = 1;
					} else {
						dp[i][k] = 0;
					}
				} else {
					if (k - cvA[i] < 0) {
						dp[i][k] = dp[i - 1][k];
					} else {
						dp[i][k] = dp[i - 1][k] + dp[i][k - cvA[i]];
					}
				}

			}
		}
		return dp[cvA.length - 1][target];
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader("input"))) {
			String[] line = in.readLine().split(" ");
			int m = Integer.parseInt(line[0]), N = Integer.parseInt(line[1]);
			int[] cv = new int[m];
			for (int i = 0; i < m; i++) {
				cv[i] = Integer.parseInt(in.readLine());
			}
			System.out.println(pathCount(cv, N));
		}
	}
}
