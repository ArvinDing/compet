
import java.io.*;
import java.util.*;

public class snowcow2 {
	static List<Integer>[] neighbor;
	static boolean[][] info;
	static long[] sum;
	static int[] parent;
	static long[] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("snowcow.in"));
		PrintWriter out = new PrintWriter(new File("snowcow.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		neighbor = new ArrayList[n];
		cnt = new long[n];
		for (int i = 0; i < n; i++)
			neighbor[i] = new ArrayList<Integer>();
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			neighbor[a].add(b);
			neighbor[b].add(a);

		}
		parent = new int[n];
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, -1 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			parent[curr[0]] = curr[1];
			for (int a : neighbor[curr[0]]) {
				if (a != curr[1])
					queue.add(new int[] { a, curr[0] });
			}
		}
		info = new boolean[n][100000];
		sum = new long[n];
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			int job = Integer.parseInt(read.nextToken());
			if (job == 1) {
				int snowball = Integer.parseInt(read.nextToken()) - 1;
				int color = Integer.parseInt(read.nextToken()) - 1;
				long old = sum[snowball];
				update(snowball, color);
				long newer = sum[snowball];
				long diff = newer - old;
				int curr = parent[snowball];
				while (curr != -1) {
					sum[curr] += diff;
					curr = parent[curr];
				}
			} else {
				out.println(sum[Integer.parseInt(read.nextToken()) - 1]);
			}
		}
		in.close();
		out.close();
	}

	private static void update(int index, int color) {
		if (!info[index][color]) {
			cnt[index]++;
			info[index][color] = true;
		}
		long sumI = cnt[index];
		for (int a : neighbor[index]) {
			if (a != parent[index]) {
				update(a, color);
				sumI += sum[a];
			}
		}
		sum[index] = sumI;
	}
}
