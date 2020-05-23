
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowtip {
	private static int dimensions;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowtip.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));
		dimensions = Integer.parseInt(in.readLine());
		boolean[][] info = new boolean[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			String read = in.readLine();
			for (int k = 0; k < dimensions; k++) {
				info[i][k] = read.charAt(k) == '0';
			}
		}
		PriorityQueue<thing> queue = new PriorityQueue<thing>();
		boolean[][] flipped = new boolean[dimensions][dimensions];
		queue.add(new thing(info, flipped, 0));
		outer: while (!queue.isEmpty()) {
			thing a = queue.poll();
			boolean[][] currInfo = a.info;
			boolean[][] currFlipped = a.flipped;
			int flips = a.flips;
			if (checkdone(info)) {
				out.println(flips);
				break;
			}

			for (int i = dimensions - 1; i >= 0; i--) {
				for (int k = dimensions - 1; k >= 0; k--) {
					if (!flipped[i][k] && !info[i][k]) {
						currFlipped[i][k] = true;
						for (int j = 0; j <= i; j++) {
							for (int o = 0; o <= k; o++) {
								currInfo[j][o] = !currInfo[j][o];
							}
						}
						queue.add(new thing(currInfo, currFlipped, flips + 1));
						continue outer;
					}
				}
			}

		}

		in.close();
		out.close();
	}

	private static class thing implements Comparable<thing> {
		boolean[][] info;
		boolean[][] flipped;
		int flips;

		private thing(boolean[][] info, boolean[][] flipped, int flips) {
			this.info = info;
			this.flipped = flipped;
			this.flips = flips;
		}

		@Override
		public int compareTo(thing o) {
			return flips - o.flips;
		}
	}

	private static boolean checkdone(boolean[][] info) {
		for (int i = 0; i < dimensions; i++) {
			for (int k = 0; k < dimensions; k++) {
				if (!info[i][k])
					return false;
			}
		}
		return true;
	}

}