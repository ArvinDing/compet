
/*
ID: dingarv1
LANG: JAVA
TASK: milk
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class piggyback {

	private static int N;
	private static List<Integer>[] info;

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("piggyback.in"));
		PrintWriter out = new PrintWriter(new FileWriter("piggyback.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int B = Integer.parseInt(read.nextToken());
		int E = Integer.parseInt(read.nextToken());
		int P = Integer.parseInt(read.nextToken());
		N = Integer.parseInt(read.nextToken());
		int M = Integer.parseInt(read.nextToken());
		info = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			info[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			info[start].add(end);
			info[end].add(start);

		}
		// piggyback
		int[] piggyPoint = bfs(N, P);
		int[] bessieCost = bfs(1, B);
		int[] elsieCost = bfs(2, E);
		int least = Integer.MAX_VALUE;
		for (int i = 1; i < N + 1; i++) {
			least = Math.min(least, piggyPoint[i] + bessieCost[i] + elsieCost[i]);
		}

		out.println(least);
		out.close();
		in.close();
	}

	private static int[] bfs(int start, int Ecost) {
		LinkedList<location> queue = new LinkedList<location>();
		queue.add(new location(start, 0));

		int[] cost = new int[N + 1];
		boolean[] done = new boolean[N + 1];
		done[start] = true;
		while (!queue.isEmpty()) {

			location loc = queue.poll();
			int energy = loc.energy;
			int position = loc.position;

			cost[position] = energy;
			for (int a : info[position]) {

				if (!done[a]) {
					queue.add(new location(a, energy + Ecost));
					done[a] = true;
				}
			}
		}
		return cost;
	}

	private static class location implements Comparable {
		int energy, position;

		location(int position, int energy) {
			this.energy = energy;
			this.position = position;

		}

		public int compareTo(Object o) {
			return energy - ((location) o).energy;
		}
	}

}
