package save;
import java.io.*;
import java.util.*;

public class PT07Z {
	static LinkedList<Integer>[] connect;
	static int best = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		connect = new LinkedList[n];

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (connect[a] == null)
				connect[a] = new LinkedList<Integer>();
			if (connect[b] == null)
				connect[b] = new LinkedList<Integer>();
			connect[a].add(b);
			connect[b].add(a);
		}
		in.close();
		recursion(0, -1);
		System.out.println(best-1);
	}

	private static int recursion(int i, int previous) {
		PriorityQueue<Integer> lol = new PriorityQueue<Integer>(Collections.reverseOrder());
		System.out.println(i+" "+previous);
		for (int neighbor : connect[i]) {
			if (neighbor != previous)
				lol.add(recursion(neighbor, i));
		}
		if (lol.isEmpty()) {
			return 1;
		}
		int longest = lol.poll();
		if (lol.isEmpty()) {
			best = Math.max(best, longest + 1);
		} else {
			best = Math.max(best, longest + lol.poll() + 1);
		}
		return longest + 1;
	}

}