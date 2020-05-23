package camp;

import java.io.*;
import java.util.*;

public class turbo {
	private static int[] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		segT = new int[4 * n];
		int[] pos = new int[n];
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			pos[curr] = i;
		}
		for (int i = 0; i < n; i++) {
			int use = i / 2;
			if (i % 2 == 0) {

				System.out.println((pos[use]) - query(0, n - 1, 1, 0, pos[use]));
				update(0, n - 1, 1, pos[use], 1);
			} else {
				System.out.println((n - 1 - pos[n - 1 - use]) - query(0, n - 1, 1, pos[n - 1 - use], n - 1));
				update(0, n - 1, 1, pos[n - 1 - use], 1);
			}
		}
		in.close();

	}

	private static int query(int l, int r, int idx, int lx, int rx) {
		if (rx < l || r < lx)
			return 0;
		if (lx <= l && r <= rx)
			return segT[idx];
		int mid = (l + r) / 2;
		int res = query(l, mid, idx * 2, lx, rx);
		int res1 = query(mid + 1, r, idx * 2 + 1, lx, rx);
		return res + res1;
	}

	private static void update(int l, int r, int idx, int position, int value) {
		if (l > position || position > r)
			return;
		if (l == r) {
			segT[idx] += value;
			return;
		}
		segT[idx] += value;
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, position, value);
		update(mid + 1, r, idx * 2 + 1, position, value);
		segT[idx] = segT[idx * 2] + segT[idx * 2 + 1];
	}
}
