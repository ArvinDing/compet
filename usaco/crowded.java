
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class crowded {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("crowded.in"));
		PrintWriter out = new PrintWriter(new FileWriter("crowded.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int distance = Integer.parseInt(read.nextToken());
		TreeMap<Integer, List<Integer>> foward = new TreeMap<Integer, List<Integer>>();
		TreeMap<Integer, List<Integer>> reverse = new TreeMap<Integer, List<Integer>>(Collections.reverseOrder());
		for (int i = 0; i < cows; i++) {
			read = new StringTokenizer(in.readLine());
			int position = Integer.parseInt(read.nextToken());
			int height = Integer.parseInt(read.nextToken());
			if (!foward.containsKey(position))
				foward.put(position, new ArrayList<Integer>());
			if (!foward.containsKey(position + distance + 1))
				foward.put(position + distance + 1, new ArrayList<Integer>());
			if (!reverse.containsKey(position))
				reverse.put(position, new ArrayList<Integer>());
			if (!reverse.containsKey(position - distance - 1))
				reverse.put(position - distance - 1, new ArrayList<Integer>());
			foward.get(position).add(height);
			foward.get(position + distance + 1).add(-height);
			reverse.get(position).add(height);
			reverse.get(position - distance - 1).add(-height);
		}
		boolean[] right = new boolean[cows];
		boolean[] left = new boolean[cows];
		int index = 0;
		TreeSet<Integer> max = new TreeSet<Integer>();
		for (List<Integer> curr : foward.values()) {
			Collections.reverse(curr);
			for (int a : curr) {
				if (a < 0)
					max.remove(-a);
				else {
					if (max.size() != 0) {
						int realMax = max.last();
						right[index] = (realMax >= 2 * a);
					}		
					index++;
					max.add(a);
				}
			}

		}
		index = cows - 1;
		for (List<Integer> curr : reverse.values()) {
			Collections.reverse(curr);
			for (int a : curr) {
				if (a < 0)
					max.remove(-a);
				else {
					if (max.size() != 0) {
						int realMax = max.last();
						left[index] = (realMax >= 2 * a);
					}		
					index--;
					max.add(a);
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < cows; i++) {
			if (left[i] && right[i])
				cnt++;
		}
		out.print(cnt);
		in.close();
		out.close();
	}
}