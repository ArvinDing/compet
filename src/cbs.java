
import java.io.*;
import java.util.*;

public class cbs {
	private static int[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cbs.in"));
		PrintWriter out = new PrintWriter(new File("cbs.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		segT = new int[k][4 * n];
		for (int i = 0; i < k; i++)
			for (int z = 0; z < 4 * n; z++)
				segT[i][z] = Integer.MAX_VALUE;
		int[][] queue = new int[n][k + 1];
		String[] info = new String[k];
		for (int i = 0; i < k; i++) {
			info[i] = in.readLine();
		}
		for (int i = 0; i < n; i++) {
			int[] cnt = new int[k + 1];
			for (int z = 0; z < k; z++) {
				if (info[z].charAt(i) == '(')
					cnt[z] = (i == 0) ? 1 : queue[i - 1][z] + 1;
				else
					cnt[z] = (i == 0) ? -1 : queue[i - 1][z] - 1;
				update(0, n - 1, 1, i, cnt[z], z);
			}
			cnt[k] = i;
			queue[i] = cnt;
		}

		// for (int z = 0; z < k; z++) {
		// for (int i = 0; i < n; i++) {
		// System.out.print((queue[i][z] < 0) ? queue[i][z] + " " : " " + queue[i][z] +
		// " ");
		// }
		// System.out.println();
		// }
		Arrays.sort(queue, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				for (int i = 0; i < arg0.length; i++) {
					if (arg0[i] == arg1[i])
						continue;
					return arg0[i] - arg1[i];
				}
				return 0;
			}
		});
		int i = 0;
		int total = 0;
		while (i < n) {
			int size = 1;
			int start = queue[i][k];
			outer: while (i + 1 < n) {
				for (int z = 0; z < k; z++) {
					if (queue[i][z] != queue[i + 1][z])
						break outer;
				}
				int end = queue[i + 1][k];
				for (int z = 0; z < k; z++) {
					if (queue[i][z] > query(0, n - 1, 1, start, end, z))
						break outer;
				}
				start = end;
				size++;
				i++;
			}
			total += (size * (size - 1)) / 2;
			i++;
		}
		out.println(total);
		in.close();
		out.close();
	}

	static int query(int l, int r, int idx, int lx, int rx, int segTi) {
		if (r < lx || rx < l)
			return Integer.MAX_VALUE;
		if (lx <= l && r <= rx)
			return segT[segTi][idx];
		int mid = (l + r) / 2;
		int res = query(l, mid, idx * 2, lx, rx, segTi);
		int res1 = query(mid + 1, r, idx * 2 + 1, lx, rx, segTi);
		return Math.min(res, res1);
	}

	static void update(int l, int r, int idx, int pos, int value, int segTi) {
		if (r < pos || pos < l)
			return;
		if (l == r) {
			segT[segTi][idx] = value;
			return;
		}
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, pos, value, segTi);
		update(mid + 1, r, idx * 2 + 1, pos, value, segTi);
		segT[segTi][idx] = Math.min(segT[segTi][idx * 2], segT[segTi][idx * 2 + 1]);
	}
}
