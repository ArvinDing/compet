package camp;

import java.io.*;
import java.util.*;

public class countHay {
	private static int[][] mSegmentT;
	private static int[][] tSegmentT;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		mSegmentT = new int[4 * n][2];
		for (int i = 0; i < 4 * n; i++) {
			mSegmentT[i][0] = Integer.MAX_VALUE;
			mSegmentT[i][1] = 0;
		}
		tSegmentT = new int[4 * n][2];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			update(0, n - 1, i, i, Integer.parseInt(read.nextToken()), 0);
		}
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			String temp = read.nextToken();
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			if (temp.equals("M")) {
				// min
				System.out.println(query(0, n - 1, start, end, 0, true));
			} else if (temp.equals("P")) {
				// put
				int amt = Integer.parseInt(read.nextToken());
				update(0, n - 1, start, end, amt, 0);
			} else {
				// total
				System.out.println(query(0, n - 1, start, end, 0, false));
			}
		}
		in.close();
	}

	public static int query(int rStart, int rEnd, int start, int end, int idx, boolean min) {
		if (rStart > end || rEnd < start) {
			if (min)
				return Integer.MAX_VALUE;
			return 0;
		}

		if (start <= rStart && rEnd <= end) {
			if (min)
				return mSegmentT[idx][0] + mSegmentT[idx][1];
			return tSegmentT[idx][0] + tSegmentT[idx][1];
		}
		int mid = (rStart + rEnd) / 2;
		if (min) {
			if (mSegmentT[idx][1] != 0) {
				update(rStart, mid, start, end, mSegmentT[idx][1], idx * 2 + 1);
				update(mid + 1, rEnd, start, end, mSegmentT[idx][1], idx * 2 + 2);
			}
		} else {
			if (tSegmentT[idx][1] != 0) {
				update(rStart, mid, start, end, tSegmentT[idx][1], idx * 2 + 1);
				update(mid + 1, rEnd, start, end, tSegmentT[idx][1], idx * 2 + 2);
			}
		}
		if (min) {
			return Math.min(query(rStart, mid, start, end, idx * 2 + 1, min),
					query(mid + 1, rEnd, start, end, idx * 2 + 2, min));
		} else {
			return query(rStart, mid, start, end, idx * 2 + 1, min)
					+ query(mid + 1, rEnd, start, end, idx * 2 + 2, min);
		}
	}

	public static void update(int rStart, int rEnd, int start, int end, int update, int idx) {
		if (rStart > end || rEnd < start)
			return;

		if (start <= rStart && rEnd <= end) {
			mSegmentT[idx][1] += update;
			tSegmentT[idx][1] += update;
			return;
		}
		// partially contained
		mSegmentT[idx][0] += update;
		tSegmentT[idx][0] += update;
		int mid = (rStart + rEnd) / 2;
		update(rStart, mid, start, end, update, idx * 2 + 1);
		update(mid + 1, rEnd, start, end, update, idx * 2 + 2);
	}
}
