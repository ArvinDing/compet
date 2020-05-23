
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class mootube2 {
	private static TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, Integer>>> save;
	private static TreeMap<Integer, Integer>[] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new File("mootube.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int videos = Integer.parseInt(read.nextToken());
		int questions = Integer.parseInt(read.nextToken());
		info = new TreeMap[videos];
		for (int i = 0; i < videos; i++) {
			info[i] = new TreeMap<Integer, Integer>();
		}
		for (int i = 0; i < videos - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int cost = Integer.parseInt(read.nextToken());
			info[a].put(b, cost);
			info[b].put(a, cost);
		}
		save = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, Integer>>>();

		for (int i = 0; i < questions; i++) {

			read = new StringTokenizer(in.readLine());
			int k = Integer.parseInt(read.nextToken());
			int start = Integer.parseInt(read.nextToken());

			out.println(recursion(start-1, k, -1)-1);
		}
		in.close();
		out.close();
	}

	private static int recursion(int curr, int k, int previous) {
		if (save.containsKey(k) && save.get(k).containsKey(curr) && save.get(k).get(curr).containsKey(previous)) {
			return save.get(k).get(curr).get(previous);
		}
		int sum = 1;
		for (Entry<Integer, Integer> neighbor : info[curr].entrySet()) {
			if (neighbor.getKey() != previous) {
				if (neighbor.getValue() >= k) {
					sum += (recursion(neighbor.getKey(), k, curr));
				}
			}
		}
		if (!save.containsKey(k))
			save.put(k, new TreeMap<Integer, TreeMap<Integer, Integer>>());
		if (!save.get(k).containsKey(curr))
			save.get(k).put(curr, new TreeMap<Integer, Integer>());
		save.get(k).get(curr).put(previous, sum);
		return sum;
	}

}
