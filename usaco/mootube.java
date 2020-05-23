
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class mootube {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new File("mootube.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int videos = Integer.parseInt(read.nextToken());
		int questions = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Integer>[] info = new TreeMap[videos];
		for (int i = 0; i < videos; i++) {
			info[i] = new TreeMap<Integer, Integer>();
		}
		for (int i = 0; i < videos - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken())-1;
			int b = Integer.parseInt(read.nextToken())-1;
			int cost = Integer.parseInt(read.nextToken());
			info[a].put(b, cost);
			info[b].put(a, cost);
		}
	
		for (int i = 0; i < questions; i++) {
			int cnt = 0;
			read = new StringTokenizer(in.readLine());
			int k = Integer.parseInt(read.nextToken());
			int start = Integer.parseInt(read.nextToken());
			LinkedList<int[]> queue = new LinkedList<int[]>();
			queue.add(new int[] { start - 1, -1 });
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				int node = curr[0];
				int previous = curr[1];
				for (Entry<Integer, Integer> neighbor : info[node].entrySet()) {
					if (neighbor.getKey() != previous) {
						if (neighbor.getValue() >= k) {
							cnt++;
							queue.add(new int[] { neighbor.getKey(), node });
						}
					}
				}
			}
			out.println(cnt);
		}
		in.close();
		out.close();
	}

}
