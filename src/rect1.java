
/*
ID: dingarv1
LANG: JAVA
TASK: rect1
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class rect1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rect1.in"));
		PrintWriter out = new PrintWriter(new File("rect1.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(read.nextToken());
		int b = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		int[][] addInfo = new int[n][4];
		int[][] removeInfo = new int[n][4];
		int[] colors = new int[n + 1];
		int[][] info = new int[n][2];
		TreeSet<Integer> y = new TreeSet<Integer>();
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int lx = Integer.parseInt(read.nextToken());
			int ly = Integer.parseInt(read.nextToken());
			int hx = Integer.parseInt(read.nextToken()) - 1;
			int hy = Integer.parseInt(read.nextToken()) - 1;
			y.add(hy + 1);
			y.add(ly);
			info[i] = new int[] { lx, hx };
			addInfo[i] = new int[] { ly, i };
			removeInfo[i] = new int[] { hy, i };
			colors[i + 1] = Integer.parseInt(read.nextToken()) - 1;
		}
		Comparator<int[]> sort = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		};
		Arrays.sort(addInfo, sort);
		Arrays.sort(removeInfo, sort);
		y.add(0);
		y.add(b);
		int addIdx = 0;
		int removeIdx = 0;
		int[] ans = new int[2500];
		boolean[] added = new boolean[n];
		Iterator<Integer> yItr = y.iterator();
		int currY = yItr.next();
		while (yItr.hasNext()) {
			while (removeIdx < n && removeInfo[removeIdx][0] == currY - 1) {
				added[removeInfo[removeIdx][1]] = false;
				removeIdx++;
			}
			int nextY = yItr.next();
			while (addIdx < n && currY == addInfo[addIdx][0]) {
				added[addInfo[addIdx][1]] = true;
				addIdx++;
			}
			LinkedList<int[]> queue = new LinkedList<int[]>();
			for (int i = 0; i < n; i++) {
				if (added[i]) {
					queue.add(new int[] { info[i][0], i, 0 });
					queue.add(new int[] { info[i][1] + 1, i, 1 });
				}
			}

			PriorityQueue<Integer> best = new PriorityQueue<Integer>(Collections.reverseOrder());
			queue.add(new int[] { a, 1 });
			best.add(-1);
			Collections.sort(queue, sort);
			int prev = 0;
			boolean[] remove = new boolean[n + 1];
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				int pos = curr[0];
				int use = -1;
				while ((use = best.peek()) != -1 && remove[use])
					best.poll();
				ans[colors[use + 1]] += (nextY - currY) * (pos - prev);
				if (pos == a)
					break;
				if (curr[2] == 0) {
					best.add(curr[1]);
				} else {
					remove[curr[1]] = true;
				}
				prev=pos;
			}
			currY = nextY;
		}
		for (int i = 0; i < 2500; i++)
			if (ans[i] != 0)
				out.println((i + 1) + " " + ans[i]);
		out.close();
		in.close();

	}
}
