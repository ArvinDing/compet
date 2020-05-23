
import java.io.*;
import java.util.*;

public class roadLib {

	static long roadsAndLibraries(int n, long c_lib, long c_road, List<Integer>[] info) {
		if (c_lib < c_road)
			return n * c_lib;
		boolean[] done = new boolean[n];

		long cost = 0;
		for (int i = 0; i < n; i++) {
			if (!done[i]) {
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(i);
				long group = 0;
				done[i] = true;
				while (!queue.isEmpty()) {
					int curr = queue.poll();
					group++;
					if (info[curr] == null)
						continue;
					for (int neighbor : info[curr]) {
						if (!done[neighbor]) {
							queue.add(neighbor);
							done[neighbor] = true;
						}
					}
				}
				cost += c_lib + (c_road * (group - 1));
			}
		}
		return cost;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int c_lib = in.nextInt();
			int c_road = in.nextInt();
			@SuppressWarnings("unchecked")
			List<Integer>[] info = new List[n];
			for (int i = 0; i < m; i++) {
				int a = in.nextInt() - 1;
				int b = in.nextInt() - 1;
				if (info[a] == null)
					info[a] = new ArrayList<Integer>();
				if (info[b] == null)
					info[b] = new ArrayList<Integer>();
				info[a].add(b);
				info[b].add(a);
			}
			long result = roadsAndLibraries(n, c_lib, c_road, info);
			System.out.println(result);
		}
		in.close();
	}
}
