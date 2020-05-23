

import java.io.*;
import java.util.*;

public class relativnost {
	private static int[][] segT;
	private static int totalC;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("relativnost.in"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		totalC = Integer.parseInt(read.nextToken());
		segT = new int[4 * n][totalC + 1];
		// (0-(c))= ways| c+1=all
		read = new StringTokenizer(in.readLine());
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(read.nextToken());
		}
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(read.nextToken());
		}
		for (int i = 0; i < n; i++) {
			update(0, n - 1, totalC, 1, i, a[i], b[i]);
		}
		int q = Integer.parseInt(in.readLine());
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			int p = Integer.parseInt(read.nextToken()) - 1;
			int aP = Integer.parseInt(read.nextToken());
			int bP = Integer.parseInt(read.nextToken());
			update(0, n - 1, totalC, 1, p, aP, bP);
			// All - (i=0->c-1)f(0,n-1,c)
			System.out.println(segT[1][totalC]);
		}
		in.close();

	}

	private static void update(int l, int r, int c, int idx, int i, int a, int b) {
		if (c > (r - l + 1))
			return;
		if (l > i || r < i)
			return;
		if (l == r) {
			segT[idx][0] = b;
			segT[idx][1] = a;
			return;
		}
		int add = 0;
		int mid = (l + r) / 2;
		if (c == totalC) {
			for (int first = 0; first <= c; first++) {
				update(l, mid, first, idx * 2, i, a, b);
				update(mid + 1, r, first, idx * 2 + 1, i, a, b);
			}
			for (int first = 0; first <= c; first++) {
				for (int second = c; second + first >= c; second--) {
					add += (segT[idx * 2][first] * segT[idx * 2 + 1][second]) % 10007;
				}
			}
		} else {
			for (int first = 0; first <= c; first++) {
				update(l, mid, first, idx * 2, i, a, b);
				update(mid + 1, r, c - first, idx * 2 + 1, i, a, b);
				add += (segT[idx * 2][first] * segT[idx * 2 + 1][c - first]) % 10007;
			}
		}
		segT[idx][c] = ( add) % 10007;

	}
}
