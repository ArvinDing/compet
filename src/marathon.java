

import java.io.*;
import java.util.*;

public class marathon {
	private static int[] infoT;
	private static int[] skipT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new File("marathon.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		int[][] info = new int[n][2];
		infoT = new int[4 * n];
		skipT = new int[4 * n];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		for (int i = 0; i < n - 1; i++) {
			update(0, n - 1, i, 1, dist(info[i], info[i + 1]));
		}
		for (int i = 0; i < n - 2; i++) {
			update1(0, n - 1, i, 1,
					dist(info[i], info[i + 1]) + dist(info[i + 1], info[i + 2]) - dist(info[i], info[i + 2]));
		}
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());

			if (read.nextToken().equals("Q")) {
				int start = Integer.parseInt(read.nextToken()) - 1;
				int end = Integer.parseInt(read.nextToken()) - 1;
				int distance = query(0, n - 1, start, end - 1, 1);
				int skipped = query1(0, n - 1, start, end - 2, 1);
		
				out.println(distance - skipped);
			} else {
				int idx = Integer.parseInt(read.nextToken()) - 1;
				int[] old = info[idx];
				info[idx] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) };
				if (idx + 1 < n) {
					update(0, n - 1, idx, 1, -dist(old, info[idx + 1]) + dist(info[idx], info[idx + 1]));
				}
				if (idx + 2 < n) {

					update1(0, n - 1, idx, 1, dist(info[idx], info[idx + 1]) + dist(info[idx + 1], info[idx + 2])
							- dist(info[idx], info[idx + 2]));
				}
				if (idx != 0) {
					if (idx + 1 < n)
						update1(0, n - 1, idx - 1, 1, dist(info[idx - 1], info[idx]) + dist(info[idx], info[idx + 1])
								- dist(info[idx - 1], info[idx + 1]));
					update(0, n - 1, idx - 1, 1, -dist(old, info[idx - 1]) + dist(info[idx], info[idx - 1]));
				}
				if (idx >= 2) {
					update1(0, n - 1, idx - 2, 1, dist(info[idx - 2], info[idx - 1]) + dist(info[idx - 1], info[idx])
							- dist(info[idx - 2], info[idx]));
				}
			}
		}
		in.close();
		out.close();

	}

	private static int dist(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}

	private static int query(int l, int r, int lx, int rx, int idx) {

		if (lx > r || rx < l)
			return 0;
		if (lx <= l && r <= rx) {
			return infoT[idx];
		}
		int mid = (l + r) / 2;
		int left = query(l, mid, lx, rx, idx * 2);
		int right = query(mid + 1, r, lx, rx, idx * 2 + 1);
		return left + right;

	}

	private static int query1(int l, int r, int lx, int rx, int idx) {
		if (lx > r || rx < l)
			return Integer.MIN_VALUE;
		if (lx <= l && r <= rx) {
			return skipT[idx];
		}
		int mid = (l + r) / 2;
		int left = query1(l, mid, lx, rx, idx * 2);
		int right = query1(mid + 1, r, lx, rx, idx * 2 + 1);
		return Math.max(right, left);

	}

	private static void update(int l, int r, int x, int idx, int value) {

		if (x < l || r < x)
			return;
		infoT[idx] += value;
		if (l == r)
			return;
		int mid = (l + r) / 2;
		update(l, mid, x, idx * 2, value);
		update(mid + 1, r, x, idx * 2 + 1, value);

	}

	private static void update1(int l, int r, int x, int idx, int value) {
		if (x < l || r < x)
			return;

		if (l == r) {
			skipT[idx] = value;
			return;
		}

		int mid = (l + r) / 2;
		update1(l, mid, x, idx * 2, value);
		update1(mid + 1, r, x, idx * 2 + 1, value);
		skipT[idx] = Math.max(skipT[idx * 2], skipT[idx * 2 + 1]);

	}

}
