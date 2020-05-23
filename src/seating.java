
import java.io.*;
import java.util.*;

public class seating {
	private static int[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("seating.in"));
		PrintWriter out = new PrintWriter(new File("seating.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		segT = new int[4 * n][4];
		// prefix,suffix,max,lazy
		for (int i = 0; i < 4 * n; i++) {
			segT[i][3] = -1;
		}
		update(0, n - 1, 1, 0, n - 1, 0);
		int turnedAway = 0;
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			if (read.nextToken().equals("A")) {
				int size = Integer.parseInt(read.nextToken());
				int imp = query(0, n - 1, 1, size);
				if (imp == -1)
					turnedAway++;
				else {
					update(0, n - 1, 1, imp, imp + size - 1, 1);
				//	System.out.println("i-"+segT[1][2]);
				}
			} else {
				int s = Integer.parseInt(read.nextToken()) - 1;
				int e = Integer.parseInt(read.nextToken()) - 1;
				update(0, n - 1, 1, s, e, 0);
		//		System.out.println("l-"+segT[1][2]);
			}
		}
		out.println(turnedAway);
		out.close();
		in.close();
	}

	private static int query(int l, int r, int idx, int size) {
		if (segT[idx][2] < size)
			return -1;
		int mid = (l + r) / 2;
		if (l != r && segT[idx][3] != -1) {
			update(l, mid, idx * 2, l, mid, segT[idx][3]);
			update(mid + 1, r, idx * 2 + 1, mid + 1, r, segT[idx][3]);
			segT[idx][3] = -1;
		}
		int lC = query(l, mid, idx * 2, size);
		if (lC != -1)
			return lC;
		if (segT[idx * 2][1] + segT[idx * 2 + 1][0] >= size)
			return mid - segT[idx * 2][1] + 1;
		return query(mid + 1, r, idx * 2 + 1, size);
	}

	private static void update(int l, int r, int idx, int lx, int rx, int sit) {
		// sit= 0-getup,1- sit
		if (rx < l || r < lx)
			return;
		if (lx <= l && r <= rx) {
			int value = (sit == 0) ? r - l + 1 : 0;
			segT[idx][0] = value;
			segT[idx][1] = value;
			segT[idx][2] = value;
			segT[idx][3] = sit;
			return;

		}
		int mid = (l + r) / 2;
		if (l != r && segT[idx][3] != -1) {
			update(l, mid, idx * 2, l, mid, segT[idx][3]);
			update(mid + 1, r, idx * 2 + 1, mid + 1, r, segT[idx][3]);
			segT[idx][3] = -1;
		}
		update(l, mid, idx * 2, lx, rx, sit);
		update(mid + 1, r, idx * 2 + 1, lx, rx, sit);
		int[] lC = segT[idx * 2];
		int[] rC = segT[idx * 2 + 1];
		segT[idx][0] = (lC[0] == mid - l + 1) ? lC[0] + rC[0] : lC[0];
		segT[idx][1] = (rC[1] == r - (mid + 1) + 1) ? lC[1] + rC[1] : rC[1];
		segT[idx][2] = Math.max(lC[1] + rC[0], Math.max(lC[2], rC[2]));
	}
}
