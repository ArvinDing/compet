package camp;

import java.io.*;
import java.util.*;

public class seating2 {
	private static int[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("seating.in"));
		PrintWriter out = new PrintWriter(new File("seating.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int events = Integer.parseInt(read.nextToken());
		// prefix,suffix,max,lazy
		segT = new int[4 * n][4];
		for (int i = 0; i < 4 * n; i++)
			segT[i][3] = -1;
		update(0, n - 1, 1, 0, n - 1, true);

	}

	private static void update(int l, int r, int idx, int lx, int rx, boolean sit) {
		// 0-false sit,1-true gettup
		if (r <= lx || rx <= l)
			return;
		if (lx <= l && r <= rx) {
			int value = 0;
			if (!sit) {
				value = l - r + 1;
			}
			segT[idx][0] = value;
			segT[idx][1] = value;
			segT[idx][2] = value;
			segT[idx][3] = sit ? 1 : 0;
			return;
		}
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, lx, rx, sit);
		update(mid, r, idx * 2 + 1, lx, rx, sit);
		int[] lC = segT[idx * 2];
		int[] rC = segT[idx * 2 + 1];
		segT[idx][0] = (lC[0] == mid - l + 1) ? lC[0] + rC[0] : lC[0];
		segT[idx][1] = (rC[1] == r - mid + 1) ? lC[1] + rC[1] : rC[1];
		segT[idx][2] = Math.max(lC[1] + rC[0], Math.max(lC[3], rC[3]));

	}

	private static int[] query(int l, int r, int idx, int lx, int rx, int size) {
		// prefix,suffix,earliestIndex
		if (r <= lx || rx <= l)
			return new int[] { 0, 0, -1 };
		int mid = (l + r) / 2;
		if(segT[idx][2]<size)
			return new int [] {0,0,-1};
		if (segT[idx][3] != -1) {
			update(l, mid, idx * 2, lx, rx, segT[idx][3] == 1);
			update(mid + 1, r, idx * 2 + 1, lx, rx, segT[idx][3] == 1);
		}
		if (lx <= l && r <= rx) {
			if(l==r) {
		//		return segT;
			}
			int[] lC = query(l, mid, idx * 2, lx, rx, size);
			if (lC[2] == -1) {
				int[] rC = query(mid + 1, r, idx * 2, lx, rx, size);
				if (lC[1] + rC[0] >= size) {
					return new int[] { lC[0], rC[1], mid - lC[1] };
				}
				return rC;
			} else {
				return lC;
			}
		}

		int[] lC = query(l, mid, idx * 2, lx, rx, size);
		if (lC[2] == -1) {
			int[] rC = query(mid + 1, r, idx * 2, lx, rx, size);
			if (lC[1] + rC[0] >= size) {
				return new int[] { lC[0], rC[1], mid - lC[1] };
			}
			return rC;
		} else {
			return lC;
		}
	}
}
