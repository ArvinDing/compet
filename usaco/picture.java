
/*
ID: dingarv1
LANG: JAVA
TASK: picture
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class picture {

	private static int add = 10000;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("picture.in"));
		PrintWriter out = new PrintWriter(new File("picture.out"));
		int lines = Integer.parseInt(in.readLine());
		TreeMap<Integer, LinkedList<int[]>> xSort = new TreeMap<Integer, LinkedList<int[]>>();
		TreeMap<Integer, LinkedList<int[]>> ySort = new TreeMap<Integer, LinkedList<int[]>>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int left = Integer.parseInt(read.nextToken()) + add;
			int bottom = Integer.parseInt(read.nextToken()) + add;
			int right = Integer.parseInt(read.nextToken()) + add;
			int top = Integer.parseInt(read.nextToken()) + add;
			if (!xSort.containsKey(left))
				xSort.put(left, new LinkedList<int[]>());
			if (!xSort.containsKey(right))
				xSort.put(right, new LinkedList<int[]>());
			if (!ySort.containsKey(bottom))
				ySort.put(bottom, new LinkedList<int[]>());
			if (!ySort.containsKey(top))
				ySort.put(top, new LinkedList<int[]>());
			xSort.get(left).addFirst(new int[] { bottom, top, 1 });
			xSort.get(right).add(new int[] { bottom, top, -1 });
			ySort.get(bottom).addFirst(new int[] { left, right, 1 });
			ySort.get(top).add(new int[] { left, right, -1 });

		}
		out.println(solve(xSort) + solve(ySort));
		in.close();
		out.close();

	}

	private static int solve(TreeMap<Integer, LinkedList<int[]>> info) {
		int ans = 0;
		int[] layers = new int[20001];
		for (LinkedList<int[]> thing : info.values()) {
			for (int[] a : thing) {
				int bot = a[0];
				int top = a[1];
				if (a[2] == 1) {
					for (int i = bot; i < top; i++) {
						if (layers[i] == 0)
							ans++;
						layers[i]++;
					}
				} else {
					for (int i = bot; i < top; i++) {
						layers[i]--;
						if (layers[i] == 0)
							ans++;
					}
				}
			}
		}
		return ans;
	}
}
