
/*
 ID: dingarv1
LANG: JAVA
TASK: telecow
 */
import java.io.*;
import java.util.*;

public class telecow {
	private static int computers;
	private static int a;
	private static int b;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("telecow.in"));
		PrintWriter out = new PrintWriter(new FileWriter("telecow.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		computers = Integer.parseInt(read.nextToken());
		int connections = Integer.parseInt(read.nextToken());
		a = Integer.parseInt(read.nextToken()) - 1;
		b = Integer.parseInt(read.nextToken()) - 1;
		int[][] info = new int[2 * computers][2 * computers];
		for (int i = 0; i < computers; i++) {
			info[i][i + computers] = 1;
			info[i + computers][i] = 1;
		}
		info[a][a + computers] = Integer.MAX_VALUE;
		info[a + computers][a] = Integer.MAX_VALUE;
		for (int i = 0; i < connections; i++) {
			read = new StringTokenizer(in.readLine());
			int first = Integer.parseInt(read.nextToken()) - 1;
			int second = Integer.parseInt(read.nextToken()) - 1;
			info[first + computers][second] = 1;
			info[second + computers][first] = 1;

		}

		int originalMaxFlow = maxFlow(info, -1);
		out.println(originalMaxFlow);
		int change = originalMaxFlow;
		int size = 0;
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < computers; i++) {
			if (i == a || i == b)
				continue;

			int different = maxFlow(info, i);
			if (different < change) {

				info[i] = new int[2 * computers];
				change = different;
				ans.add(i + 1);
				i = 0;
				size++;
				if (size == originalMaxFlow)
					break;
			}
		}
		Collections.sort(ans);
		Iterator<Integer> itr = ans.iterator();
		out.print(itr.next());
		while (itr.hasNext()) {
			out.print(" " + itr.next());
		}
		out.println();
		in.close();
		out.close();
	}

	private static int maxFlow(int[][] info, int exclude) {
		int[][] temp = new int[2 * computers][2 * computers];
		for (int i = 0; i < 2 * computers; i++) {
			for (int k = 0; k < 2 * computers; k++) {
				temp[i][k] = info[i][k];
			}
		}
		int[] previous = new int[2 * computers];
		int sum = 0;
		while (bfs(temp, previous, exclude)) {
			int index = b;
			int capacity = Integer.MAX_VALUE;
			while (previous[index] != -1) {
				capacity = Math.min(capacity, temp[previous[index]][index]);
				index = previous[index];
			}
			index = b;
			while (previous[index] != -1) {
				temp[previous[index]][index] -= capacity;
				temp[index][previous[index]] += capacity;
				index = previous[index];
			}
			sum += capacity;
			previous = new int[2 * computers];
		}
		return sum;

	}

	private static boolean bfs(int[][] info, int[] previous, int exclude) {

		boolean[] done = new boolean[2 * computers];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(a);
		done[a] = true;
		previous[a] = -1;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (curr == b)
				return true;
			for (int i = 0; i < 2 * computers; i++) {
				if (info[curr][i] > 0 && !done[i] && exclude != i) {
					queue.add(i);
					previous[i] = curr;
					done[i] = true;
				}
			}
		}
		return false;
	}
}
