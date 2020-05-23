
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
import java.util.Comparator;
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

public class irrigation {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("irrigation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int least = Integer.parseInt(read.nextToken());
		int[][] info = new int[n][2];

		read = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(read.nextToken());
		int y = Integer.parseInt(read.nextToken());
		info[0] = new int[] { x, y };

		PriorityQueue<thing> queue = new PriorityQueue<thing>();
		queue.add(new thing(0, 0));
		for (int i = 1; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			x = Integer.parseInt(read.nextToken());
			y = Integer.parseInt(read.nextToken());
			info[i] = new int[] { x, y };
		}
		int[][] dp = new int[n][n];
		boolean[] connected = new boolean[n];

		int answer = 0;
		while (!queue.isEmpty()) {
			thing curr = queue.poll();
			int position = curr.index;
			if (connected[position])
				continue;
			answer += curr.distance;
			connected[position] = true;
			for (int i = 0; i < n; i++) {
				if (i == position || connected[i])
					continue;
				int distance = 0;
				if (dp[position][i] != 0) {
					distance = dp[position][i];
				} else {
					distance = (info[position][0] - info[i][0]) * (info[position][0] - info[i][0])
							+ (info[position][1] - info[i][1]) * (info[position][1] - info[i][1]);
					dp[position][i] = distance;
					dp[i][position] = distance;
				}
				if (distance < least) {
					continue;
				}
				queue.add(new thing(i, distance));
			}
		}
		lol: {
			for (int i = 0; i < connected.length; i++) {
				if (!connected[i]) {
					out.println(-1);
					break lol;
				}
			}
			out.print(answer);
		}
		in.close();
		out.close();
	}

	private static class thing implements Comparable<thing> {
		int index;
		int distance;

		private thing(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(thing o) {
			return (int) (distance - o.distance);
		}

	}

}