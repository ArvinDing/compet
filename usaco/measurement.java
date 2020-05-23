
import java.io.*;
import java.util.*;

public class measurement {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
		PrintWriter out = new PrintWriter(new File("measurement.out"));
		TreeMap<Integer, List<int[]>> map = new TreeMap<Integer, List<int[]>>();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		TreeMap<Integer, TreeSet<Integer>> max = new TreeMap<Integer, TreeSet<Integer>>();
		TreeMap<Integer, Integer> cows = new TreeMap<Integer, Integer>();
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int days = Integer.parseInt(read.nextToken());
			int cow = Integer.parseInt(read.nextToken());
			int change = Integer.parseInt(read.nextToken());
			if (!map.containsKey(days))
				map.put(days, new ArrayList<int[]>());
			map.get(days).add(new int[] { cow, change });
			cows.put(cow, 0);
		}
		cows.put(1000000001, 0);
		max.put(0, new TreeSet<>(cows.keySet()));
		int cnt = 0;
		for (List<int[]> currTemp : map.values()) {
			TreeSet<Integer> oldMax =new TreeSet<Integer>( max.lastEntry().getValue());
			for (int[] curr : currTemp) {
				int cow = curr[0];
				int change = curr[1];
				int old = cows.get(cow);
				cows.put(cow, old + change);
				if (max.get(old).size() == 1)
					max.remove(old);
				else
					max.get(old).remove(cow);

				if (!max.containsKey(old + change))
					max.put(old + change, new TreeSet<Integer>());
				max.get(old + change).add(cow);
			}
			if (!oldMax.equals( max.lastEntry().getValue()))
				cnt++;
		}
		out.println(cnt);
		out.close();
		in.close();
	}
}