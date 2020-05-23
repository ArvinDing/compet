package save;
import java.io.*;
import java.util.*;

public class steeple {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("steeple.in"));
		PrintWriter out = new PrintWriter(new File("steeple.out"));
		int n = Integer.parseInt(in.readLine());
		List<int[]> vertical = new ArrayList<int[]>();
		List<int[]> horizontal = new ArrayList<int[]>();
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			int x1 = Integer.parseInt(read.nextToken());
			int y1 = Integer.parseInt(read.nextToken());
			if (y == y1) {
				if (x < x1)
					horizontal.add(new int[] { x, y, x1, y1 });
				else
					horizontal.add(new int[] { x1, y, x, y1 });
			}
			if (x == x1) {
				if (y < y1)
					vertical.add(new int[] { x, y, x1, y1 });
				else
					vertical.add(new int[] { x, y1, x1, y });
			}
		}
		int[][] connection = new int[n + 2][n + 2];
		for (int i = 0; i < vertical.size(); i++) {
			connection[0][i + 1] = 1;
		}
		int verts = vertical.size();
		int i = 0;
		for (int[] a : vertical) {
			int k = 0;
			for (int[] b : horizontal) {
				if (overlap(a, b)) {
					connection[i + 1][verts + 1 + k] = 1;
				}
				k++;
			}
			i++;
		}
		for (int z = verts + 1; z < connection.length; z++) {
			connection[z][n + 1] = 1;
		}
		out.println(n - maxFlow(connection));
		out.close();
		in.close();
	}

	private static int maxFlow(int[][] connection) {
		int sum = 0;
		while (true) {
			boolean[] visited = new boolean[connection.length];
			LinkedList<Integer> queue = new LinkedList<Integer>();
			int[] parent = new int[connection.length];
			queue.add(0);
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				visited[curr] = true;
				if (curr == connection.length - 1)
					break;
				for (int i = 0; i < connection.length; i++) {
					if (!visited[i] && connection[curr][i] != 0) {
						parent[i] = curr;
						queue.add(i);
					}
				}
			}
			if (!visited[connection.length - 1])
				break;
			int curr = connection.length - 1;
			int capacity = Integer.MAX_VALUE;
			while (curr != 0) {
				capacity = Math.min(capacity, connection[parent[curr]][curr]);
				curr = parent[curr]; 
			}
			curr = connection.length - 1;
			while (curr != 0) {
				connection[parent[curr]][curr] -= capacity;
				connection[curr][parent[curr]] -= capacity;
				curr = parent[curr];
			}
			sum += capacity;
		}
		return sum;

	}

	private static boolean overlap(int[] v, int[] h) {
		return (h[0] <= v[0] && v[0] <= h[2]) && (v[1] <= h[1] && h[1] <= v[3]);
	}

}
