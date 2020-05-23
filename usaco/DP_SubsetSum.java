
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

public class DP_SubsetSum {
	public static boolean hasSubset(int[] nums, int sum) {
		boolean[][] dp = new boolean[nums.length + 1][500001];
		dp[nums.length][0] = true;
		for (int i = nums.length - 1; i >= 0; i--) {
			for (int k = 0; k < sum; k++) {
				if (dp[i + 1][k]) {
					if (k == sum || k + nums[i] == sum) {
						return true;
					}
					dp[i][k] = true;
					dp[i][k + nums[i]] = true;
				}
			}
		}
		return false;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader("input"))) {
			int sum = Integer.parseInt(in.readLine());
			String[] line = in.readLine().split(" ");
			int[] nums = new int[line.length];
			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.parseInt(line[i]);
			}
			System.out.println(hasSubset(nums, sum));
		}
	}
}