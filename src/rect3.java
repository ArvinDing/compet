
/*
ID: dingarv1
LANG: JAVA
TASK: rect1
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class rect3 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rect1.in"));
		PrintWriter out = new PrintWriter(new File("rect1.out"));
		long begin = System.currentTimeMillis();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(read.nextToken());
		int b = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		int[][] addInfo = new int[n * 2][4];
		int[][] removeInfo = new int[n * 2][4];
		TreeSet<Integer> compact = new TreeSet<Integer>();
		TreeSet<Integer> y = new TreeSet<Integer>();
		int[] colors = new int[n + 1];
		colors[0] = 0;
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int lx = Integer.parseInt(read.nextToken());
			int ly = Integer.parseInt(read.nextToken());
			int hx = Integer.parseInt(read.nextToken()) - 1;
			int hy = Integer.parseInt(read.nextToken()) - 1;
			y.add(hy + 1);
			y.add(ly);
			compact.add(lx);
			compact.add(hx + 1);
			addInfo[i] = new int[] { ly, lx, i + 1, 0 };
			addInfo[i + n] = new int[] { ly, hx + 1, i + 1, 1 };
			removeInfo[i] = new int[] { hy, lx, i + 1, 0 };
			removeInfo[i + n] = new int[] { hy, hx + 1, i + 1, 1 };

			colors[i + 1] = Integer.parseInt(read.nextToken()) - 1;
		}
		Comparator<int[]> sort = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}
				return Integer.compare(o1[0], o2[0]);
			}
		};
		Arrays.sort(addInfo, sort);
		Arrays.sort(removeInfo, sort);
		int[] ans = new int[2500];
		compact.add(0);
		compact.add(a);
		Iterator<Integer> itr = compact.iterator();
		int prev = itr.next();
		int[][] compactX = new int[compact.size()][2];
		boolean[][] added = new boolean[compactX.length][n + 1];
		int idx = 0;
		while (itr.hasNext()) {
			compactX[idx][0] = prev;
			int end = itr.next();
			compactX[idx][1] = end - 1;
			prev = end;
			idx++;
		}
		compactX[compact.size() - 1] = new int[] { a, a };
		PriorityQueue<Integer>[] imp = new PriorityQueue[compactX.length];
		for (int i = 0; i < compactX.length; i++) {
			imp[i] = new PriorityQueue<Integer>(Collections.reverseOrder());
			imp[i].add(0);
			added[i][0] = true;
		}
		int aIdx = 0;
		int rIdx = 0;
		y.add(0);
		y.add(b);
		Iterator<Integer> yItr = y.iterator();
		int currY = yItr.next();
		while (yItr.hasNext()) {
			// System.out.println(currY);
			int nextY = yItr.next();
			LinkedList<Integer> add = new LinkedList<Integer>();
			LinkedList<Integer> remove = new LinkedList<Integer>();
			for (int k = 0; k < compactX.length; k++) {
				int start = compactX[k][0];
				int end = compactX[k][1];
				while (aIdx < addInfo.length && (currY == addInfo[aIdx][0])
						&& (start == addInfo[aIdx][1] || end == addInfo[aIdx][1])) {
					if (addInfo[aIdx][3] == 0) {
						// add
						add.add(addInfo[aIdx][2]);
					} else if (addInfo[aIdx][3] == 1) {
						add.remove(Integer.valueOf(addInfo[aIdx][2]));
					}
					aIdx++;
				}
				while (rIdx < removeInfo.length && (removeInfo[rIdx][0] + 1 == currY)
						&& (start == removeInfo[rIdx][1] || end == removeInfo[rIdx][1])) {
					if (removeInfo[rIdx][3] == 0) {
						remove.add(removeInfo[rIdx][2]);
					} else {
						remove.remove(Integer.valueOf(removeInfo[rIdx][2]));
					}
					rIdx++;
				}
				itr = add.iterator();
				while (itr.hasNext()) {
					int curr = itr.next();
					imp[k].add(curr);
					added[k][curr] = true;
				}
				itr = remove.iterator();
				while (itr.hasNext()) {

					added[k][itr.next()] = false;
				}
				while (!added[k][imp[k].peek()]) {
					imp[k].remove();
				}
				if (k != compactX.length - 1) {

					ans[colors[imp[k].peek()]] += (nextY - currY) * (end - start + 1);
					// System.out.print(colors[imp[k].peek()] + " ");
				}
			}
			currY = nextY;
		}
		for (int i = 0; i < 2500; i++) {
			if (ans[i] != 0)
				out.println((i + 1) + " " + ans[i]);
		}
		System.out.println(System.currentTimeMillis()-begin);
		out.close();
		in.close();

	}
}
