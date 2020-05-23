package camp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class countHay1 {
	private static long[] mSegmentT;
	private static long[] tSegmentT;
	private static long[] lazy;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new File("haybales.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		mSegmentT = new long[4 * n];
		tSegmentT = new long[4 * n];
		lazy = new long[4 * n];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			update(0, n - 1, i, i, Integer.parseInt(read.nextToken()), 1);
		}
	//	debug(0,n-1,1);
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			String temp = read.nextToken();
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			if (temp.equals("M")) {
				// min
				out.println(query(0, n - 1, start, end, 1)[1]);
			} else if (temp.equals("P")) {
				// put
				int amt = Integer.parseInt(read.nextToken());
				update(0, n - 1, start, end, amt, 1);
			} else {
				// total
				out.println(query(0, n - 1, start, end, 1)[0]);
			}
		//	System.out.println();
	//		debug(0,n-1,1);
		}
		out.close();
		in.close();
	}

	public static void debug(int l, int r, int idx) {
		System.out.println(idx + " " + l + " " + r + " " + lazy[idx] + " " + mSegmentT[idx]);
		if (l == r)
			return;
		int mid = (l + r) / 2;
		debug(l, mid, idx * 2);
		debug(mid + 1, r, idx * 2 + 1);
	}

	public static long[] query(int l, int r, int lx, int rx, int idx) {
		// 1-total,2-min
		if (r < lx || rx < l) {
			long[] temp = new long[] { 0, Integer.MAX_VALUE };
			return temp;
		}
		if (lx <= l && r <= rx) {
			long[] temp = new long[] { tSegmentT[idx] + (r - l + 1) * lazy[idx], mSegmentT[idx] + lazy[idx] };
			return temp;
		}
		int mid = (l + r) / 2;
		lazy[idx * 2] += lazy[idx];
		lazy[idx * 2 + 1] += lazy[idx];
		lazy[idx] = 0;
		long[] rChild = query(l, mid, lx, rx, idx * 2);
		long[] lChild = query(mid + 1, r, lx, rx, idx * 2 + 1);
		mSegmentT[idx] = Math.min(mSegmentT[idx * 2] + lazy[idx * 2], mSegmentT[idx * 2 + 1] + lazy[idx * 2 + 1]);
		tSegmentT[idx] = tSegmentT[idx * 2] + lazy[idx * 2] * (mid - l + 1) + tSegmentT[idx * 2 + 1]
				+ lazy[idx * 2 + 1] * (r - mid);
		return new long[] { rChild[0] + lChild[0], Math.min(rChild[1], lChild[1]) };
	}

	public static void update(int l, int r, int lx, int rx, long value, int idx) {
		//System.out.println("*"+idx + " " + l + " " + r + " " + lazy[idx] + " " + mSegmentT[idx]);
		if (r < lx || rx < l) {
			return;
		}
		if (lx <= l && r <= rx) {
			lazy[idx] += value;
		//	System.out.println("-"+idx + " " + l + " " + r + " " + lazy[idx] + " " + mSegmentT[idx]);
			return;
		}
		lazy[idx * 2] += lazy[idx];
		lazy[idx * 2 + 1] += lazy[idx];
		lazy[idx] = 0;
		int mid = (l + r) / 2;
		update(l, mid, lx, rx, value, idx * 2);
		update(mid + 1, r, lx, rx, value, idx * 2 + 1);
		mSegmentT[idx] = Math.min(mSegmentT[idx * 2] + lazy[idx * 2], mSegmentT[idx * 2 + 1] + lazy[idx * 2 + 1]);
		tSegmentT[idx] = tSegmentT[idx * 2] + lazy[idx * 2] * (mid - l + 1) + tSegmentT[idx * 2 + 1]
				+ lazy[idx * 2 + 1] * (r - mid);
	}
}
