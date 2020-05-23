
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

public class DP2_subsequence {
	static int lSeqLength(int arr[]) {
		int[] dp = new int[arr.length + 1];
		int max = 1;
		Arrays.fill(dp, 1);
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < i; k++) {
				if (dp[k + 1] + 1 > dp[i + 1] && arr[k] < arr[i]) {
					dp[i+1] = dp[k+1] + 1;
					max = Math.max(max, dp[i+1]);
				}
			}
		}
	
		return max;
	}

	public static void main(String args[]) throws NumberFormatException, IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			int cnt = Integer.parseInt(in.readLine());
			int[] arr = new int[cnt];
			for (int i = 0; i < cnt; i++) {
				arr[i] = Integer.parseInt(in.readLine());
			}
			System.out.println(lSeqLength(arr));
		}
	}
}