package camp;

import java.io.*;
import java.util.*;

public class longInSub {
	private static int[] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[] info = new int[n];
		segT = new int[2 * 1000000];

		update(0, 1000000 - 1, 1, 0, 0);
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(read.nextToken());
			int max = query(0, 1000000 - 1, 0, curr - 1, 1);
			System.out.println(max);
			update(0, 1000000 - 1, 1, curr, max + 1);
		}
		System.out.println(segT[1]);

	}

	

	private static void update(int l, int r, int idx, int i, int value) {
		if (i < l || i > r)
			return;
		if (l == r) {
			segT[idx] = Math.max(segT[idx], value);
			return;
		}
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, i, value);
		update(mid + 1, r, idx * 2 + 1, i, value);
		segT[idx] = Math.max(segT[idx * 2], segT[idx * 2 + 1]);
	}

	private static int query(int l, int r, int lX, int rX, int idx) {
		if (rX < l || lX > r)
			return 0;
		if (lX <= l && r <= rX)
			return segT[idx];
		int mid = (l + r) / 2;
		int res1 = query(l, mid, lX, rX, idx * 2);
		int res2 = query(mid + 1, r, lX, rX, idx * 2 + 1);
		return Math.max(res1, res2);
	}
}
