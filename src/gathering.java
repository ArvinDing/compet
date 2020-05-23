import java.io.*;
import java.util.*;

public class gathering {
	static LinkedList<Integer>[] neigh, req;
	static int previ[];

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("gathering.in"));
		PrintWriter out = new PrintWriter(new File("gathering.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		last = new boolean[n];
		neigh = new LinkedList[n];
		parent = new boolean[n];
		already = new TreeSet[n];
		directChild = new int[n];
		previ = new int[n];
		for (int i = 0; i < n; i++) {
			already[i] = new TreeSet<Integer>();
		}
		Arrays.fill(last, true);
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (neigh[a] == null)
				neigh[a] = new LinkedList<Integer>();
			if (neigh[b] == null)
				neigh[b] = new LinkedList<Integer>();
			neigh[a].add(b);
			neigh[b].add(a);
		}

		req = new LinkedList[n];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int bef = Integer.parseInt(read.nextToken()) - 1;
			int aft = Integer.parseInt(read.nextToken()) - 1;
			if (req[aft] == null)
				req[aft] = new LinkedList<Integer>();
			req[aft].add(bef);
		}

		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, -1 });
		while (!queue.isEmpty()) {
			int[] loop = queue.poll();
			int curr = loop[0];
			int prev = loop[1];
			previ[curr] = prev;
			for (int child : neigh[curr]) {
				if (child != prev) {
					queue.add(new int[] { child, curr });
				}
			}
		}
		recursion(0, -1);
		for (int i = 0; i < n; i++) {
			if (last[i])
				out.println(1);
			else
				out.println(0);
		}
		in.close();
		out.close();
	}

	static int[] directChild;
	static boolean[] parent, last;
	static TreeSet<Integer>[] already;

	static void delete(int curr, int prev) {
		if (already[curr].contains(prev))
			return;
		already[curr].add(prev);
		last[curr] = false;
		for (int child : neigh[curr]) {
			if (child != prev) {
				delete(child, curr);
			}
		}
	}

	static void recursion(int curr, int prev) {
		if (req[curr] != null) {
			for (int a : req[curr]) {
				if (parent[a]) {
					delete(a, directChild[a]);
				} else {
					delete(a, previ[a]);
				}
			}
		}
		for (int child : neigh[curr]) {
			if (child != prev) {
				parent[curr] = true;
				directChild[curr] = child;
				recursion(child, curr);
				parent[curr] = false;
			}
		}

	}
}
