
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

public class DP_EqualSumPartition {
	private static boolean equalSumPartition(int[] arr) {
		int sum2 = 0;
		for (int one : arr) {
			sum2 += one;
		}
		if (sum2 % 2 != 0) {
			return false;
		}
		sum2 /= 2;
		// TODO: implement this
		boolean[][] dp = new boolean[arr.length + 1][500001];
		dp[arr.length][0] = true;
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int k = 0; k < sum2; k++) {
				if (dp[i + 1][k]) {
					if (k == sum2 || k + arr[i] == sum2) {
						return true;
					}
					dp[i][k] = true;
					dp[i][k + arr[i]] = true;
				}
			}
		}
		return false;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(
				new java.io.InputStreamReader(System.in))) {/*
															 * )) {
															 */
			String[] line = in.readLine().split(" ");
			int[] seq = new int[line.length];
			for (int i = 0; i < seq.length; i++) {
				seq[i] = Integer.parseInt(line[i]);
			}
			System.out.println(equalSumPartition(seq));
		}
	}
}