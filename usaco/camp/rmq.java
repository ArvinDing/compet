package camp;

import java.io.*;
import java.util.*;

public class rmq {
	private static int[] segT;

	public static void main(String[] args) {
		int n = 10;
		int[] random = new int[n];

		segT = new int[4 * n];
		// Random rand = new Random();
		// for (int i = 0; i < n; i++)
		// random[i] = rand.nextInt(1000000000);
		random = new int[] { 11, 7, 18, 9, 15, 12, 11, 9, 12, 10 };

		int[] bitL = new int[n + 1];
		int small = 1;
		for (int i = 1; i <= n; i++) {
			if (i >= small * 2)
				small *= 2;
			bitL[i] = small;
		}

		boolean[] points = new boolean[n + 1];
		int add = 1;
		while (add  <= n) {
			points[add] = true;
			add = add << 1;
		}
		int[] logBase2 = new int[n + 1];
		int sum = 0;
		for (int i = 0; i <= n; i++) {
			logBase2[i] = sum;
			if (points[i])
				sum++;
		}
		int layers = logBase2[n];
		int[][] info = new int[layers + 1][n];
		for (int i = 0; i < n; i++) {
			info[0][i] = random[i];
		}
		int gap = 1;
		for (int i = 1; i < layers + 1; i++) {
			for (int k = 0; k < n - gap; k++) {
				info[i][k] = Math.min(info[i - 1][k], info[i - 1][k + gap]);
			}
			gap *= 2;
		}
		for (int i = 0; i < layers + 1; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(info[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int k = i; k < n; k++) {
				int[] curr = new int[] { i, k };
				int size = bitL[curr[1] - curr[0] + 1];
				int imp = logBase2[size];
				System.out.println("Query " + curr[0] + " " + curr[1]);
				System.out.println(Math.max(info[imp][curr[0]], info[imp][curr[1] - size + 1]));
			}
		}

	}

	private static void update(int l, int r, int idx, int i, int value) {
		if (i > r || i < l)
			return;
		if (l <= i && i <= r)
			segT[idx] = Math.max(segT[idx], value);
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, i, value);
		update(mid + 1, r, idx * 2 + 1, i, value);
	}

	private static int query(int l, int r, int idx, int lx, int rx) {
		if (lx > r || rx < l)
			return 0;
		if (lx <= l && r <= rx)
			return segT[idx];
		int mid = (l + r) / 2;
		int res = query(l, mid, idx * 2, lx, rx);
		int res1 = query(mid + 1, r, idx * 2 + 1, lx, rx);
		return Math.max(res, res1);
	}
}
