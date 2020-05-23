
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class rblock {
	private static int[][] info;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int fields = Integer.parseInt(read.nextToken());
		int paths = Integer.parseInt(read.nextToken());
		List<Integer>[] neighbors = new List[fields];
		info = new int[fields][fields];
		for (int i = 0; i < paths; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int cost = Integer.parseInt(read.nextToken());
			info[a][b] = cost;
			info[b][a] = cost;
			if (neighbors[a] == null)
				neighbors[a] = new ArrayList<Integer>();
			if (neighbors[b] == null)
				neighbors[b] = new ArrayList<Integer>();
			neighbors[a].add(b);
			neighbors[b].add(a);
		}
		List<Integer>[] answer = new List[fields];
		int[] check = new int[fields];
		boolean[] done = new boolean[fields];
		PriorityQueue<thing> queue = new PriorityQueue<thing>();
		answer[0] = new ArrayList<Integer>();
		answer[0].add(0);
		queue.add(new thing(0, 0));
		while (!queue.isEmpty()) {
			thing curr = queue.poll();
			int position = curr.position;
			int cost = curr.cost;
			if (position == fields - 1) {
				break;
			}
			done[position] = true;
			for (int a : neighbors[position]) {
				if (!done[a]) {
					int tempCost = cost + info[position][a];
					if (check[a] != 0) {
						if (check[a] < tempCost)
							continue;
						queue.remove(new thing(a, check[a]));
					}
					queue.add(new thing(a, cost + info[position][a]));
					check[a] = tempCost;
					answer[a] = new ArrayList<Integer>();
					answer[a].addAll(answer[position]);
					answer[a].add(a);

				}
			}
		}
		int oldMax = check[fields - 1];
		int max = 0;
		for (int i = 0; i < answer[fields - 1].size() - 1; i++) {
			info[answer[fields - 1].get(i)][answer[fields - 1].get(i + 1)] *= 2;
			check = new int[fields];
			done = new boolean[fields];
			queue = new PriorityQueue<thing>();
			queue.add(new thing(0, 0));
			while (!queue.isEmpty()) {
				thing curr = queue.poll();
				int position = curr.position;
				int cost = curr.cost;
				if (position == fields - 1) {
					break;
				}
				done[position] = true;
				for (int a : neighbors[position]) {
					if (!done[a]) {
						int tempCost = cost + info[position][a];
						if (check[a] != 0) {
							if (check[a] < tempCost)
								continue;
							queue.remove(new thing(a, check[a]));
						}
						queue.add(new thing(a, cost + info[position][a]));
						check[a] = tempCost;

					}
				}
			}
			max = Math.max(check[fields - 1], max);
			info[answer[fields - 1].get(i)][answer[fields - 1].get(i + 1)] /= 2;
		}
		out.println(max-oldMax);
		in.close();
		out.close();
	}

	private static class thing implements Comparable<thing> {
		int cost;
		int position;

		private thing(int position, int cost) {
			this.cost = cost;
			this.position = position;
		}

		@Override
		public int compareTo(thing o) {
			return cost - o.cost;
		}

	}

}