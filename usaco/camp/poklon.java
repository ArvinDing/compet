package camp;

import java.io.*;
import java.util.*;

public class poklon {
	private static int[] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("poklon.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) };
		}
		segT = new int[4 * 1000000];
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg1[0] == arg0[0])
					return arg1[1] - arg0[1];
				return arg0[0] - arg1[0];
			}
		});
		for (int i = 0; i < n; i++) {
			int e = info[i][1];
			update(0, 1000000, 1, e, query(0, 1000000, 1, e, 1000000) + 1);
		}
		System.out.println(segT[1]);
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
