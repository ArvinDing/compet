package save;

import java.util.*;
import java.io.*;

public class lifeguards {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new File("lifeguards.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0] == arg1[0])
					return arg1[1] - arg0[1];
				return arg0[0] - arg1[0];
			}
		});
		int maxEnd = -1;
		int oldN = n;
		LinkedList<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < oldN; i++) {
			if (info[i][1] > maxEnd) {
				queue.add(info[i]);
				maxEnd = Math.max(maxEnd, info[i][1]);
			} else {
				k--;
				n--;
			}
		}
		k = Math.max(k, 0);
		int[][] dp = new int[n + 1][k + 1];
		Arrays.fill(dp[0], -1);
		dp[0][k] = 0;
		int nonIntersect = 0;
		int index = 1;
		int[] ends = new int[n + 1];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			while (nonIntersect + 1 < index && (ends[nonIntersect + 1] <= curr[0])) {
				nonIntersect++;
			}
			int[] leftMost = null;
			if (nonIntersect + 1 < index) {
				leftMost = dp[nonIntersect + 1];
			}
			int[] best = dp[nonIntersect];
			for (int i = 0; i < k; i++) {
				dp[index][i] = dp[index - 1][i + 1];
			}

			for (int i = 0; i <= k; i++) {
				int noOverlap = Math.max(0, i - ((index - 1) - nonIntersect));
				int overlap = Math.max(0, i - ((index - 2) - nonIntersect));
				if (best[i] != -1)
					dp[index][noOverlap] = Math.max(dp[index][noOverlap], best[i] + (curr[1] - curr[0]));
				if (leftMost != null && leftMost[i] != -1)
					dp[index][overlap] = Math.max(dp[index][overlap],
							leftMost[i] + (curr[1] - curr[0]) - (ends[(nonIntersect + 1)] - curr[0]));
			}
			for (int i = 0; i < k; i++) {
				dp[index][i + 1] = Math.max(dp[index][i + 1], dp[index][i]);
			}
			ends[index] = curr[1];

			index++;
		}
		out.println(dp[n][0]);
		out.close();
		in.close();

	}
}
