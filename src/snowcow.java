
import java.io.*;
import java.util.*;

public class snowcow {
	static List<Integer>[] neighbor;
	static int[] parent;
	static int[] fIndex;// inclusive
	static int[] lIndex;// exclusive
	// static int[] dfsOrd;
	static long[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("snowcow.in"));
		PrintWriter out = new PrintWriter(new File("snowcow.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		neighbor = new ArrayList[n];
		for (int i = 0; i < n; i++)
			neighbor[i] = new ArrayList<Integer>();
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			neighbor[a].add(b);
			neighbor[b].add(a);

		}
		// parent = new int[n];
		// LinkedList<int[]> queue = new LinkedList<int[]>();
		// queue.add(new int[] { 0, -1 });
		// while (!queue.isEmpty()) {
		// int[] curr = queue.poll();
		// parent[curr[0]] = curr[1];
		// for (int a : neighbor[curr[0]]) {
		// if (a != curr[1])
		// queue.add(new int[] { a, curr[0] });
		// }
		// }
		fIndex = new int[n];
		lIndex = new int[n];
		// dfsOrd = new int[n];
		segT = new long[4 * n][2];
		done = new boolean[n];
		dfs(0, -1);

		PriorityQueue<int[]>[] overlap = new PriorityQueue[100000];
		List<int[]> updates = new ArrayList<int[]>();
		LinkedList<int[]> query = new LinkedList<int[]>();

		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			if (Integer.parseInt(read.nextToken()) == 1) {
				int idx = Integer.parseInt(read.nextToken()) - 1;
				int c = Integer.parseInt(read.nextToken()) - 1;
				updates.add(new int[] { fIndex[idx], lIndex[idx] - 1, c, i });
			} else {
				int idx = Integer.parseInt(read.nextToken()) - 1;
				query.add(new int[] { fIndex[idx], lIndex[idx] - 1, i });
			}
		}

		Collections.sort(updates, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o1[3] - o2[3];
				return o1[0] - o2[0];
			}
		});
		List<int[]>[] newUpdates = new ArrayList[q];
		for (int i = 0; i < q; i++)
			newUpdates[i] = new ArrayList<int[]>();
		for (int[] curr : updates) {
			int start = curr[0];
			int end = curr[1];
			int color = curr[2];
			int idx = curr[3];
			newUpdates[idx].add(new int[] { start, end, 1, idx });
			if (overlap[color] != null) {
				// top surronds curr
				int[] top = overlap[color].peek();
				while (start > top[1] && !overlap[color].isEmpty()) {
					top = overlap[color].remove();

					top = overlap[color].peek();
					if (top == null)
						break;
				}

				if (top != null && start <= top[1]) {
					if (idx < top[3]) {
						newUpdates[top[3]].add(new int[] { start, end, -1 });
					} else {
						newUpdates[idx].add(new int[] { start, end, -1 });
					}

				}
			}

			if (overlap[color] == null)
				overlap[color] = new PriorityQueue<int[]>(new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						return o1[3] - o2[3];
					}
				});
			overlap[color].add(curr);
		}
		for (int i = 0; i < q; i++) {

			for (int[] loop : newUpdates[i]) {
				update(loop[0], loop[1], 0, n - 1, 1, loop[2]);
				// System.out.println(loop[0] + " " + loop[1] + "|" + loop[2]);

			}
			if (!query.isEmpty()) {
				if (i == query.peek()[2]) {
					int[] curr = query.poll();
					long ans = query(curr[0], curr[1], 0, n - 1, 1);
					out.println(ans);
				}
			}
		}

		in.close();
		out.close();

	}

	static void update(int l, int r, int lB, int rB, int idx, long val) {
		if (lB > r || rB < l)
			return;
		if (l <= lB && rB <= r) {
			if (lB == rB) {
				segT[idx][0] += val;
				return;
			}
			segT[idx][1] += val;
			return;
		}
		int mid = (lB + rB) / 2;
		update(l, r, lB, mid, idx * 2, val);
		update(l, r, mid + 1, rB, idx * 2 + 1, val);
		long valL = segT[idx * 2][0] + segT[idx * 2][1] * (mid - lB + 1);
		long valR = segT[idx * 2 + 1][0] + segT[idx * 2 + 1][1] * (rB - (mid + 1) + 1);

		segT[idx][0] = valL + valR;

	}

	static long query(int l, int r, int lB, int rB, int idx) {
		if (lB > r || rB < l)
			return 0;
		if (l <= lB && rB <= r) {
			return segT[idx][0] + segT[idx][1] * (rB - lB + 1);
		}
		int mid = (lB + rB) / 2;
		if (segT[idx][1] != 0) {
			update(lB, mid, lB, rB, idx, segT[idx][1]);
			update(mid + 1, rB, lB, rB, idx, segT[idx][1]);
			segT[idx][1] = 0;
		}

		return query(l, r, lB, mid, idx * 2) + query(l, r, mid + 1, rB, idx * 2 + 1);
	}

	static int index = 0;
	static boolean[] done;

	static void dfs(int curr, int old) {

		// dfsOrd[index] = curr;
		fIndex[curr] = index;
		index++;
		for (int a : neighbor[curr]) {
			if (a != old)
				dfs(a, curr);
		}
		lIndex[curr] = index;
	}

}
