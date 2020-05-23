package camp;

import java.io.*;
import java.util.*;

public class centriodDecomp {
	private static List<Integer>[] info;
	private static boolean[] dUse;
	private static int n;
	private static boolean[] para;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		n = Integer.parseInt(in.readLine());
		info = new List[n];
		para = new boolean[n];
		for (int i = 1; i < n; i++) {
			int parent = Integer.parseInt(in.readLine()) - 1;
			if (info[parent] == null)
				info[parent] = new ArrayList<Integer>();
			if (info[i] == null)
				info[i] = new ArrayList<Integer>();
			info[parent].add(i);
			info[i].add(parent);
		}
		for (int i = 0; i < n; i++) {
			para[i] = in.readLine().equals("(");
		}
		dUse = new boolean[n];
		System.out.println(centriod(0, n));
		in.close();
	}

	private static int[] sum;
	private static int[] start;
	private static int[] end;

	private static int solve(int idx) {

		boolean[] finished = new boolean[n];
		Queue<int[]> solve = new LinkedList<int[]>();
		start = new int[2 * n];
		end = new int[2 * n];
		// could be negative
		int ans = 0;
		List<Integer> saveS = new ArrayList<Integer>();
		List<Integer> saveE = new ArrayList<Integer>();
		TreeMap<Integer, Integer> ends = new TreeMap<Integer, Integer>();
		for (int neighbor : info[idx]) {
			if (dUse[neighbor])
				continue;
			// curr,val,min,max
			if (para[neighbor]) {
				solve.add(new int[] { neighbor, 1, 0, 0 });
			} else {
				solve.add(new int[] { neighbor, -1, 0, 0 });
			}
			while (!solve.isEmpty()) {
				int[] a = solve.poll();
				finished[a[0]] = true;
				for (int next : info[idx]) {
					if (dUse[next] || finished[next])
						continue;
					int cnt = a[1];
					if (para[next])
						cnt++;
					else
						cnt--;
					if (a[2] >= cnt) {
						// possible end
						if (para[idx])
							ans = (ans + (start[n - (cnt + 1)])) % 1000000007;
						else
							ans = (ans + (start[n - (cnt - 1)])) % 1000000007;

						saveE.add(cnt + n);

					}
					if (a[3] <= cnt) {
						// possible start
						if (para[idx])
							ans = (ans + (end[n - (cnt + 1)])) % 1000000007;
						else
							ans = (ans + (end[n - (cnt - 1)])) % 1000000007;
						saveS.add(cnt + n);
					}
					solve.add(new int[] { next, cnt, Math.min(cnt, a[2]), Math.max(a[3], cnt) });
				}
			}
			for (int s : saveS)
				start[s]++;
			for (int e : saveE)
				end[e]++;
		}
		if (para[idx])
			ans += end[n - 1];
		else
			ans += start[n + 1];
		return ans;

	}

	private static int centriod(int root, int size) {
		sum = new int[n];
		sumDfs(root, -1);
		int idx = root;
		int prev = -1;
		while (true) {
			int max = 0;
			int maxI = -1;
			for (int neighbor : info[idx]) {
				if (dUse[neighbor] || neighbor == prev)
					continue;
				if (sum[neighbor] > max) {
					max = sum[neighbor];
					maxI = neighbor;
				}
			}
			if (max <= size / 2)
				break;
			prev = idx;
			idx = maxI;
		}
		dUse[idx] = true;
		LinkedList<int[]> queue = new LinkedList<int[]>();
		for (int neighbor : info[idx]) {
			if (dUse[neighbor])
				continue;
			if (sum[neighbor] > sum[idx]) {
				sum[neighbor] = sum[root] - sum[idx] + 1;
			}
			queue.add(new int[] { neighbor, sum[neighbor] });
		}
		int total = solve(idx);
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			total += centriod(curr[0], curr[1]);
		}
		return total;
	}

	private static void sumDfs(int curr, int prev) {
		sum[curr] = 1;
		for (int neighbor : info[curr]) {
			if (!dUse[neighbor] && neighbor != prev) {
				sumDfs(neighbor, curr);
				sum[curr] += sum[neighbor];
			}
		}

	}
}
