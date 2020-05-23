package save;
import java.io.*;
import java.util.*;

public class faketour {
	private static boolean[] done;
	private static LinkedList<Integer> order;
	private static LinkedList<Integer>[] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int idx = 0; idx < test; idx++) {
			int n = Integer.parseInt(in.readLine());
			info = new LinkedList[n];
			LinkedList<Integer>[] reversed = new LinkedList[n];
			done = new boolean[n];
			for (int i = 0; i < n; i++) {
				StringTokenizer read = new StringTokenizer(in.readLine());
				int size = Integer.parseInt(read.nextToken());
				reversed[i] = new LinkedList<Integer>();
				for (int k = 0; k < size; k++) {
					int save = Integer.parseInt(read.nextToken()) - 1;
					if (info[save] == null) {
						info[save] = new LinkedList<Integer>();
					}
					info[save].add(i);
					reversed[i].add(save);
				}
			}
			order = new LinkedList<Integer>();
			int[] scc = new int[n];
			int[] sizeScc = new int[n];
			for (int i = 0; i < n; i++) {
				if (!done[i])
					recursion(i);
			}
			Arrays.fill(scc, -1);
			while (!order.isEmpty()) {
				int last = order.pollLast();
				if (scc[last] != -1)
					continue;
				LinkedList<Integer> bfs = new LinkedList<Integer>();
				bfs.add(last);
				scc[last] = last;
				while (!bfs.isEmpty()) {
					int curr = bfs.poll();
					sizeScc[last]++;
					if (info[curr] != null)
						for (int neighbor : reversed[curr]) {
							if (scc[neighbor] == -1) {
								scc[neighbor] = last;
								bfs.add(neighbor);
							}
						}
				}
			}
			int[] cnt = new int[n];
			for (int i = 0; i < n; i++) {
				if (info[i] != null)
					for (int k : info[i]) {
						if (scc[i] != scc[k])
							cnt[scc[k]]++;

					}
			}
			int overAll = 0;
			int index = -1;
			for (int i = 0; i < n; i++) {
				if (cnt[i] == 0 && sizeScc[i] != 0) {
					overAll++;
					index = i;
				}
			}
			if (overAll == 1) {
				System.out.println(sizeScc[index]);
			} else {
				System.out.println(0);
			}
		}
		in.close();

	}

	private static void recursion(int index) {
		done[index] = true;
		if (info[index] != null)
			for (int neighbor : info[index]) {
				if (!done[neighbor])
					recursion(neighbor);
			}
		order.add(index);
	}
}