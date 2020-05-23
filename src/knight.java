
import java.io.*;
import java.util.*;

public class knight {
	static int[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		segT = new int[4 * n][2];
		int[][] info = new int[m][3];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int l = Integer.parseInt(read.nextToken());
			int r = Integer.parseInt(read.nextToken());
			int winner = Integer.parseInt(read.nextToken());
			info[i] = new int[] { l, r, winner };
		}
		for (int i = m - 1; i >= 0; i--) {
			int[] a = info[i];
			a[0]--;
			a[1]--;
			update(0, n - 1, a[0], (a[2] - 1) - 1, a[2], 1);
			update(0, n - 1, (a[2] + 1) - 1, a[1], a[2], 1);
		}
		for (int i = 0; i < n; i++) {
			int a = query(0, n - 1, i, 1);
			if (a -1== i)
				a = 0;
			if (i != 0)
				System.out.print(" " + a);
			else
				System.out.print(a);
		}
	
		in.close();
	}

	static void update(int l, int r, int lx, int rx, int value, int idx) {
		if (r < lx || rx < l) {
			return;
		}
		if (lx <= l && r <= rx) {
			segT[idx][0] = value;
			segT[idx][1] = value;
			return;
		}
		int mid = (l + r) / 2;
		if (segT[idx][1] != 0) {
			update(l, mid, l, mid, segT[idx][1], idx * 2);
			update(mid + 1, r, mid+1, r, segT[idx][1], idx * 2 + 1);
			segT[idx][1] = 0;
		}
		update(l, mid, lx, rx, value, idx * 2);
		update(mid + 1, r, lx, rx, value, idx * 2 + 1);
	}

	static int query(int l, int r, int x, int idx) {
		if (x < l || x > r) {
			return -1;
		}
		if (l == r)
			return segT[idx][0];
		int mid = (l + r) / 2;
		if (segT[idx][1] != 0) {
			update(l, mid, l, mid, segT[idx][1], idx * 2);
			update(mid + 1, r, mid + 1, r, segT[idx][1], idx * 2 + 1);
			segT[idx][1] = 0;
		}
		return Math.max(query(l, mid, x, idx * 2), query(mid + 1, r, x, idx * 2 + 1));
	}
}
