import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class split {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("split.in"));
		PrintWriter out = new PrintWriter(new FileWriter("split.out"));
		int cows = Integer.parseInt(in.readLine());

		TreeMap<Integer, TreeSet<Integer>> xSort = new TreeMap<Integer, TreeSet<Integer>>();
		TreeMap<Integer, TreeSet<Integer>> xSortReverse = new TreeMap<Integer, TreeSet<Integer>>(
				Collections.reverseOrder());
		TreeMap<Integer, TreeSet<Integer>> ySort = new TreeMap<Integer, TreeSet<Integer>>();
		TreeMap<Integer, TreeSet<Integer>> ySortReverse = new TreeMap<Integer, TreeSet<Integer>>(
				Collections.reverseOrder());
		long maxX = Integer.MIN_VALUE;
		long maxY = Integer.MIN_VALUE;
		long minX = Integer.MAX_VALUE;
		long minY = Integer.MAX_VALUE;
		for (int i = 0; i < cows; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);

			if (!xSort.containsKey(x))
				xSort.put(x, new TreeSet<Integer>());
			if (!ySort.containsKey(y))
				ySort.put(y, new TreeSet<Integer>());
			if (!xSortReverse.containsKey(x))
				xSortReverse.put(x, new TreeSet<Integer>());
			if (!ySortReverse.containsKey(y))
				ySortReverse.put(y, new TreeSet<Integer>());
			xSort.get(x).add(y);
			ySort.get(y).add(x);
			xSortReverse.get(x).add(y);
			ySortReverse.get(y).add(x);

		}
		out.println(((maxX - minX) * (maxY - minY))
				- Math.min(solve(xSort, xSortReverse, minX, maxX), solve(ySort, ySortReverse, minY, maxY)));
		in.close();
		out.close();
	}

	private static long solve(TreeMap<Integer, TreeSet<Integer>> sorted, TreeMap<Integer, TreeSet<Integer>> reverse,
			long minKey, long maxKey) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		long[] info = new long[sorted.size()+1];
		int i = 1;
		for (Entry<Integer, TreeSet<Integer>> curr : sorted.entrySet()) {
			min = Math.min(min, curr.getValue().first());
			max = Math.max(max, curr.getValue().last());
			info[i] = (curr.getKey() - minKey) * (max - min);
			i++;
		}
		i--;
		i--;
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		long answer = Long.MAX_VALUE;
		for (Entry<Integer, TreeSet<Integer>> curr : reverse.entrySet()) {
			min = Math.min(min, curr.getValue().first());
			max = Math.max(max, curr.getValue().last());
			info[i] += (maxKey - curr.getKey()) * (max - min);
			answer = Math.min(answer, info[i]);
			i--;
		}
		return answer;
	}
}
