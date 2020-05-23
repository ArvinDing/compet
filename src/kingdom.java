
import java.io.*;
import java.util.*;

public class kingdom {
	static int[] below, parent;
	static List<Integer>[] neigh;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		neigh = new ArrayList[n];
		for (int i = 0; i < n-1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (neigh[a] == null)
				neigh[a] = new ArrayList<Integer>();
			if (neigh[b] == null)
				neigh[b] = new ArrayList<Integer>();
			neigh[a].add(b);
			neigh[b].add(a);
		}
		parent = new int[n];
		int[] above = new int[n];
		below = new int[n];

		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, 0 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			above[curr[0]] = curr[1];
			for (int a : neigh[curr[0]]) {
				if (a != parent[curr[0]]) {
					queue.add(new int[] { a, curr[1] + 1 });
					parent[a] = curr[0];
				}
			}
		}
		sumB(0);
		long ans = 0;
		List<Integer> best = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			best.add(above[i] - (below[i] - 1));
		}
		Collections.sort(best,Collections.reverseOrder());
		for (int i = 0; i < k; i++) {
			ans += best.get(i);
		}
		System.out.print(ans);
		in.close();
	}

	static int sumB(int index) {
		int sum = 1;
		for (int a : neigh[index]) {
			if (a != parent[index]) {
				sum += sumB(a);
			}
		}
		below[index] = sum;
		return sum;
	}
}
