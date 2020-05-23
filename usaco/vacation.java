
import java.io.*;
import java.util.*;

public class vacation {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("vacation.in"));
		PrintWriter out = new PrintWriter(new File("vacation.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int farms = Integer.parseInt(read.nextToken());
		int lines = Integer.parseInt(read.nextToken());
		int hubs = Integer.parseInt(read.nextToken());
		int requests = Integer.parseInt(read.nextToken());
		int[][] costs = new int[farms][farms];

		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			int cost = Integer.parseInt(read.nextToken());
			if (costs[start][end] == 0)
				costs[start][end] = cost;
			else
				costs[start][end] = Math.min(costs[start][end], cost);
		}
		for (int i = 0; i < hubs; i++) {
			PriorityQueue<thing> queue = new PriorityQueue<thing>();
			boolean[] done = new boolean[farms];
			queue.add(new thing(i, 0));
			while (!queue.isEmpty()) {
				thing curr = queue.poll();
				int position = curr.position;
				int value = curr.value;
				if (done[position])
					continue;
				done[position] = true;
				costs[position][i] = value;
				for (int k = 0; k < farms; k++) {
					if (costs[k][position] != 0) {
						queue.add(new thing(k, value + costs[k][position]));
					}
				}
			}
			queue = new PriorityQueue<thing>();
			done = new boolean[farms];
			queue.add(new thing(i, 0));
			while (!queue.isEmpty()) {
				thing curr = queue.poll();
				int position = curr.position;
				int value = curr.value;
				if (done[position])
					continue;
				done[position] = true;
				costs[i][position] = value;
				for (int k = 0; k < farms; k++) {
					if (costs[position][k] != 0) {
						queue.add(new thing(k, value + costs[position][k]));
					}
				}
			}
		}
		int possibleTrips = 0;
		long routeCost = 0;
		for (int i = 0; i < requests; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
		
				boolean flag = false;
				int min = Integer.MAX_VALUE;
				for (int k = 0; k < hubs; k++) {
					if ((start == k || costs[start][k] != 0) && (end == k || costs[k][end] != 0)) {
						min = Math.min(costs[start][k] + costs[k][end], min);
						flag = true;
					}
				}
				if (flag) {
					possibleTrips++;
					routeCost += min;
				}

			
		}
		out.println(possibleTrips);
		out.println(routeCost);
		in.close();
		out.close();

	}

	private static class thing implements Comparable<thing> {
		int position;
		int value;

		private thing(int position, int value) {
			this.position = position;
			this.value = value;
		}

		@Override
		public int compareTo(thing o) {
			return value - o.value;
		}

	}

}
