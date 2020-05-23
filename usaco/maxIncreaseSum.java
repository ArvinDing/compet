import java.util.*;
import java.io.*;

public class maxIncreaseSum {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] info = new int[n];
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int z = 0; z < n; z++) {
				info[z] = Integer.parseInt(read.nextToken());
			}
			int[] dp = new int[n];
			for (int p = 0; p < n; p++) {
				dp[p] = info[p];
			}
			int max=0;
			for (int z = 0; z < n; z++) {
				max=Math.max(max, dp[z]);
				for (int w = z + 1; w < n; w++) {
					if (info[w] > info[z]) {
						dp[w] = Math.max(dp[w], dp[z] + info[w]);
					}
				}
			}
			System.out.println(max);
		}
		in.close();
	}
}
