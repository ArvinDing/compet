import java.io.*;
import java.util.*;

public class dirtraverse {
	private static List<Integer>[] tree;
	private static boolean[] isFile;
	private static long[] inside;
	private static int totalFiles;
	private static int[] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("dirtraverse.in"));
		PrintWriter out = new PrintWriter(new File("dirtraverse.out"));
		int n = Integer.parseInt(in.readLine());
		isFile = new boolean[n];
		inside = new long[n];
		tree = new List[n];
		cost = new int[n];
		totalFiles = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			cost[i] = read.nextToken().length();
			int neighbors = Integer.parseInt(read.nextToken());
			tree[i] = new ArrayList<Integer>();
			if (neighbors == 0) {
				isFile[i] = true;
				totalFiles++;
			}
			for (int k = 0; k < neighbors; k++) {
				tree[i].add(Integer.parseInt(read.nextToken()) - 1);
			}
		}
		long[] temp = recursion(0, 0);
		long totalDistance = temp[0];
		out.println(again(0, totalDistance));
		out.close();
		in.close();
	}

	private static long again(int position, long distance) {
		long min = distance;
		for (int neighbor : tree[position]) {
			if (isFile[neighbor])
				continue;
			min = Math.min(min, again(neighbor,
					distance + ((totalFiles - inside[neighbor]) * 3) - (inside[neighbor] * (cost[neighbor] + 1))));
		}
	//	System.out.println(position+" "+min);
		return min;
	}

	private static long[] recursion(int position, long distance) {
		long sum = 0;
		long insideCNT = 0;
		for (int neighbor : tree[position]) {
			if (isFile[neighbor]) {
				sum += distance + cost[neighbor];
				insideCNT++;
			} else {
				long[] temp = recursion(neighbor, distance + cost[neighbor] + 1);
				sum += temp[0];
				insideCNT += temp[1];
			}
		}
		inside[position] = insideCNT;
		return new long[] { sum, insideCNT };
	}
}