
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowroute2 {
	private static link[][] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowroute.in"));
		PrintWriter out = new PrintWriter(new File("cowroute.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(read.nextToken());
		int end = Integer.parseInt(read.nextToken());
		int routes = Integer.parseInt(read.nextToken());
		info = new link[1001][1001];

		for (int i = 0; i < routes; i++) {
			read = new StringTokenizer(in.readLine());
			int cost = Integer.parseInt(read.nextToken());
			int cities = Integer.parseInt(read.nextToken());
			read = new StringTokenizer(in.readLine());
			int[] city = new int[cities];
			for (int k = 0; k < cities; k++) {
				city[k] = Integer.parseInt(read.nextToken());
			}
			for (int k = 0; k < cities; k++) {
				for (int o = k + 1; o < cities; o++) {
					if (info[city[k]][city[o]] == null || info[city[k]][city[o]].cost > cost) {
						info[city[k]][city[o]] = new link(cost, o - k);
					} else if (info[city[k]][city[o]].cost == cost && o - k < info[city[k]][city[o]].flight) {
						info[city[k]][city[o]] = new link(cost, o - k);
					}

				}
			}
		}
		PriorityQueue<position> queue = new PriorityQueue<position>();
		queue.add(new position(start, 0, 0));
		boolean[] done = new boolean[1001];
		while (true) {

			position a = queue.poll();
			if (a == null) {
				out.println(-1 + " " + -1);
				break;
			}
			long cost = a.cost;
			int city = a.city;
			int flights = a.flights;
			if (city == end) {
				out.println(cost + " " + flights);
				break;
			}
			if (done[city])
				continue;
			done[city] = true;
			for (int i = 1; i < 1001; i++) {
				if (info[city][i] != null) {
					queue.add(new position(i, cost + info[city][i].cost, flights + info[city][i].flight));
				}
			}
		}

		out.close();
		in.close();
	}

	private static class link {
		long cost;
		int flight;

		private link(long cost, int flight) {
			this.cost = cost;
			this.flight = flight;
		}
	}

	private static class position implements Comparable<position> {
		int city;
		long cost;
		int flights;

		private position(int city, long cost, int flights) {
			this.city = city;
			this.cost = cost;
			this.flights = flights;
		}

		@Override
		public int compareTo(position o) {
			if ((cost - o.cost) < 0) {
				return -1;
			} else if ((cost - o.cost) == 0) {
				return flights - o.flights;
			} else {
				return 1;
			}

		}
	}

}