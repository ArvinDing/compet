package camp;

import java.io.*;
import java.util.*;

public class lifeguards {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new File("lifeguards.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		LinkedList<int[]> info = new LinkedList<int[]>();
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			info.add(new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) });
		}
		Collections.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		// inside Overlap
		LinkedList<int[]> real = new LinkedList<int[]>();
		int maxEnd = -1;
		while (!info.isEmpty()) {
			int[] curr = info.poll();
			if (curr[1] <= maxEnd) {
				k--;
				continue;
			}
			maxEnd = curr[1];
			real.add(curr);
		}
		int[][] data = new int[real.size()][2];
		int o = 0;
		while (!real.isEmpty()) {
			data[o] = real.poll();
			o++;
		}

		int[][] dp = new int[data.length][k + 1];
		int[] max = new int[k];
		// earliest overlap

		max[0] = 0;
		Arrays.fill(max, -1);
		dp[0][0] = data[0][1] - data[0][0];
		dp[0][1] = 0;
		int[] eOverlap = new int[n];
		int eO = 0;
		for (int i = 1; i < n; i++) {
			while (eO + 1 != i && data[eO][1] <= data[i][0]) {
				eO++;
			}
			eOverlap[i] = eO;
		}
		for (int i = 1; i < data.length; i++) {
			// cut used

			for (int l = Math.min(k, i + 1); l >= 0; l--) {
				int olap = eOverlap[i];
				if (data[olap][1] <= data[i][0]) {
					// noOverlap
					if (l == 0)
						dp[i][l] = dp[eO][l] + data[i][1] - data[i][0];
					else
						dp[i][l] = Math.max(dp[eO][l], max[l - 1]) + data[i][1] - data[i][0];
				} else {
					if (l == 0)
						dp[i][l] = dp[eO][l] + (data[i][1] - data[eO][0]) - (data[i - 1][1] - data[eO][0]);
					else {
						dp[i][l] = Math.max(max[l - 1] + data[i][1] - data[i][0],
								dp[i - 1][l] + (data[i][1] - data[i - 1][0]) - (data[i - 1][1] - data[i - 1][0]));
						if (l == 1)
							max[l - 1] = dp[i - 1][k - 1];
						else
							max[l - 1] = Math.max(max[l - 2], dp[i - 1][k - 1]);
					}
				}

			}
		}
		int maxC = 0;
		for (int i = 0; i < data.length; i++) {
			maxC = Math.max(maxC, dp[i][k]);
		}
		out.println(maxC);
		out.close();
		in.close();

	}
}