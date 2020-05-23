package camp;

import java.io.*;
import java.util.*;

public class photo {
	private static int[] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new File("photo.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken()) + 1;
		int m = Integer.parseInt(read.nextToken());
		int[][] info = new int[m][2];
		segT = new int[4 * n];
		Arrays.fill(segT, -1);
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			info[m] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) };
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}

		});
		int[] smallS = new int[n];
		int[] start = new int[n];
		Arrays.fill(smallS, -1);
		int photoI = 0;
		for (int i = 0; i < n; i++) {
			while (photoI != m && i > info[photoI][1])
				photoI++;
			if (photoI == m)
				break;
			smallS[i] = info[photoI][0];
		}
		int minI = Integer.MAX_VALUE;
		photoI = 0;
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}

		});
		for (int i = 0; i < n; i++) {
			while (photoI != m && i > info[photoI][1]) {
				minI = Math.min(minI, info[photoI][0]);
				photoI++;
			}
			start[i] = minI;
		}
		update(0, n - 1, 1, 0, 0);
		for (int i = 0; i < n; i++) {
			if (smallS[i] == -1)
				smallS[i] = i;
			if (start[i] == Integer.MAX_VALUE)
				start[i] = 0;
			int res = query(0, n - 1, 1, start[i], smallS[i]);
			if (res != -1)
				update(0, n - 1, 1, i, res + 1);
		}
		out.println(segT[1]);
		out.close();
		in.close();

	}

	private static int query(int l, int r, int idx, int lx, int rx) {
		if (rx < l || r < lx)
			return 0;
		if (lx <= l && r <= rx)
			return segT[idx];
		int mid = (l + r) / 2;
		int res1 = query(l, mid, idx * 2, lx, rx);
		int res2 = query(mid + 1, r, idx * 2 + 1, lx, rx);
		return Math.max(res1, res2);

	}

	private static void update(int l, int r, int idx, int i, int value) {
		if (i < l || r < i)
			return;
		if (l == r) {
			segT[idx] = value;
			return;
		}
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, i, value);
		update(mid + 1, r, idx * 2 + 1, i, value);
		segT[idx] = Math.max(segT[idx * 2], segT[idx * 2 + 1]);
	}
}
