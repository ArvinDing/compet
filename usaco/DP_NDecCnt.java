
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

public class DP_NDecCnt {
	private static long countNDec(int n) {
		int[][] dp = new int[n][10];
		for (int i = 0; i <= 9; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i < n; i++) {
			for (int k = 0; k <= 9; k++) {
				for (int z = k; z >= 0; z--) {
					dp[i][k] += dp[i - 1][z];
				}
			}
		}
		int sum = 0;
		for (int i = 0; i <= 9; i++) {
			sum += dp[n - 1][i];
		}
		return sum;

	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader(
				"input"))) {/*
							 * new java.io.InputStreamReader(System.in))) {//
							 */
			System.out.println(countNDec(Integer.parseInt(in.readLine())));
		}
	}

}