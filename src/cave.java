import java.io.*;
import java.util.*;

public class cave {
	static final int mod = (int) 1e9 + 7;
	static int n, m;
	static boolean[][] info, done;

	static final int maxN = 500000;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cave.in"));
		PrintWriter out = new PrintWriter(new PrintWriter("cave.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		m = Integer.parseInt(read.nextToken());
		info = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			for (int j = 0; j < m; j++) {
				info[i][j] = (line.charAt(j) == '.');
			}
		}
		long ans = 1;
		idxs = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				idxs[i][j] = -1;
		neigh = new HashSet[maxN];
		tree = new HashSet[maxN];

		disjoint = new int[maxN];
		parent = new int[maxN];

		exist = new boolean[maxN];
		isChild = new boolean[maxN];
		for (int i = 0; i < maxN; i++) {
			disjoint[i] = i;
			tree[i] = new HashSet<Integer>();
			neigh[i] = new HashSet<Integer>();
			parent[i] = -1;
		}
		idx = 0;
		done = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!done[i][j] && info[i][j]) {
					ans = ans * calc(i, j) % mod;
				}
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}

	static int[][] idxs;
	static HashSet<Integer>[] neigh, tree;
	static int idx;
	static int[] disjoint, parent;
	static long totalT = 0;
	static boolean[] exist, isChild;

	static int[] find(int i, int j) {
		int start = j;
		while (start >= 0 && info[i][start])
			start--;
		int end = j;
		while (end != m && info[i][end])
			end++;
		return new int[] { start + 1, end };

	}

	static void fill(int i, int j, int index) {
		int[] temp = find(i, j);
		for (int z = temp[0]; z < temp[1]; z++)
			idxs[i][z] = index;
	}

	static void print(int[][] in) {
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[0].length; j++) {
				if (9 >= in[i][j] && in[i][j] >= 0) {
					System.out.print(" " + in[i][j]);
				} else {
					System.out.print(in[i][j]);
				}
			}
			System.out.println();
		}
	}

	static long calc(int startI, int startK) {
		LinkedList<int[]> queue = new LinkedList<int[]>();
		int firstIdx = idx;
		queue.add(new int[] { startI, startK, idx });
		fill(startI, startK, idx);
		idx++;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int[] temp = find(curr[0], curr[1]);
			for (int i = temp[0]; i < temp[1]; i++) {
				done[curr[0]][i] = true;
				if (info[curr[0] + 1][i]) {
					if (idxs[curr[0] + 1][i] == -1) {
						queue.add(new int[] { curr[0] + 1, i, idx });
						fill(curr[0] + 1, i, idx);
						neigh[curr[2]].add(idx);
						isChild[idx] = true;
						idx++;
					} else {
						neigh[curr[2]].add(idxs[curr[0] + 1][i]);
						isChild[idxs[curr[0] + 1][i]] = true;
					}
				}
				if (info[curr[0] - 1][i]) {
					if (idxs[curr[0] - 1][i] == -1) {
						queue.add(new int[] { curr[0] - 1, i, idx });
						fill(curr[0] - 1, i, idx);
						idx++;
					}
				}
			}

		}
		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i = firstIdx; i < idx; i++) {
			if (!isChild[i])
				q.add(i);
		}
		while (!q.isEmpty()) {
			int cIdx = q.poll();
			for (int child : neigh[cIdx]) {
				if (parent[child] != -1) {
					int a = parent[child];
					int b = cIdx;
					int parA = getPar(a);
					int parB = getPar(b);
					while (parA != parB) {

						disjoint[parA] = parB;
						System.out.println(a + " " + b);
						//one node can have multiple parents
						a = parent[a];
						b = parent[b];
						if (a == -1 || b == -1)
							break;

						parA = getPar(a);
						parB = getPar(b);
					}
				} else {
					parent[child] = cIdx;
					q.add(child);
				}
			}
		}
		HashSet<Integer> test = new HashSet<Integer>();
		// print(idxs);
		for (int i = firstIdx; i < idx; i++) {
			int actual = getPar(i);
			for (int curr : neigh[i]) {
				tree[actual].add(getPar(curr));
				test.add(getPar(curr));
			}
		}
		int cnt = 0;
		for (int i = firstIdx; i < idx; i++) {
			int actual = getPar(i);
			if (!test.contains(actual)) {
				test.add(actual);
				cnt++;
			}
		}
		if (cnt > 1)
			System.out.println("wat");
		return ways(getPar(firstIdx));
	}

	static long ways(int idx) {
		long prod = 1;
		for (int child : tree[idx]) {
			prod = (prod * ways(child)) % mod;
		}
		return (1 + prod) % mod;
	}

	static int getPar(int idx) {
		int curr = idx;
		while (disjoint[curr] != curr) {
			curr = disjoint[curr];
		}
		int trueTop = curr;
		curr = idx;
		while (disjoint[curr] != curr) {
			int old = disjoint[curr];
			disjoint[curr] = trueTop;
			curr = old;
		}
		return trueTop;
	}
}
