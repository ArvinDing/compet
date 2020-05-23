
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

public class DP_MaxSquare {

	private static int maxSquare(boolean[][] map) {
		int[][] dp = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			dp[0][i] = (map[0][i]) ? 1 : 0;
			dp[i][0] = (map[i][0]) ? 1 : 0;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < map.length; i++) {
			for (int k = 1; k < map.length; k++) {
				if (map[i][k]) {
					int min = Integer.MAX_VALUE;
					min = Math.min(dp[i - 1][k], min);
					min = Math.min(dp[i][k - 1], min);
					min = Math.min(dp[i - 1][k - 1], min);
					dp[i][k] = min+1;
					max = Math.max(dp[i][k], max);
				}
			}
		}
		return max;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader("input"))) {
			int n = Integer.parseInt(in.readLine());
			boolean[][] map = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				String line = in.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = line.charAt(j) == '1';
				}
			}
			System.out.println(maxSquare(map));
		}
	}
}