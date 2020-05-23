  import java.io.*;
import java.util.*;

public class contention {
	static thing[] segT;
	static int[] intervals;
	static final int big = 100000;
	static boolean[] done;
	static Set<Integer> updated;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for (int i = 0; i < t; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(read.nextToken());
			int q = Integer.parseInt(read.nextToken());
			segT = new thing[4 * n];
			for (int z = 0; z < 4 * n; z++) {
				segT[z] = new thing(0, 0, new HashSet<Integer>());
			}
			intervals = new int[q];
			int[][] info = new int[q][2];
			for (int z = 0; z < q; z++) {
				read = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(read.nextToken()) - 1;
				int b = Integer.parseInt(read.nextToken()) - 1;
				set(1, 0, n - 1, a, b, z);
				info[z] = new int[] { a, b };
			}

			PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o2[1] - o1[1];
				}

			});
			int ans = Integer.MAX_VALUE;
			updated = new HashSet<Integer>();
			done = new boolean[4 * n];
			fix(1, 0, n - 1, -1);
			for (int z = 0; z < q; z++) {
				queue.add(new int[] { z, intervals[z] });
			}
			boolean[] doneIntervals = new boolean[n];
			for (int z = 0; z < q; z++) {
				int[] a = new int[] { -1, -1 };
				while (!queue.isEmpty()) {
					a = queue.poll();
					if (!doneIntervals[a[0]])
						break;
				}
				int[] gap = info[a[0]];
				remove(1, 0, n - 1, gap[0], gap[1], a[0]);
				ans = Math.min(ans, intervals[a[0]]);
				fix(1, 0, n - 1, -1);
				for (int p : updated) {
					queue.add(new int[] { p, intervals[p] });
				}
			}
			System.out.println("Case #" + (i + 1) + ": " + ans);
		}

		in.close();
	}

	public static void set(int idx, int start, int end, int uStart, int uEnd, int interval) {
		if (uEnd < start || uStart > end) {
			return;
		}
		if (start == end) {
			segT[idx].value += 1;
			return;
		}
		if (uStart <= start && end <= uEnd) {
			segT[idx].lazy += 1;
			segT[idx].list.add(interval);
			return;
		}

		segT[idx * 2].lazy += segT[idx].lazy;
		segT[idx * 2 + 1].lazy += segT[idx].lazy;
		segT[idx].lazy = 0;
		set(idx * 2, start, ((start + end) / 2), uStart, uEnd, interval);
		set(idx * 2 + 1, ((start + end) / 2) + 1, end, uStart, uEnd, interval);
		int layers = segT[idx * 2].value + segT[idx * 2].lazy;
		int layers1 = segT[idx * 2 + 1].value + segT[idx * 2 + 1].lazy;
		segT[idx].value = Math.min(layers, layers1);
	}

	// addUpdate
	public static void remove(int idx, int start, int end, int uStart, int uEnd, int interval) {
		if (uEnd < start || uStart > end) {
			return;
		}
		if (start == end) {
			segT[idx].value--;
			return;
		}
		if (uStart <= start && end <= uEnd) {
			segT[idx].lazy+=-1;
			segT[idx].list.remove(interval);
			return;
		}

		segT[idx * 2].lazy += segT[idx].lazy;
		segT[idx * 2 + 1].lazy += segT[idx].lazy;
		segT[idx].lazy = 0;

		remove(idx * 2, start, ((start + end) / 2), uStart, uEnd, interval);
		remove(idx * 2 + 1, ((start + end) / 2) + 1, end, uStart, uEnd, interval);
		int layers = segT[idx * 2].value + segT[idx * 2].lazy;
		int layers1 = segT[idx * 2 + 1].value + segT[idx * 2 + 1].lazy;
	
		segT[idx].value = Math.min(layers,layers1);

	}

	public static void fix(int idx, int start, int end, int remain) {
		if (segT[idx].value != 1)
			return;
		if (done[idx])
			return;
		if (start == end) {
			intervals[remain]++;
			done[idx] = true;
			updated.add(remain);
			return;
		}
		if (!segT[idx].list.isEmpty())
			remain = segT[idx].list.iterator().next();
		segT[idx * 2].lazy += segT[idx].lazy;
		segT[idx * 2 + 1].lazy += segT[idx].lazy;
		segT[idx].lazy = 0;
		fix(idx * 2, start, ((start + end) / 2), remain);
		fix(idx * 2 + 1, ((start + end) / 2) + 1, end, remain);
		if (done[idx * 2] && done[idx * 2 + 1])
			done[idx] = true;
		int layers = segT[idx * 2].value + segT[idx * 2].lazy;
		int layers1 = segT[idx * 2 + 1].value + segT[idx * 2 + 1].lazy;
		segT[idx].value = Math.min(layers,layers1);

	}

	public static class thing {
		int value;
		int lazy;
		Set<Integer> list;

		public thing(int value, int lazy, Set<Integer> list) {
			this.value = value;
			this.lazy = lazy;
			this.list = list;

		}
	}
}
