import java.io.*;
import java.util.*;

public class walk {
	static boolean[][] done;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("walk.in"));
		PrintWriter out = new PrintWriter(new PrintWriter("walk.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		done = new boolean[n][n];
		q.add(new int[] { 2019201997 - 84 * ((n - 2) + 1) - 48 * ((n - 1) + 1), n - 2, n - 1 });
		done[n - 2][n - 1] = true;

		int[] disjoint = new int[n];
		for (int i = 0; i < n; i++)
			disjoint[i] = i;
		int[] size = new int[n];
		int groupsC = n;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[2] - 1 != curr[1] && !done[curr[1]][curr[2] - 1]) {
				q.add(new int[] { curr[0] + 48, curr[1], curr[2] - 1 });
				done[curr[1]][curr[2] - 1] = true;

			}
			if (curr[1] - 1 != -1 && !done[curr[1] - 1][curr[2]]) {
				q.add(new int[] { curr[0] + 84, curr[1] - 1, curr[2] });
				done[curr[1] - 1][curr[2]] = true;
			}
			int parentA = curr[1];
			while (disjoint[parentA] != parentA) {
				parentA = disjoint[parentA];
			}
			int a = curr[1];
			while (disjoint[a] != a) {
				int temp = a;
				a = disjoint[a];
				disjoint[temp] = parentA;
			}
			int parentB = curr[2];
			while (disjoint[parentB] != parentB) {
				parentB = disjoint[parentB];
			}
			int b = curr[2];
			while (disjoint[b] != b) {
				int temp = b;
				b = disjoint[b];
				disjoint[temp] = parentB;
			}
			if (parentA != parentB) {
				if (groupsC == k) {
					out.println(curr[0]);
					break;
				}
				groupsC--;
				if (size[parentB] > size[parentA]) {
					size[parentB] += size[parentA];
					disjoint[parentA] = parentB;
				} else {
					size[parentA] += size[parentB];
					disjoint[parentB] = parentA;
				}
			}
		}
		in.close();
		out.close();
	}
}
