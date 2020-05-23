package camp;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class lazyUpdate {
	private static int[][] segmentT;

	public static void main(String[] args) throws IOException {
		int[][] update = new int[5][3];
		int n = update.length;
		update[0] = new int[] { 1, 2, 4 };
		update[1] = new int[] { 4, 8, 4 };
		update[2] = new int[] { 2, 5, 4 };
		update[3] = new int[] { 3, 4, 4 };
		update[4] = new int[] { 10, 16, 4 };
		segmentT = new int[4 * n][2];
		for (int i = 0; i < n; i++) {
			update(0, n, update[i][0], update[i][1], update[i][2], 0);
		}
		System.out.println(query(0,n,1,2,0));
	

	}

	public static int query(int rStart, int rEnd, int start, int end, int idx) {
		if (rStart > end) {
			return -1;
		}
		if (rEnd < start)
			return -1;
		if (start <= rStart && rEnd <= end) {
			return segmentT[idx][0] + segmentT[idx][1];
		}
		int mid = (rStart + rEnd) / 2;
		if (segmentT[idx][1] != 0) {
			update(rStart, mid, start, end, segmentT[idx][1], idx * 2+1);
			update(mid + 1, rEnd, start, end, segmentT[idx][1], idx * 2 + 2);
		}
		return Math.max(query(rStart, mid, start, end, idx * 2+1), query(mid+1, rEnd, start, end, idx * 2 + 2));

	}

	public static void update(int rStart, int rEnd, int start, int end, int update, int idx) {
		if (rStart > end)
			return;
		if (rEnd < start)
			return;
		if (start <= rStart && rEnd <= end) {
			segmentT[idx][1] += update;
			return;
		}
		segmentT[idx][0]+=update;
		int mid = (rStart + rEnd) / 2;
		update(rStart, mid, start, end, update, idx * 2+1);
		update(mid + 1, rEnd, start, end, update, idx * 2 + 2);
	}
}