
import java.io.BufferedReader;
import java.io.File;
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

public class DP_NDigitSum {
	public static long countNSum(int n, int sum) {
		long[][] dp = new long[n + 1][sum + 1];
		for (int i = 1; i <= 9; i++) {
			if (i > sum) {
				break;
			}
			dp[1][i] = 1;
		}
		for (int i = 2; i <= n; i++) {
			for (int z = 0; z <= sum; z++) {
				if (dp[i - 1][z] != 0) {
					for (int add = 0; add <= 9; add++) {
						if (z + add > sum) {
							continue;
						}
						dp[i][z + add] += dp[i - 1][z];
					}
				}
			}
		}
		return dp[n ][sum];

	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader("input"))) {
			String[] line = in.readLine().split(" ");
			System.out.println(countNSum(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
		}
	}
}