package camp;

import java.io.*;
import java.util.*;

public class optmilk {
	private static long[][][] segT;
	private static long[] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("optmilk.in"));
		PrintWriter out = new PrintWriter(new File("optmilk.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int d = Integer.parseInt(read.nextToken());
		segT = new long[4 * n][2][2];
		info = new long[n];

		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(in.readLine());
			update(i, 0, n - 1, 1);
		}
	//	print(0, n - 1, 1);
		long sum = 0;
		for (int i = 0; i < d; i++) {
			read = new StringTokenizer(in.readLine());
			int updateI = Integer.parseInt(read.nextToken()) - 1;
			info[updateI] = Integer.parseInt(read.nextToken());
			update(updateI, 0, n - 1, 1);
			sum += Math.max(Math.max(segT[1][0][0], segT[1][1][1]), Math.max(segT[1][1][0], segT[1][0][1]));
		}
		out.println(sum);
		in.close();
		out.close();
	}

	private static void print(int l, int r, int idx) {

		System.out.println(idx + "* " + l + " " + r + "|" + segT[idx][0][0] + " " + segT[idx][0][1] + " "
				+ segT[idx][1][0] + " " + segT[idx][1][1]);
		if (l == r)
			return;
		int mid = (l + r) / 2;
		print(l, mid, idx * 2);
		print(mid + 1, r, idx * 2 + 1);
	}

	private static void update(int cIdx, int l, int r, int idx) {
		
		if (cIdx < l || r < cIdx)
			return;
		if (l == r) {
			segT[idx][1][1] = info[cIdx];
			return;
		}
		int mid = (l + r) / 2;
		update(cIdx, l, mid, idx * 2);
		update(cIdx, mid + 1, r, idx * 2 + 1);

		int lC = idx * 2;
		int rC = idx * 2 + 1;
	
		segT[idx][0][0] = Math.max(segT[lC][0][1] + segT[rC][0][0],
				Math.max(segT[lC][0][0] + segT[rC][1][0], segT[lC][0][0] + segT[rC][0][0]));
		segT[idx][0][1] = Math.max(segT[lC][0][1] + segT[rC][0][1],
				Math.max(segT[lC][0][0] + segT[rC][1][1], segT[lC][0][0] + segT[rC][0][1]));
		segT[idx][1][0] = Math.max(segT[lC][1][1] + segT[rC][0][0],
				Math.max(segT[lC][1][0] + segT[rC][1][0], segT[lC][1][0] + segT[rC][0][0]));
		segT[idx][1][1] = Math.max(segT[lC][1][1] + segT[rC][0][1],
				Math.max(segT[lC][1][0] + segT[rC][1][1], segT[lC][1][0] + segT[rC][0][1]));
	}
}