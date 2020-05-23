
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class mroute {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mroute.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mroute.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int junctions = Integer.parseInt(read.nextToken());
		int pipes = Integer.parseInt(read.nextToken());
		double milk = Integer.parseInt(read.nextToken());
		int[][] info = new int[pipes][pipes];
		int[][] capacities = new int[pipes][pipes];
		List<Integer>[] neighbors = new List[junctions];
		TreeSet<Integer> possiblemMinCapacity = new TreeSet<Integer>();
		for (int i = 0; i < pipes; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int latency = Integer.parseInt(read.nextToken());
			int capacity = Integer.parseInt(read.nextToken());
			info[a][b] = latency;
			capacities[a][b] = capacity;
			info[b][a] = latency;
			capacities[b][a] = capacity;
			if (neighbors[a] == null)
				neighbors[a] = new ArrayList<Integer>();
			if (neighbors[b] == null)
				neighbors[b] = new ArrayList<Integer>();
			neighbors[a].add(b);
			neighbors[b].add(a);
			possiblemMinCapacity.add(capacity);
		}
		double min = 1000000000;
		for (int minCapacity : possiblemMinCapacity) {
			PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}

			});

			// int[] save = new int[junctions];
			boolean[] done = new boolean[junctions];
			queue.add(new int[] { 0, 0 });
			int now = 1000000000;
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				int poss = curr[0];
				int cost = curr[1];
				if (done[poss])
					continue;
				done[poss] = true;
				if (poss == junctions - 1) {
					now=cost;
					break;
				}
				for (int next : neighbors[poss]) {
					if (capacities[poss][next] < minCapacity)
						continue;
					if (done[next])
						continue;
					queue.add(new int[] { next, cost + info[poss][next] });
				}
			}
			min=Math.min((now+milk/minCapacity), min);
		}
		out.print((int)(Math.floor(min)));
		in.close();
		out.close();
	}
}