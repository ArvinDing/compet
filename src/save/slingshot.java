package save;
import java.io.*;
import java.util.*;

public class slingshot {
	private static int m;
	private static int n;
	private static int adjustedSize;
	private static TreeMap<Integer, Integer> link;
	private static long start;

	public static void main(String[] args) throws Exception {
		start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("slingshot.in"));
		PrintWriter out = new PrintWriter(new File("slingshot.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		m = Integer.parseInt(read.nextToken());
		LinkedList<int[]> slingshots = new LinkedList<int[]>();
		LinkedList<int[]> backslingshots = new LinkedList<int[]>();
		TreeSet<Integer> compress = new TreeSet<Integer>();
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			int time = Integer.parseInt(read.nextToken());
			compress.add(a);
			compress.add(b);
			if (a < b) {
				slingshots.add(new int[] { a, b, time, 0 });
				slingshots.add(new int[] { b, a, time, 1 });
			} else {
				backslingshots.add(new int[] { b, a, time, 0 });
				backslingshots.add(new int[] { a, b, time, 1 });
			}
		}

		int[][] saveQ = new int[m][2];
		LinkedList<int[]> queries = new LinkedList<int[]>();
		LinkedList<int[]> backwardsqueries = new LinkedList<int[]>();
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			saveQ[i] = new int[] { a, b };
			compress.add(a);
			compress.add(b);
			if (a < b) {
				queries.add(new int[] { a, b, i, 0 });
				queries.add(new int[] { b, a, i, 1 });
			} else {
				backwardsqueries.add(new int[] { b, a, i, 0 });
				backwardsqueries.add(new int[] { a, b, i, 1 });
			}
		}
		int index = 0;
		link = new TreeMap<Integer, Integer>();
		for (int curr : compress) {
			link.put(curr, index);
			index++;
		}
		adjustedSize = compress.size();
		Comparator<int[]> ascending = new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		};
		long[][] merge = new long[2][m];
		
		Collections.sort(slingshots, ascending);
		Collections.sort(queries, ascending);
		Collections.sort(backslingshots, ascending);
		Collections.sort(backwardsqueries, ascending);
	
		merge[0] = solve(slingshots, queries);
		merge[1] = solve(backslingshots, backwardsqueries);

		for (int i = 0; i < m; i++) {
			out.println(Math.min(Math.abs(saveQ[i][0] - saveQ[i][1]), Math.min(merge[0][i], merge[1][i])));
		}
		in.close();
		out.close();
	}

	private static long[] special(LinkedList<int[]> rSlingshots, LinkedList<int[]> rQueries) {
		long[] ans = new long[m];
		Arrays.fill(ans, Long.MAX_VALUE / 2);
		long[] seg = new long[4 * adjustedSize + 1];
		Arrays.fill(seg, Long.MAX_VALUE / 2);
		while (!rSlingshots.isEmpty() || !rQueries.isEmpty()) {
			int[] sCurr = null, qCurr = null;
			if (!rSlingshots.isEmpty())
				sCurr = rSlingshots.peek();
			if (!rQueries.isEmpty())
				qCurr = rQueries.peek();
			if (qCurr == null)
				break;
			if (!rSlingshots.isEmpty() && sCurr[0] >= qCurr[0]) {
				rSlingshots.removeFirst();
				if (sCurr[3] == 0) {
					update(link.get(sCurr[1]), sCurr[2] + sCurr[0] + sCurr[1], 1, 0, adjustedSize - 1, seg);
				}
			} else {
				rQueries.removeFirst();
				if (qCurr[3] == 0)
					ans[qCurr[2]] = Math.min(ans[qCurr[2]], -qCurr[0] - qCurr[1]
							+ query(link.get(qCurr[1]), adjustedSize - 1, 1, 0, adjustedSize - 1, seg));
			}

		}
		return ans;
	}

	private static long[] solve(LinkedList<int[]> slingshots, LinkedList<int[]> queries) {
		long[][][] seg = new long[2][2][4 * adjustedSize + 1];
		System.out.println(System.currentTimeMillis()-start);
		Arrays.fill(seg[0][0], Long.MAX_VALUE / 2);
		Arrays.fill(seg[1][0], Long.MAX_VALUE / 2);
		Arrays.fill(seg[0][1], Long.MAX_VALUE / 2);
		System.out.println(System.currentTimeMillis()-start);
		// [1][1] doesnt work Arrays.fill(seg[1][1], Long.MAX_VALUE / 2);

		LinkedList<int[]> reverseSlingshot = new LinkedList<int[]>();
		LinkedList<int[]> reverseQueries = new LinkedList<int[]>();
		Iterator<int[]> itr = slingshots.descendingIterator();
		while (itr.hasNext())
			reverseSlingshot.add(itr.next());
		itr = queries.descendingIterator();
		while (itr.hasNext())
			reverseQueries.add(itr.next());
		long[] ans = special(reverseSlingshot, reverseQueries);

		while (!slingshots.isEmpty() || !queries.isEmpty()) {
			int[] sCurr = null, qCurr = null;

			if (!slingshots.isEmpty())
				sCurr = slingshots.peek();
			if (!queries.isEmpty())
				qCurr = queries.peek();
			if (qCurr == null)
				break;

			if (!slingshots.isEmpty() && sCurr[0] <= qCurr[0]) {
				slingshots.removeFirst();
				int index = link.get(sCurr[1]);
				if (sCurr[3] == 0) {
					update(index, sCurr[2] - sCurr[0] + sCurr[1], 1, 0, adjustedSize - 1, seg[0][1]);
				} else {
					update(index, sCurr[2] + sCurr[1] - sCurr[0], 1, 0, adjustedSize - 1, seg[1][0]);
					update(index, sCurr[2] - sCurr[1] - sCurr[0], 1, 0, adjustedSize - 1, seg[0][0]);
				}
			} else {
				queries.removeFirst();
				int index = link.get(qCurr[1]);
				if (qCurr[3] == 0) {
					ans[qCurr[2]] = Math.min(ans[qCurr[2]],
							qCurr[0] - qCurr[1] + query(index, adjustedSize - 1, 1, 0, adjustedSize - 1, seg[0][1]));
				} else {
					ans[qCurr[2]] = Math.min(ans[qCurr[2]],
							qCurr[1] + qCurr[0] + query(0, index, 1, 0, adjustedSize - 1, seg[0][0]));
					ans[qCurr[2]] = Math.min(ans[qCurr[2]],
							-qCurr[1] + qCurr[0] + query(index, link.get(qCurr[0]), 1, 0, adjustedSize - 1, seg[1][0]));

				}
			}
		}
		return ans;
	}

	private static long query(int start, int end, int segIdx, int segStart, int segEnd, long[] seg) {
		int mid = (segStart + segEnd) >> 1;
		if (segStart == segEnd)
			return seg[segIdx];
		if (end <= mid)
			return query(start, end, (segIdx << 1), segStart, mid, seg);

		if (start > mid)
			return query(start, end, (segIdx << 1) + 1, mid + 1, segEnd, seg);

		if (start == segStart && end == segEnd)
			return seg[segIdx];

		long right = query(start, end, (segIdx << 1), segStart, mid, seg);
		long left = query(start, end, (segIdx << 1) + 1, mid + 1, segEnd, seg);
		return Math.min(right, left);

	}

	private static void update(int idx, int value, int segIdx, int start, int end, long[] seg) {
		if (idx < start || idx > end)
			return;

		seg[segIdx] = Math.min(seg[segIdx], value);

		if (start == end)
			return;
		int mid = (start + end) >> 1;
		update(idx, value, (segIdx << 1), start, mid, seg);
		update(idx, value, (segIdx << 1) + 1, mid + 1, end, seg);
	}

}