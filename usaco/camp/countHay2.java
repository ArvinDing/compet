package camp;

import java.io.*;
import java.util.*;

public class countHay2 {
	private static long[][] segT;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		segT = new long[4 * n][3];
		// total,min,lazy
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++)
			update(0, n - 1, i, i, 1, Long.parseLong(read.nextToken()));
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			String curr = read.nextToken();
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			if (curr.equals("M")) {
				System.out.println(query(0, n - 1, start, end, 1)[1]);
			} else if (curr.equals("S")) {
				System.out.println(query(0, n - 1, start, end, 1)[0]);
			} else {
				long val = Long.parseLong(read.nextToken());
				update(0, n - 1, start, end, 1, val);
			}
		}
		in.close();
	}

	private static void update(int l, int r, int lx, int rx, int idx, long value) {
		if (rx < l || lx > r)
			return;
		if (lx <= l && r <= rx) {
			segT[idx][0] += value * (r - l + 1);
			segT[idx][1] += value;
			segT[idx][2] += value;
			return;
		}
		int mid = (l + r) / 2;
		update(l, mid, l, mid, idx * 2, segT[idx][2]);
		update(mid + 1, r, mid + 1, r, idx * 2 + 1, segT[idx][2]);
		segT[idx][2] = 0;
		update(l, mid, lx, rx, idx * 2, value);
		update(mid + 1, r, lx, rx, idx * 2 + 1, value);
		segT[idx][0] = segT[idx * 2][0] + segT[idx * 2 + 1][0];
		segT[idx][1] = Math.min(segT[idx * 2][1], segT[idx * 2 + 1][1]);
	}

	private static long[] query(int l, int r, int lx, int rx, int idx) {
		// total,min
		if (rx < l || lx > r)
			return new long[] { 0, Long.MAX_VALUE / 2 };
		if (lx <= l && r <= rx) {
			return new long[] { segT[idx][0], segT[idx][1] };
		}
		int mid = (l + r) / 2;
		if (segT[idx][2] != 0) {
			update(l, mid, l, mid, idx * 2, segT[idx][2]);
			update(mid + 1, r, mid + 1, r, idx * 2 + 1, segT[idx][2]);
			segT[idx][2] = 0;
		}
		long[] res = query(l, mid, lx, rx, idx * 2);
		long[] res1 = query(mid + 1, r, lx, rx, idx * 2 + 1);

		return new long[] { res[0] + res1[0], Math.min(res[1], res1[1]) };
	}
}
