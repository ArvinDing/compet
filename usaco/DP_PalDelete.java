
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

public class DP_PalDelete {
	private static int[][] dp;

	static int minStepsToDeleteString(String str) {
		dp = new int[str.length()][str.length() ];
		return recursion(str, 0, str.length() - 1);

	}

	static int recursion(String str, int start, int end) {
		if (dp[start][end] != 0)
			return dp[start][end];
		if (start == end || start > end)
			return 1;

		if (str.charAt(start) == str.charAt(end)) {
			return recursion(str, start + 1, end - 1);
		}

		int min = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {
			min = Math.min(min, recursion(str, start, i) + recursion(str, i + 1, end));
		}
		dp[start][end] = min;
		return min;

	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		try  (BufferedReader in = new BufferedReader(new java.io.InputStreamReader(System.in)))  {
			System.out.println(minStepsToDeleteString(in.readLine()));
		}
	}
}
