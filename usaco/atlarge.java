
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class atlarge {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter out = new PrintWriter(new File("atlarge.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int barns = Integer.parseInt(read.nextToken());
		int surface = Integer.parseInt(read.nextToken()) - 1;
		List<Integer>[] neighbors = new List[barns];
		for (int i = 0; i < barns; i++) {
			neighbors[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < barns - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			neighbors[a].add(b);
			neighbors[b].add(a);
		}
		List<Integer> exits = new ArrayList<Integer>();
		for (int i = 0; i < barns; i++) {
			if (neighbors[i].size() == 1) {
				exits.add(i);
			}
		}
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { surface, -1, 0 });
		int[] bessie = new int[barns];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			bessie[curr[0]] = curr[2];
			for (int neighborsCurr : neighbors[curr[0]]) {
				if (neighborsCurr != curr[1]) {
					queue.add(new int[] { neighborsCurr, curr[0], curr[2] + 1 });
				}
			}
		}
		TreeMap<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
		for (int exit : exits) {
			if (!map.containsKey(bessie[exit]))
				map.put(bessie[exit], new ArrayList<Integer>());
			map.get(bessie[exit]).add(exit);
		}
		int cnt = 0;
		boolean[] covered = new boolean[barns];
		for (List<Integer> loop : map.values()) {
			for (int check : loop) {
				if (!covered[check]) {
					cnt++;
					int[] times = new int[barns];
					queue = new LinkedList<int[]>();
					queue.add(new int[] { check, -1, 0 });
					while (!queue.isEmpty()) {
						int[] curr = queue.poll();
						times[curr[0]] = curr[2];
						for (int neighborsCurr : neighbors[curr[0]]) {
							if (neighborsCurr != curr[1]) {
								queue.add(new int[] { neighborsCurr, curr[0], curr[2] + 1 });
							}
						}
					}
					for (int exit : exits) {
						if (times[exit] <= bessie[exit]) {
							covered[exit] = true;
						}
					}
				}
			}
		}
		out.println(cnt);
		in.close();
		out.close();
	}

}
