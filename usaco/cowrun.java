
import java.io.*;
import java.util.*;

public class cowrun {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowrun.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));
		int cows = Integer.parseInt(in.readLine());
		int[] info = new int[cows + 1];
		info[0] = 0;
		for (int i = 1; i <= cows; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(info);
		int lastNeg = 0;
		for (int i = 0; i < cows; i++) {
			if (info[i] >= 0) {
				lastNeg = i;
				break;
			}
		}
		int[][][] dp = new int[lastNeg + 1][cows - lastNeg + 1][2];

		PriorityQueue<thing> queue = new PriorityQueue<thing>();
		queue.add(new thing(lastNeg - 1, lastNeg + 1, 0, true));

		while (true) {
			thing curr = queue.poll();
			int next = curr.next;
			boolean position = curr.position;
			int prev = curr.previous;
			int cost = curr.cost;
			if (prev == -1 && next == cows + 1) {
				out.println(cost);
				break;
			}
		//	System.out.println(prev + " " + next + " " + cost);
			if (prev > -1) {
				int prevCost = position ? info[next - 1] - info[prev] : info[prev + 1] - info[prev];
				if ((dp[prev][next - lastNeg - 1][0] == 0
						|| dp[prev][next - lastNeg - 1][0] > cost + ((cows - ((next - 1) - (prev + 1))) * prevCost))) {
					queue.remove(new thing(prev - 1, next, 0, false));
					queue.add(new thing(prev - 1, next, cost + ((cows - ((next - 1) - (prev + 1))) * prevCost), false));
					dp[prev][next - lastNeg - 1][0] = cost + prevCost;
				}
			}
			if (next < cows + 1) {
				int nextCost = position ? info[next] - info[next - 1] : info[next] - info[prev + 1];
				if ((dp[prev + 1][next - lastNeg][1] == 0
						|| dp[prev + 1][next - lastNeg][1] > cost + ((cows - ((next - 1) - (prev + 1))) * nextCost))) {
					queue.remove(new thing(prev, next + 1, 0, true));
					queue.add(new thing(prev, next + 1, cost + ((cows - ((next - 1) - (prev + 1))) * nextCost), true));
					dp[prev + 1][next - lastNeg][1] = cost + ((cows - ((next - 1) - (prev + 1))) * nextCost);
				}
			}

		}
		in.close();
		out.close();
	}

	private static class thing implements Comparable<thing> {
		int previous;
		int next;
		int cost;

		boolean position;

		private thing(int previous, int next, int cost, boolean position) {
			this.previous = previous;
			this.next = next;
			this.cost = cost;
			this.position = position;

		}

		@Override
		public int compareTo(thing o) {
			return cost - o.cost;
		}

		public int hashCode() {
			return next * 1000 + previous;
		}

		public boolean equals(Object obj) {
			thing a = (thing) obj;
			return a.next == next && a.previous == previous && a.position == position;

		}
	}

}