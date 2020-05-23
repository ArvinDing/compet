
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class route2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("route.in"));
		PrintWriter out = new PrintWriter(new File("route.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int l = Integer.parseInt(read.nextToken());
		int r = Integer.parseInt(read.nextToken());
		int route = Integer.parseInt(read.nextToken());
		int[] left = new int[l];
		int[] right = new int[r];
		for (int i = 0; i < l; i++)
			left[i] = Integer.parseInt(in.readLine());
		for (int i = 0; i < r; i++)
			right[i] = Integer.parseInt(in.readLine());
		int[][] info = new int[route * 2][3];
		for (int i = 0; i < route; i++) {
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			info[i] = new int[] { a, b, 0 };
			info[i + route] = new int[] { b, a, 1 };
		}
		int[] rSegT = new int[r * 4];
		int[] lSegT = new int[l * 4];

		Arrays.sort(info, new Comparator<int[]>() {

			@Override 
			public int compare(int[] arg0, int[] arg1) {
				return arg1[0] - arg0[0];
			}
		});
		int max = 0;
		for (int i = 0; i < route * 2; i++) {
			int[] curr = info[i];
			if (curr[2] == 0) {
				int value = left[i] + query(0, r - 1, 1, curr[1], r - 1, rSegT);
				update(0, l - 1, 1, curr[0], value, lSegT);
				max = Math.max(max, value);
			} else {
				int value = right[i] + query(0, l - 1, 1, curr[1], l - 1, lSegT);
				update(0, r - 1, 1, curr[0], value, rSegT);
				max = Math.max(max, value);
			}
		}
		out.println(max);
		out.close();
		in.close();
	}

	static void update(int l, int r, int idx, int index, int value, int[] segT) {
		if (r < index || index < l)
			return;
		segT[idx] = Math.max(value, segT[idx]);
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, index, value, segT);
		update(mid + 1, r, idx * 2 + 1, index, value, segT);

	}

	static int query(int l, int r, int idx, int lx, int rx, int[] segT) {
		if (r < lx || rx < l)
			return 0;
		if (lx <= l && r <= rx)
			return segT[idx];
		int mid = (l + r) / 2;
		int res = query(l, mid, idx * 2, lx, rx, segT);
		int res1 = query(mid + 1, r, idx * 2 + 1, lx, rx, segT);
		return Math.max(res, res1);
	}

}
