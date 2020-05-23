package camp;

import java.io.*;
import java.util.*;

public class maxflow {
	private static int[][] edges;
	private static int[] start;
	private static boolean[] visited;
	private static int idx = 0;
	private static int[] path;
	private static int[] change;
	private static int[] nick;
	private static int n;
	private static int[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("maxflow.in"));
		PrintWriter out = new PrintWriter(new File("maxflow.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		edges = new int[2 * n - 2][2];
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			edges[i][0] = Integer.parseInt(read.nextToken()) - 1;
			edges[i][1] = Integer.parseInt(read.nextToken()) - 1;
			edges[n - 1 + i][0] = edges[i][1];
			edges[n - 1 + i][1] = edges[i][0];
		}
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0] == arg1[0])
					return arg0[1] - arg1[1];
				return arg0[0] - arg1[0];
			}
		});
		start = new int[n];
		for (int i = edges.length - 1; i >= 0; i--) {
			start[edges[i][0]] = i;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		nick = new int[n];
		change = new int[n];
		// nick[0]=0
		visited = new boolean[n];
		int[] parent = new int[n];
		parent[0] = -1;
		int name = 0;
		visited[0] = true;
		queue.add(0);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int startE = start[curr];
			nick[curr] = name;
			change[name] = curr;
			name++;

			for (int i = startE; i < edges.length; i++) {
				if (edges[i][0] != curr)
					break;
				int neigh = edges[i][1];
				if (!visited[neigh]) {
					queue.add(neigh);
					visited[neigh] = true;
					parent[neigh] = curr;
				}
			}
		}

		visited = new boolean[n];
		path = new int[2 * n - 1];
		dfs(0);
		preCompute(n);
		int[] firstTime = new int[n];
		Arrays.fill(firstTime, -1);
		for (int i = 0; i < 2 * n - 1; i++) {
			if (firstTime[path[i]] == -1)
				firstTime[path[i]] = i;
		}
		int layers = logBase2[path.length];
		int[][] info = new int[layers + 1][path.length];
		for (int i = 0; i < path.length; i++) {
			info[0][i] = path[i];
		}

		int gap = 1;
		for (int i = 1; i < layers + 1; i++) {
			for (int k = 0; k < path.length - gap; k++) {
				info[i][k] = Math.min(info[i - 1][k], info[i - 1][k + gap]);
			}
			gap *= 2;
		}
		ans = new int[n];
		// query
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			int[] curr = new int[] { firstTime[nick[start]], firstTime[nick[end]] };
			Arrays.sort(curr);
			int size = bitL[curr[1] - curr[0] + 1];
			int imp = logBase2[size];
			int lca = Math.min(info[imp][curr[0]], info[imp][curr[1] - size + 1]);
			int real = change[lca];
			if (parent[real] != -1)
				ans[parent[real]]--;
			ans[real]--;
			ans[start]++;
			ans[end]++;
		}
		visited = new boolean[2 * n];
		max = 0;
		dfs1(0);
		out.println(max);
		in.close();
		out.close();

	}

	private static int[] bitL;
	private static int[] logBase2;
	private static int max;

	private static int dfs1(int curr) {
		int startE = start[curr];
		visited[curr] = true;
		int sum = ans[curr];
		for (int i = startE; i < edges.length; i++) {
			if (edges[i][0] != curr)
				break;
			int neigh = edges[i][1];
			if (!visited[neigh]) {
				sum += dfs1(neigh);
			}
		}
		max = Math.max(max, sum);
		return sum;
	}

	private static void dfs(int curr) {
		int startE = start[curr];
		visited[curr] = true;
		path[idx++] = nick[curr];
		for (int i = startE; i < edges.length; i++) {
			if (edges[i][0] != curr)
				break;
			int neigh = edges[i][1];
			if (!visited[neigh]) {
				dfs(neigh);
				path[idx++] = nick[curr];
			}
		}
	}

	private static void preCompute(int n) {
		// 2^floor(log2(n))
		bitL = new int[2 * n + 1];
		int temp = 1;
		for (int i = 1; i <= 2 * n; i++) {
			if (i >= temp * 2)
				temp *= 2;
			bitL[i] = temp;
		}
		boolean[] points = new boolean[2 * n + 1];
		int add = 1;
		while (add <= 2 * n) {
			points[add] = true;
			add = add << 1;
		}
		logBase2 = new int[2 * n + 1];
		int sum = 0;
		for (int i = 0; i <= 2 * n; i++) {
			logBase2[i] = sum;
			if (points[i])
				sum++;
		}
	}

}
