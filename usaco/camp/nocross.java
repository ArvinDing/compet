package camp;

import java.io.*;
import java.util.*;

public class nocross {
	private static int[] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new File("nocross.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		segT = new int[4 * n];
		int[] r = new int[n];
		for (int i = 0; i < n; i++) {
			r[i] = Integer.parseInt(in.readLine()) - 1;
		}
		int[] posl = new int[n];
		for (int i = 0; i < n; i++) {
			int value = Integer.parseInt(in.readLine());
			posl[value - 1] = i;
		}
		for (int i = 0; i < n; i++) {
			int curr = r[i];
			Integer[] indexes = new Integer[9];
			Arrays.fill(indexes, -1);
			for (int add = 4; add >= -4; add--) {
				if (curr + add < 0 || n <= curr + add)
					continue;
				indexes[add - 4 * -1] = posl[curr + add];
			}
			Arrays.sort(indexes,new Comparator<Integer>() {

				@Override
				public int compare(Integer arg0, Integer arg1) {
					return arg1-arg0;
				}});
			for (int z = 0; z < 9; z++) {
				int leftI = indexes[z];
				if (indexes[z] == -1)
					continue;
				int best = query(0, n - 1, 1, -1, leftI - 1);
				System.out.println(best);
				update(0, n - 1, 1, leftI, best + 1);
			}
		}
		out.println(segT[1]);
		out.close();
		in.close();

	}

	private static int query(int l, int r, int idx, int lx, int rx) {
		if (rx < l || r < lx)
			return 0;
		if (lx <= l && r <= rx)
			return segT[idx];
		int mid = (l + r) / 2;
		int res1 = query(l, mid, idx * 2, lx, rx);
		int res2 = query(mid + 1, r, idx * 2 + 1, lx, rx);
		return Math.max(res1, res2);

	}

	private static void update(int l, int r, int idx, int i, int value) {
		if (i < l || r < i)
			return;
		if (l == r) {
			segT[idx] = value;
			return;
		}
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, i, value);
		update(mid + 1, r, idx * 2 + 1, i, value);
		segT[idx] = Math.max(segT[idx * 2], segT[idx * 2 + 1]);
	}
}
