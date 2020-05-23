
/*
ID: dingarv1
LANG: JAVA
TASK: rect1
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class rect2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rect1.in"));
		PrintWriter out = new PrintWriter(new File("rect1.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(read.nextToken());
		int b = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		int[][] info = new int[n * 4][4];
		TreeSet<Integer> compact = new TreeSet<Integer>();
		
		int[] colors = new int[n + 1];
		colors[0] = 0;
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int lx = Integer.parseInt(read.nextToken());
			int ly = Integer.parseInt(read.nextToken());
			int hx = Integer.parseInt(read.nextToken()) - 1;
			int hy = Integer.parseInt(read.nextToken()) - 1;
			compact.add(lx);
			compact.add(hx + 1);
			info[i] = new int[] { ly, lx, i + 1, 0 };
			info[i + n] = new int[] { ly, hx + 1, i + 1, 1 };
			info[i + 2 * n] = new int[] { hy, lx, i + 1, 2 };
			info[i + 3 * n] = new int[] { hy, hx + 1, i + 1, 3 };

			colors[i + 1] = Integer.parseInt(read.nextToken()) - 1;
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}
				return Integer.compare(o1[0], o2[0]);
			}
		});
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
		int cnt = 0;
		idx = 0;
	
		for (int i = 0; i < b; i++) {
			LinkedList<Integer> add = new LinkedList<Integer>();
			LinkedList<Integer> remove = new LinkedList<Integer>();
		
			for (int k = 0; k < compactX.length; k++) {
				int start = compactX[k][0];
				int end = compactX[k][1];
				while (idx < info.length && i == info[idx][0] && (start == info[idx][1] || end == info[idx][1])) {
					if (info[idx][3] == 0) {
						// add
						add.add(info[idx][2]);

					} else if (info[idx][3] == 1) {
						add.remove(Integer.valueOf(info[idx][2]));
					} else if (info[idx][3] == 2) {
						remove.add(info[idx][2]);
					} else {
						remove.remove(Integer.valueOf(info[idx][2]));
					}
					idx++;
				}
				itr = add.iterator();
				while (itr.hasNext()) {
					int curr = itr.next();
					cnt++;
					imp[k].add(curr);
					added[k][curr] = true;
				}
				while (!added[k][imp[k].peek()]) {
					imp[k].remove();
				}
				if (k != compactX.length - 1) {
					ans[colors[imp[k].peek()]] += end - start + 1;
					// System.out.print(colors[imp[k].peek()] + " ");
				}
				itr = remove.iterator();
				while (itr.hasNext()) {
					added[k][itr.next()] = false;
				}
			}

			// System.out.println();
		}
		System.out.println(cnt);
		for (int i = 0; i < 2500; i++) {
			if (ans[i] != 0)
				out.println((i + 1) + " " + ans[i]);
		}
		out.close();
		in.close();

	}
}
