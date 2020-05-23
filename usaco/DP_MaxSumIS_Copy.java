
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

public class DP_MaxSumIS_Copy {
	private static int[] dp = new int[200001];

	public static int maxSumIS(int[] arr, int index, int currMax, boolean save) {
		if (dp[index] != 0)
			return dp[index];

		if (index == arr.length)
			return 0;
		int max = 0;

		if (arr[index] > currMax) {

			max = Math.max(maxSumIS(arr, index + 1, arr[index], save) + arr[index],
					maxSumIS(arr, index + 1, currMax, save));
			if (save) {
				dp[index] = max;
				// System.out.println( index+" "+max);
			}

		} else {
			max = maxSumIS(arr, index + 1, currMax, false);
		}

		return max;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			String[] line = in.readLine().split(" ");
			int[] seq = new int[line.length];
			for (int i = 0; i < seq.length; i++) {
				seq[i] = Integer.parseInt(line[i]);
			}
			System.out.println(maxSumIS(seq, 0, Integer.MIN_VALUE, true));
		}
	}
}
