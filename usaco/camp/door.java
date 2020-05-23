package camp;

import java.io.*;
import java.util.*;

public class door {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		boolean[] open = new boolean[n];
		boolean[] done = new boolean[m];
		boolean[] onOrOff = new boolean[m];
		int openC = 0;
		for (int i = 0; i < n; i++) {
			open[i] = Integer.parseInt(read.nextToken()) == 1;
			if (open[i])
				openC++;
		}
		int[][] dtswitch = new int[n][2];
		int[] dtwI = new int[n];
		LinkedList<Integer>[] stdoor = new LinkedList[m];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int size = Integer.parseInt(read.nextToken());
			stdoor[i] = new LinkedList<Integer>();
			for (int k = 0; k < size; k++) {
				int curr = Integer.parseInt(read.nextToken()) - 1;
				dtswitch[curr][dtwI[curr]] = i;
				dtwI[curr]++;
				stdoor[i].add(curr);
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (done[i])
				continue;
			LinkedList<int[]> queue = new LinkedList<int[]>();
			boolean work=true;
			queue.add(new int[] { 0, 1 });
			outer: while (!queue.isEmpty()) {
				int[] c = queue.poll();
				int cS = c[0];
				boolean toggle = (c[1] == 1);
				done[cS] = true;
				for (int changed : stdoor[cS]) {
					if (done[dtswitch[changed][0]] && done[dtswitch[changed][1]]) {
						if(toggle)
							open[changed] = !open[changed];
						if(!open[changed]) {
							work=false;
							break outer;
						}
					}
					
				}
			}
		}
		in.close();
	}
}
