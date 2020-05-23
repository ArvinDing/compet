
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class stampede {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("stampede.in"));
		PrintWriter out = new PrintWriter(new File("stampede.out"));
		int lines = Integer.parseInt(in.readLine());
		List<Cow> info = new ArrayList<Cow>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			int rate = Integer.parseInt(read.nextToken());
			info.add(new Cow(x, y, rate));
		}
		TreeMap<Integer, Integer> intervals = new TreeMap<Integer, Integer>();
		Collections.sort(info);
		int cnt = 0;
		for (Cow a : info) {
			int x = a.x;
			int rate = a.rate;
			int start = (-1 - x) * rate + 1;
			int end = start + rate - 1;
			System.out.println(start + " " + end);
			int leftStart = start;
			int leftEnd = end;
			boolean add = true;
			if (intervals.floorKey(start) != null) {
				int previous = intervals.floorKey(start);
				int pEnd = intervals.get(previous);
				if (pEnd >= start - 1) {

					intervals.remove(previous);

					start = previous;
					end = Math.max(end, pEnd);
					if (pEnd >= end) {
						add = false;
					}
					leftStart = pEnd;

				}
			}

			Integer next = start;
			while (true) {
				next = intervals.ceilingKey(next);
				if (next == null || next >= end+2) {
					break;
				}
				int nEnd = intervals.get(next);
				if (next <= leftStart && nEnd >= leftEnd) {
					add = false;
				}
				intervals.remove(next);
				if (nEnd > end) {
					end = nEnd;

				}

			}
			intervals.put(start, end);
			if (add && leftStart <= leftEnd) {
				cnt++;
			}
		}

		out.println(cnt);
		out.close();
		in.close();
	}

	public static class Cow implements Comparable<Cow> {
		int x;
		int y;
		int rate;

		public Cow(int x, int y, int rate) {
			this.x = x;
			this.y = y;
			this.rate = rate;
		}

		@Override
		public int compareTo(Cow o) {

			return y - o.y;
		}
	}

}