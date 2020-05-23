
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

public class DP_NStairWays {
	public static long countWays(int num) {
		long[] dp = new long[num + 1];
		dp[0]=1;
		dp[1] = 1;
		for (int i = 2; i <= num; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[num];
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(// new
													// java.io.FileReader(""input"")))
													// {/*
				new java.io.InputStreamReader(System.in))) {// */
			System.out.println(countWays(Integer.parseInt(in.readLine())));
		}
	}
}