
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class poklon {
	private static int[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		long start = System.currentTimeMillis();
		int n = Integer.parseInt(in.readLine());
		int maxV = 1000000;
		int[][] info = new int[n][2];
		segT = new int[4 * maxV][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = new int[] { Integer.parseInt(read.nextToken()) - 1, Integer.parseInt(read.nextToken()) - 1 };
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o2[1] - o1[1];
				return o1[0] - o2[0];
			}
		});
		int max = 1;
		int maxI = 0;
		int[] best = new int[n];
		for (int i = 0; i < n; i++) {
			int e = info[i][1];
			int[] imp = query(0, maxV - 1, 1, e, maxV - 1);
			best[i] = imp[1];
			if (imp[0] + 1 > max) {
				max = imp[0] + 1;
				maxI = i;
			}
			update(0, maxV - 1, 1, e, new int[] { imp[0] + 1, i });
		}
		System.out.println(max);
		Stack<int[]> save = new Stack<int[]>();
		while (maxI != -1) {
			save.push(info[maxI]);
			maxI = best[maxI];
		}
		while (!save.isEmpty()) {
			int[] a = save.pop();
			System.out.println((a[0] + 1) + " " + (a[1] + 1));
		}
		in.close();
	}

	static int[] query(int l, int r, int idx, int lx, int rx) {
		if (r < lx || rx < l)
			return new int[] { 0, -1 };
		if (lx <= l && r <= rx)
			return segT[idx];
		int mid = (l + r) / 2;
		int[] res = query(l, mid, idx * 2, lx, rx);
		int[] res1 = query(mid + 1, r, idx * 2 + 1, lx, rx);
		if (res[0] < res1[0])
			return res1;
		return res;
	}

	static void update(int l, int r, int idx, int pos, int[] value) {
		if (r < pos || pos < l)
			return;
		if (value[0] > segT[idx][0])
			segT[idx] = new int[] { value[0], value[1] };
		if (l == r)
			return;
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, pos, value);
		update(mid + 1, r, idx * 2 + 1, pos, value);
	}
}
