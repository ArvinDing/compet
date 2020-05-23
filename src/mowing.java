import java.io.*;
import java.util.*;

public class mowing {
	private static int[] cnt;
	private static long[] dp;
	private static int[][] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mowing.in"));
		PrintWriter out = new PrintWriter(new File("mowing.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		// nvm
		int n = Integer.parseInt(read.nextToken());
		int t = Integer.parseInt(read.nextToken());
		info = new int[n + 2][2];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		info[n] = new int[] { 0, 0 };
		info[n + 1] = new int[] { t, t };

		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int[][] sortY = new int[n + 2][3];
		for (int i = 0; i < n + 2; i++) {
			sortY[i] = new int[] { info[i][0], info[i][1], i };
		}
		Arrays.sort(sortY, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		int[] previousY = new int[n + 2];
		previousY[0] = -1;
		for (int i = 1; i < n + 2; i++) {
			previousY[sortY[i][2]] = sortY[i - 1][2];
		}
		dp = new long[n + 2];
		Arrays.fill(dp, Long.MAX_VALUE / 2);
		dp[0]=0;
		cnt = new int[n + 2];
		outer: for (int i = 0; i < info.length - 1; i++) {
			long x = info[i][0];
			long y = info[i][1];

			int idx = i + 1;
			while (info[idx][1] < y) {
				idx++;
				if (idx == n + 2)
					continue outer;
			}
			if (info[idx][0] >= x)
				update(i, idx);
			while (info[previousY[idx]][1] > y) {
				if (info[previousY[idx]][0] >= x)
					update(i, previousY[idx]);
				idx = previousY[idx];
			}

		}
		out.println(dp[n + 1]);
		in.close();
		out.close();
	}

	private static void update(int prev, int next) {
		if (cnt[next] > cnt[prev] + 1)
			return;
		if (cnt[next] < cnt[prev] + 1) {
			cnt[next] = cnt[prev] + 1;
			dp[next] = dp[prev] + (long)(info[next][1] - info[prev][1]) * (info[next][0] - info[prev][0]);
			return;
		}
		dp[next] = Math.min(dp[next], dp[prev] + (long)(info[next][1] - info[prev][1]) * (info[next][0] - info[prev][0]));
	}
}
