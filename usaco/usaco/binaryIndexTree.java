package usaco;
import java.util.LinkedList;

public class binaryIndexTree {
	private static int[] bit;
	private static int[] info;
	private static int n = 10;
	private static int[] bit1;
	private static int[] bit2;

	public static void main(String[] args) {
		info = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// Point Update,Range Query
		bit = new int[n + 1];
		for (int i = 0; i < n; i++) {
			update(i, info[i], 0);
		}
		for (int i = 0; i < n; i++) {
			System.out.print(query(i, bit) + " ");
		}
		System.out.println();
		// Range Update,Point Query
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, 3, 1 });
		queue.add(new int[] { 2, 4, 1 });
		queue.add(new int[] { 5, 9, 1 });
		queue.add(new int[] { 2, 6, 1 });
		/*
		 *i= 0 1 2 3 4 5 6 7 8 9 
		 *   1 1 1 1 
		 *   1 1 2 2 1 
		 *   1 1 2 2 1 1 1 1 1 1
		 *   1 1 3 3 2 2 2 1 1 1
		 */
		bit = new int[n + 1];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int start = curr[0];
			int end = curr[1];
			int value = curr[2];
			update(start, value, 0);
			update(end + 1, -value, 0);
		}
		for (int i = 0; i < n; i++) {
			System.out.print(query(i, bit) + " ");
		}
		System.out.println();
		// Range Update,Range Query
		queue = new LinkedList<int[]>();
    	queue.add(new int[] { 0, 3, 1 });
		queue.add(new int[] { 2, 4, 1 });
		queue.add(new int[] { 5, 9, 1 });
		queue.add(new int[] { 2, 6, 1 });
		/*
		 *i= 0 1 2 3 4 5 6 7 8 9 
		 *   1 1 1 1 
		 *   1 1 2 2 1 
		 *   1 1 2 2 1 1 1 1 1 1
		 *   1 1 3 3 2 2 2 1 1 1
		 */
		bit1 = new int[n + 1];
		bit2 = new int[n + 1];
		// really good tutorial:
		// https://github.com/manoharreddyporeddy/advanced-data-structures-and-algorithms/blob/master/BIT_fenwick_tree_explanation.md
		// sum(0,x)=(x*query[1])-query[bit 2];
		// special update
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int start = curr[0];
			int end = curr[1];
			int value = curr[2];
			update(start, value, 1);
			update(end + 1, -value, 1);
			update(start, value * (start - 1), 2);
			update(end + 1, -end * value, 2);
		}
		for (int end = 0; end < n; end++) {
			System.out.print((end * query(end, bit1) - query(end, bit2)) + " ");
		}
	}

	private static void update(int index, int value, int caseN) {
		index++;
		while (index <= n) {
			if (caseN == 0)
				bit[index] += value;
			else if (caseN == 1)
				bit1[index] += value;
			else
				bit2[index] += value;
			index += (index & -index);
		}
	}

	private static int query(int index, int[] currBit) {
		index++;
		int sum = 0;
		while (index > 0) {
			sum += currBit[index];
			index = (index & index - 1);
		}
		return sum;
	}
}
