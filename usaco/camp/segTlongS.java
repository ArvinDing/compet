package camp;

import java.io.*;
import java.util.*;

public class segTlongS {
	private static int[][] segT;
	private static int maxN = 1000000000;
	private static int saveI = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer read = new StringTokenizer(in.readLine());

		segT = new int[2000000][5];
		segT[saveI][0] = 0;
		segT[saveI][1] = maxN;
		saveI++;
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(read.nextToken());
			int max = query(1, 0, curr - 1);
			System.out.println(max);
			update(1, curr, max + 1);
		}
		System.out.println(segT[1][2]);

	}

	private static void update(int idx, int i, int value) {
		if (idx == 0)
			return;
		int l = segT[idx][0];
		int r = segT[idx][1];
		if (i < l || r <i)
			return;

		if (l == r) {
			segT[idx][2] = value;
			return;
		}
		int mid = (l + r) / 2;
		if (i <= mid && segT[idx][3] == 0) {
			segT[idx][3] = saveI;
			segT[saveI][0] = l;
			segT[saveI][1] = mid;
			saveI++;
		}
		int lC = segT[idx][3];
		if (i >= mid + 1 && segT[idx][4] == 0) {
			segT[idx][4] = saveI;
			segT[saveI][0] = mid + 1;
			segT[saveI][1] = r;
			saveI++;
		}

		int rC = segT[idx][4];

		update(lC, i, value);
		update(rC, i, value);
		segT[idx][2] = Math.max(segT[lC][2], segT[rC][2]);
	}

	private static int query(int idx, int lX, int rX) {
		int l = segT[idx][0];
		int r = segT[idx][1];
		if (rX < l || lX > r)
			return 0;
		if (lX <= l && r <= rX)
			return segT[idx][2];
		int res1 = (segT[idx][3] == 0) ? 0 : query(segT[idx][3], lX, rX);
		int res2 = (segT[idx][4] == 0) ? 0 : query(segT[idx][4], lX, rX);
		return Math.max(res1, res2);
	}
}
