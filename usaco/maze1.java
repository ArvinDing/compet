
/*
ID: dingarv1
LANG: JAVA
TASK: maze1
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class maze1 {

	public static char[][] info;
	public static int[][] costs;
	public static boolean[][] visited;
	public static boolean[][] added;
	public static int maxDist;
	public static int w;
	public static int h;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new File("maze1.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		w = 2 * Integer.parseInt(st.nextToken()) + 1;
		h = 2 * Integer.parseInt(st.nextToken()) + 1;
		info = new char[h][w];
		costs = new int[h][w];
		visited = new boolean[h][w];
		for (int i = 0; i < h; i++) {
			String pie = in.readLine();
			for (int j = 0; j < pie.length(); j++) {
				info[i][j] = pie.charAt(j);
			}
		}
		ArrayList<int[]> ent = findEntrances();
		goThrough(ent);
		System.out.println(maxDist / 2);
		out.println(maxDist / 2);
		out.close();
	}

	public static void goThrough(ArrayList<int[]> ent) {
		int dist = 1;
		while (ent.size() > 0) {
			ArrayList<int[]> neighbors = new ArrayList<int[]>();
			added = new boolean[h][w];
			for (int[] t : ent) {
				costs[t[0]][t[1]] = dist;
				maxDist = dist;
				visited[t[0]][t[1]] = true;
				neighbors.addAll(isConnected(t));
			}
			ent = neighbors;
			dist++;
		}
	}

	public static ArrayList<int[]> isConnected(int[] c) {
		ArrayList<int[]> res = new ArrayList<int[]>();
		int[][] dirs = new int[][] { { 1, 0, -1, 0 }, { 0, 1, 0, -1 } };
		for (int i = 0; i < dirs[0].length; i++) {
			int newR = c[0] + dirs[0][i];
			int newC = c[1] + dirs[1][i];
			if (newR >= 0 && newC >= 0 && newR < info.length && newC < info[0].length && !visited[newR][newC]
					&& !added[newR][newC] && (info[newR][newC] == ' ')) {
				res.add(new int[] { newR, newC });
				added[newR][newC] = true;
			}
		}
		return res;
	}

	public static ArrayList<int[]> findEntrances() {
		ArrayList<int[]> res = new ArrayList<int[]>();

		for (int i = 0; i < info[0].length; i++) {
			for (int j : new int[] { 0, info.length - 1 }) {
				char c = info[j][i];
				if (c == ' ') {
					int[] temp = new int[] { j, i };
					res.add(temp);
				}
			}
		}

		for (int i = 1; i < info.length - 1; i++) {
			for (int j : new int[] { 0, info[0].length - 1 }) {
				char c = info[i][j];
				if (c == ' ') {
					int[] temp = new int[] { i, j };
					res.add(temp);
				}
			}
		}
		return res;
	}

}
