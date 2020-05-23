
/*
ID: dingarv1
LANG: JAVA
TASK: packrec
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class packrec {
	static int minArea = Integer.MAX_VALUE;
	static LinkedList<Integer> ans;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter out = new PrintWriter(new File("packrec.out"));
		int[][] info = new int[4][2];
		for (int i = 0; i < 4; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) };
		}
		int[][] temp = new int[4][2];

		for (int a = 0; a < 2; a++) {
			if (a == 0)
				temp[0] = new int[] { info[0][0], info[0][1] };
			else
				temp[0] = new int[] { info[0][1], info[0][0] };
			for (int b = 0; b < 2; b++) {
				if (b == 0)
					temp[1] = new int[] { info[1][0], info[1][1] };
				else
					temp[1] = new int[] { info[1][1], info[1][0] };
				for (int c = 0; c < 2; c++) {
					if (c == 0)
						temp[2] = new int[] { info[2][0], info[2][1] };
					else
						temp[2] = new int[] { info[2][1], info[2][0] };
					for (int d = 0; d < 2; d++) {
						if (d == 0)
							temp[3] = new int[] { info[3][0], info[3][1] };
						else
							temp[3] = new int[] { info[3][1], info[3][0] };
						dfs(temp, new boolean[4], new LinkedList<Integer>());
					}
				}
			}
		}
		out.println(minArea);
		double imp = Math.sqrt(minArea);
		Collections.sort(ans);
		int previous = -1;
		for (int a : ans) {
			if (a > imp)
				break;
			if (a != previous) {
				out.println(a + " " + minArea / a);
				previous = a;
			}
		}
		in.close();
		out.close();
	}

	static void solve(int[][] info) {
		// x,y
		int width = info[0][0] + info[1][0] + info[2][0] + info[3][0];
		int height = Math.max(Math.max(info[0][1], info[1][1]), Math.max(info[2][1], info[3][1]));
		process(width, height);
		width = Math.max(info[3][0], info[0][0] + info[1][0] + info[2][0]);
		height = info[3][1] + Math.max(info[0][1], Math.max(info[1][1], info[2][1]));
		process(width, height);
		width = Math.max(info[3][0], info[0][0] + info[1][0]) + info[2][0];
		height = Math.max(info[3][1] + Math.max(info[0][1], info[1][1]), info[2][1]);
		process(width, height);
		width = info[0][0] + Math.max(info[1][0], info[2][0]) + info[3][0];
		height = Math.max(Math.max(info[0][1], info[1][1] + info[2][1]), info[3][1]);
		process(width, height);
		width = Math.max(info[0][0] + info[2][0], info[1][0] + info[3][0]);
		height = Math.max(info[0][1], info[2][1]) + Math.max(info[1][1], info[3][1]);
		process(width, height);
		height = Math.max(info[2][1] + info[3][1], info[0][1] + info[1][1]);
		if (info[1][0] > info[0][0])
			height = Math.max(height, info[2][1] + info[1][1]);
		else if (info[1][0] < info[0][0]) {
			height = Math.max(height, info[0][1] + info[3][1]);
		}
		process(width, height);
	}

	private static void process(int width, int height) {
		int area = width * height;
		// System.out.println(width + " " + height + " " + debug);
		if (area < minArea) {
			minArea = area;
			ans = new LinkedList<Integer>();
		} else if (area == minArea) {
			ans.add(width);
			ans.add(height);
		}

	}

	static void dfs(int[][] info, boolean[] done, LinkedList<Integer> res) {
		if (res.size() == 4) {
			int[][] temp = new int[4][2];
			Iterator<Integer> itr = res.iterator();
			for (int i = 0; i < 4; i++)
				temp[i] = info[itr.next()];
			solve(temp);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (!done[i]) {
				done[i] = true;
				res.add(i);
				dfs(info, done, res);
				res.removeLast();
				done[i] = false;
			}
		}

	}
}
