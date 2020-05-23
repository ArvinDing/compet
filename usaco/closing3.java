

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class closing3 {
	private static Set<Integer> [] edges;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		int N, M;
		int [] rOrder;
		try (BufferedReader in = new BufferedReader(new FileReader("closing.in"))) {
			String [] line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			edges = new Set[N];
			int [][] eA = new int[M][2];
			for (int i = 0; i < M; i ++) {
				line = in.readLine().split(" ");
				eA[i][0] = Integer.parseInt(line[0]) - 1;
				eA[i][1] = Integer.parseInt(line[1]) - 1;
			}
			rOrder = new int[N];
			int [] rR = new int[N]; // used for saving edges
			for (int i = 1; i <= N; i ++) {
				rOrder[N - i] = Integer.parseInt(in.readLine()) - 1;
				rR[rOrder[N - i]] = i; // saving edges in vertex with smaller rR values
			}
			addEdge(eA, rR);
		}
		boolean [] ans = new boolean[N];
		DisjointSet dsu = new DisjointSet(N);
		for (int i = 0; i < N; i ++) {
			int idx = rOrder[i];
			dsu.makeSet(idx);
			if (edges[idx] != null) {
				int root = idx;
				for (Iterator<Integer> it = edges[idx].iterator(); it.hasNext(); ) {
					int one = it.next();
					if (dsu.nodeExist(one)) {
						root = dsu.union(root, one);
						it.remove();
					}
				}
			}
			if (dsu.getRoots() == 1) {
				ans[i] = true;
			}
		}
		try (PrintWriter out = new PrintWriter(new FileWriter("closing.out"))) {
			for (int i = 1; i <= N; i ++) {
				out.println(ans[N-i] ? "YES" : "NO");
			}			
		}
	}

	private static void addEdge(int [][] eA, int [] order) {
		for (int [] edge : eA) {
			int v1, v2;// v1 has smaller order value than v2;
			if (order[edge[0]] < order[edge[1]]) {
				v1 = edge[0];
				v2 = edge[1];
			} else {
				v1 = edge[1];
				v2 = edge[0];
			}
			if (edges[v1] == null) {// save in v1
				edges[v1] = new HashSet<Integer>();
			}
			edges[v1].add(v2);
		}
	}
	
	public static class DisjointSet {
		private int roots = 0;
		private int [] [] nodes;
		
		public DisjointSet(int size) {
			nodes = new int[size][];
		}
		
		public boolean nodeExist(int one) {
			return  nodes[one] != null;
		}
		
		public void makeSet(int idx) {
			nodes[idx] = new int [] {idx, 0}; // {parentId, rank}
			roots ++;
		}
		
		public int union(int one, int two) {
			int p1 = find(one), p2 = find(two);
			if (p1 != p2) {// merge
				int root;
				if (nodes[p1][1] == nodes[p2][1]) {
					nodes[p1][1] ++;
					nodes[p2][0] = root = p1;
				} else if (nodes[p1][1] > nodes[p2][1]) {
					nodes[p2][0] = root = p1;
				} else {
					nodes[p1][0] = root = p2;
				}
				roots --;
				return root;
			}
			return p1;
		}

		public int find(int one) {
			int p1, root = one;
			while ((p1 = nodes[root][0]) != root) {// find root
				root = p1;
			}
			while (root != (p1 = nodes[one][0])) {// flatten tree
				nodes[one][0] = root;
				one = p1;
			}
			return root;
		}

		public int getRoots() {
			return roots;
		}
	}
}
