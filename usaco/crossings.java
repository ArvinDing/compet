
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class crossings {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("crossings.in"));
		PrintWriter out = new PrintWriter(new File("crossings.out"));
		int cows = Integer.parseInt(in.readLine());
		TreeSet<Linked> info = new TreeSet<Linked>();

		for (int i = 0; i < cows; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int begin = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			info.add(new Linked(begin, end, i));

		}
		boolean[] safe = new boolean[cows];
		Arrays.fill(safe, true);
		int max = Integer.MIN_VALUE;
		int prev = -1;
		for (Linked a : info) {
			if (a.end < max) {
				safe[a.cow] = false;
				safe[prev] = false;
			}
			if (a.end > max) {
				max = a.end;
				prev = a.cow;
			}

		}
		int min = Integer.MAX_VALUE;
		prev = -1;
		for (Linked a : info.descendingSet()) {

			if (a.end > min) {
				safe[a.cow] = false;
				safe[prev] = false;
			}
			if (a.end < min) {
				min = a.end;
				prev = a.cow;
			}

		}
		int cnt = 0;
		for (boolean a : safe) {
			if (a)
				cnt++;
		}
		out.println(cnt);
		in.close();
		out.close();
	}

	private static class Linked implements Comparable<Linked> {
		int start;
		int end;
		int cow;

		private Linked(int start, int end, int cow) {
			this.start = start;
			this.end = end;
			this.cow = cow;
		}

		@Override
		public int compareTo(Linked o) {
			return start - o.start;
		}
	}

}