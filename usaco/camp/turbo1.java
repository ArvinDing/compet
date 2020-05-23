package camp;

import java.io.*;
import java.util.*;

public class turbo1 {
	private static int[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		segT = new int[4 * n][2];
		int[] pos = new int[n];
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			update(0, n - 1, 1, i, i, i);
			pos[curr] = i;
		}

		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				int changed = query(0, n - 1, 1, pos[i / 2]);
				System.out.println( (changed - i / 2));
				update(0, n - 1, 1, 0, changed, 1);
			} else {
				int newPos = (n - 1) - i / 2;
				int changed = query(0, n - 1, 1, pos[newPos]);
				System.out.println(((newPos) - changed));
				update(0, n - 1, 1, changed,n-1, -1);
			}
		}
		in.close();
	}

	private static int query(int l, int r, int idx, int i) {
		if (i < l || r < i)
			return 0;
		if (l == r)
			return segT[idx][0] + segT[idx][1];
		int mid = (l + r) / 2;
		if (segT[idx][1] != 0) {
			update(l, mid, idx * 2, i, i, segT[idx][1]);
			update(mid + 1, r, idx * 2 + 1, i, i, segT[idx][1]);
		}
		int res = query(l, mid, idx * 2, i);
		int res1 = query(mid + 1, r, idx * 2 + 1, i);
		return res + res1;
	}

	private static void update(int l, int r, int idx, int lx, int rx, int value) {
		if (r < lx || rx < l)
			return;
		if (lx <= l && r <= rx) {
			segT[idx][1] += value;
			return;
		}
		segT[idx][0] += value;
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, lx, rx, value);
		update(mid + 1, r, idx * 2 + 1, lx, rx, value);
	}
}
