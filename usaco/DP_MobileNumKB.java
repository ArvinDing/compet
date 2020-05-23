
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

public class DP_MobileNumKB {
	public static long countNums(int N) {
		long[][] dp = new long[N][10];
		for (int i = 0; i < 10; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i < N; i++) {
			dp[i][0] += dp[i - 1][0] + dp[i - 1][8];
			dp[i][1] += dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4];
			dp[i][2] += dp[i - 1][2] + dp[i - 1][1] + dp[i - 1][3] + dp[i - 1][5];
			dp[i][3] += dp[i - 1][3] + dp[i - 1][2] + dp[i - 1][6];
			dp[i][4] += dp[i - 1][4] + dp[i - 1][1] + dp[i - 1][5] + dp[i - 1][7];
			dp[i][5] += dp[i - 1][5] + dp[i - 1][2] + dp[i - 1][4] + dp[i - 1][6] + dp[i - 1][8];
			dp[i][6] += dp[i - 1][6] + dp[i - 1][3] + dp[i - 1][5] + dp[i - 1][9];
			dp[i][7] += dp[i - 1][7] + dp[i - 1][4] + dp[i - 1][8];
			dp[i][8] += dp[i - 1][8] + dp[i - 1][5] + dp[i - 1][7] + dp[i - 1][9] + dp[i - 1][0];
			dp[i][9] += dp[i - 1][9] + dp[i - 1][6] + dp[i - 1][8];
		}
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += dp[N - 1][i];
		}
		return sum;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(// new
													// java.io.FileReader(""input"")))
													// {/*
				new java.io.InputStreamReader(System.in))) {// */
			System.out.println(countNums(Integer.parseInt(in.readLine())));
		}
	}
}