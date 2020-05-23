
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class pairup {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
		PrintWriter out = new PrintWriter(new File("pairup.out"));
		int lines = Integer.parseInt(in.readLine());
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int cows = Integer.parseInt(read.nextToken());
			int time = Integer.parseInt(read.nextToken());
			if (!info.containsKey(time)) {
				info.put(time, 0);
			}
			info.put(time, info.get(time) + cows);
		}

		int max = Integer.MIN_VALUE;

		while (info.size() >= 2) {
			Entry<Integer, Integer> first = info.pollFirstEntry();
			Entry<Integer, Integer> last = info.pollLastEntry();
			int cMin = Math.min(first.getValue(), last.getValue());
			if (first.getValue() - cMin != 0) {
				info.put(first.getKey(), first.getValue() - cMin);
			}
			if (last.getValue() - cMin != 0) {
				info.put(last.getKey(), last.getValue() - cMin);
			}
			max = Math.max(first.getKey() + last.getKey(), max);

		}
		if (info.size() == 1) {
			max = Math.max(2 * info.pollFirstEntry().getKey(), max);
		}
		out.print(max);
		out.close();
		in.close();
	}

}