import java.io.*;
import java.util.*;

public class ronald {
	private static int n;
	private static boolean[][] connectivity;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("ronald.in"));
		PrintWriter out = new PrintWriter(new File("ronald.out"));
		n = Integer.parseInt(in.readLine());
		int edges = Integer.parseInt(in.readLine());
		connectivity = new boolean[n][n];
		for (int i = 0; i < edges; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			connectivity[a][b] = true;
			connectivity[b][a] = true;
		}
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, -1 });
		mustS = new int[n];
		mustS[0] = -1;
		boolean a = check(queue);
		if (!a) {
			queue = new LinkedList<int[]>();
			queue.add(new int[] { 0, 1 });
			mustS = new int[n];
			mustS[0] = 1;
			a = check(queue);
		}
		out.println(a ? "DA" : "NE");
		out.close();
		in.close();
	}

	private static int[] mustS;

	private static boolean check(int i, int supposedTo, int opposite, LinkedList<int[]> queue) {
		if (mustS[i] == opposite)
			return false;
		else if (mustS[i] == 0) {
			queue.add(new int[] { i, supposedTo });
			mustS[i] = supposedTo;
		}
		return true;

	}

	private static boolean check(LinkedList<int[]> queue) {
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int current = curr[0];
			for (int i = 0; i < n; i++) {
				if (current == i)
					continue;
				boolean imp;
				if (!connectivity[current][i]) {
					if (mustS[current] == 1) {
						imp = check(i, -1, 1, queue);
					} else {
						imp = check(i, 1, -1, queue);
					}
				} else {
					if (mustS[current] == 1) {
						imp = check(i, 1, -1, queue);
					} else {
						imp = check(i, -1, 1, queue);
					}
				}
				if (!imp)
					return false;
			}

		}
		return true;
	}
}
