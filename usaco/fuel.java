
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class fuel {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fuel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int gasStations = Integer.parseInt(read.nextToken());
		int tank = Integer.parseInt(read.nextToken());
		int fuel = Integer.parseInt(read.nextToken());
		int distance = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		for (int i = 0; i < gasStations; i++) {
			read = new StringTokenizer(in.readLine());
			int index = Integer.parseInt(read.nextToken());
			int cost = Integer.parseInt(read.nextToken());
			if (!info.containsKey(index)) {
				info.put(index, Integer.MAX_VALUE);
			}
			info.put(index, Math.min(info.get(index), cost));

		}
		info.put(distance, -1);

		int[] gasCurrent = new int[gasStations];
		long[] gasCost = new long[gasStations];
		long currCost = 0;
		int currPoss = 0;
		int i = 0;
	
		outer: for (Entry<Integer, Integer> curr : info.entrySet()) {

			int position = curr.getKey();

			if (currPoss + fuel >= position) {
				fuel -= position - currPoss;

			} else {
				long goal = (position - currPoss) - fuel;
				while (goal != 0) {
					int minIndex = -1;
					long minCost = Integer.MAX_VALUE;
					long gasCanbuy = tank;
					for (int k = i - 1; k >= 0; k--) {
						if (gasCurrent[k] == tank)
							break;
						gasCanbuy = Math.min(gasCanbuy, tank - gasCurrent[k]);
						if (gasCost[k] < minCost) {
							minIndex = k;
							minCost = gasCost[k];
						}

					}
					if (minIndex == -1) {
						out.println(-1);
						break outer;
					}

					if (gasCanbuy >= goal) {
						for (int a = minIndex; a < i; a++) {
							gasCurrent[a] += goal;
						}
						currCost += goal * minCost;
						goal = 0;
					} else {
						for (int a = minIndex; a < i; a++) {
							gasCurrent[a] += gasCanbuy;
						}
						currCost += gasCanbuy * minCost;
						goal -= gasCanbuy;
					}
				}
				fuel = 0;
			}
			currPoss = position;
			if (currPoss == distance) {
				out.println(currCost);
				break;
			}
			// System.out.println(currCost);
			gasCost[i] = curr.getValue();
			gasCurrent[i] = fuel;
			i++;
		}
		in.close();
		out.close();
	}

	private static class station implements Comparable<station> {
		int position;
		int costPG;

		private station(int position, int costPG) {
			this.position = position;
			this.costPG = costPG;
		}

		@Override
		public int compareTo(station o) {
			return position - o.position;
		}

		@Override
		public boolean equals(Object v) {
			station a = (station) v;
			return a.position == position;
		}

		@Override
		public int hashCode() {
			return position;
		}
	}
}