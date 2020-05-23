import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class buffet {
	private static int[][] distance;
	private static int[] dp;
	private static patch[] info;
	private static int cost;
	private static patch[] sorted;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("buffet.in"));
		PrintWriter out = new PrintWriter(new File("buffet.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		cost = Integer.parseInt(read.nextToken());
		info = new patch[lines];
		sorted = new patch[lines];
		dp = new int[lines];
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int quality = Integer.parseInt(read.nextToken());
			int neighborsCnt = Integer.parseInt(read.nextToken());
			List<Integer> currNeighbors = new ArrayList<Integer>();
			for (int k = 0; k < neighborsCnt; k++) {
				currNeighbors.add(Integer.parseInt(read.nextToken()) - 1);
			}
			info[i] = new patch(quality, currNeighbors, 0);
			sorted[i] = new patch(quality, currNeighbors, i);
		}

		distance = new int[lines][lines];

		for (int i = 0; i < lines; i++) {
			PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});
			queue.add(new int[] { i, 0 });
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				if (curr[0] != i && distance[i][curr[0]] != 0)
					continue;
				distance[i][curr[0]] = curr[1];
				for (int a : info[curr[0]].neighbors) {
					if (a == i || distance[i][a] != 0)
						continue;
					queue.add(new int[] { a, curr[1] + cost });
				}
			}
		}
		Arrays.sort(sorted);
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < lines; i++) {
			max = Math.max(max, recursion(i));
		}
		out.print(max);
		in.close();
		out.close();
	}

	private static int recursion(int position) {
		if (dp[position] != 0)
			return dp[position];
		int max = sorted[position].quality;

		// System.out.println(index);
		for (int i = 0; i < position; i++) {
			if (sorted[i].quality == sorted[position].quality)
				break;
			if (distance[sorted[position].index][sorted[i].index] == 0)
				continue;
			max = Math.max(max,
					-distance[sorted[position].index][sorted[i].index] + recursion(i) + sorted[position].quality);
		}
		dp[position] = max;
		return max;

	}

	private static class patch implements Comparable<patch> {
		int quality;
		List<Integer> neighbors;
		int index;

		private patch(int quality, List<Integer> neighbors, int index) {
			this.quality = quality;
			this.neighbors = neighbors;
			this.index = index;
		}

		@Override
		public int compareTo(patch o) {
			return quality - o.quality;
		}

	}
}