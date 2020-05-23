
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class DP_LongestCommonSubseq {
	private static int[][] dp;
	private static char[] x;
	private static char[] y;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int xLength = Integer.parseInt(read.nextToken());
			int yLength = Integer.parseInt(read.nextToken());
			x = in.readLine().toCharArray();
			y = in.readLine().toCharArray();
			dp = new int[xLength][yLength];
			boolean flag = false;
			for (int z = 0; z < xLength; z++) {
				if (flag) {
					dp[z][0] = 1;
					continue;
				}
				dp[z][0] = (x[z] == y[0]) ? 1 : 0;
				flag = (x[z] == y[0]);
			}
			flag = false;
			for (int z = 0; z < yLength; z++) {
				if (flag) {
					dp[0][z] = 1;
					continue;
				}
				dp[0][z] = (x[0] == y[z]) ? 1 : 0;
				flag = (x[0] == y[z]);
			}
			for (int xI = 1; xI < xLength; xI++) {
				for (int yI = 1; yI < yLength; yI++) {
					dp[xI][yI] = Math.max(dp[xI][yI - 1], dp[xI - 1][yI]);
					if (x[xI] == y[yI]) {
						dp[xI][yI] = Math.max(dp[xI - 1][yI - 1] + 1, dp[xI][yI]);
					}
				}
			}
			System.out.println(dp[xLength - 1][yLength - 1]);

		}
		in.close();
	}
}
