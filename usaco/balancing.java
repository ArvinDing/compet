
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class balancing {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		TreeMap<Integer, List<Integer>> yKey = new TreeMap<Integer, List<Integer>>();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		TreeSet<Integer> yIndex = new TreeSet<Integer>();
		TreeSet<Integer> xIndex = new TreeSet<Integer>();
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			if (!yKey.containsKey(y)) {
				yKey.put(y, new ArrayList<Integer>());
			}
			yKey.get(y).add(x);
			yIndex.add(y);
			xIndex.add(x);
		}
		int min = Integer.MAX_VALUE;

		for (int y : yIndex) {
			SortedMap<Integer, List<Integer>> head = yKey.headMap(y);
			SortedMap<Integer, List<Integer>> tail = yKey.tailMap(y);
		
			for (int x : xIndex) {
				int lessEqualXhead = 0;
				int greaterXhead = 0;
				for (List<Integer> hTemp : head.values()) {
					for (int h : hTemp) {
						if (h <= x)
							lessEqualXhead++;
						else
							greaterXhead++;

					}
				}

				int lessEqualXtail = 0;
				int greaterXtail = 0;
				for (List<Integer> tTemp : tail.values()) {
					for (int t : tTemp) {

						if (t <= x)
							lessEqualXtail++;
						else
							greaterXtail++;
					}
				}
				int cMax = Math.max(lessEqualXhead, greaterXhead);
				cMax = Math.max(lessEqualXtail, cMax);
				cMax = Math.max(greaterXtail, cMax);
				min = Math.min(min, cMax);
			}

		}
		out.println(min);
		in.close();
		out.close();
	}

}