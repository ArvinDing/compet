
import java.io.*;
import java.util.*;

public class checklist2 {
	private static int[][] hInfo;
	private static int[][] gInfo;
	private static int h;
	private static int g;
	private static boolean[][][] done;
	private static long[][] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("checklist.in"));
		PrintWriter out = new PrintWriter(new File("checklist.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		h = Integer.parseInt(read.nextToken());
		g = Integer.parseInt(read.nextToken());
		hInfo = new int[h][2];
		gInfo = new int[g][2];
		done = new boolean[h][g][2];
		distance = new long[h + g][h + g];
		for (int i = 0; i < h; i++) {
			read = new StringTokenizer(in.readLine());
			hInfo[i][0] = Integer.parseInt(read.nextToken());
			hInfo[i][1] = Integer.parseInt(read.nextToken());
		}
		for (int i = 0; i < g; i++) {
			read = new StringTokenizer(in.readLine());
			gInfo[i][0] = Integer.parseInt(read.nextToken());
			gInfo[i][1] = Integer.parseInt(read.nextToken());
		}
		PriorityQueue<long[]> queue = new PriorityQueue<long[]>(new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				if (o1[3] > o2[3])
					return 1;
				else if(o1[3]==o2[3])
					return o1[0]-o2[0]<0 ?-1:1;
				return -1;
			}
		});

		queue.add(new long[] { 0, -1, 0, 0 });
		while (!queue.isEmpty()) {
			long[] curr = queue.poll();
			int hCurr = (int) curr[0];
			int gCurr = (int) curr[1];
			int hTrue = (int) curr[2];
			long cost = curr[3];
			
			if (gCurr != -1) {
				if (done[hCurr][gCurr][hTrue])
					continue;
				done[hCurr][gCurr][hTrue] = true;
			}
		//	System.out.println((hCurr + 1) + " " + (gCurr + 1) + " " + hTrue + " " + cost);
			if (hCurr == h - 1) {
				out.println(cost);
				break;
			}
			int currX;
			int currY;
			if (hTrue == 0) {
				currX = hInfo[hCurr][0];
				currY = hInfo[hCurr][1];
			} else {
				currX = gInfo[gCurr][0];
				currY = gInfo[gCurr][1];
			}
			long add = 0;
			if (gCurr != g - 1) {
				int end = gCurr + h + 1;
				int start = (hTrue == 0) ? hCurr : gCurr + h;
				if (distance[start][end] != 0) {
					add = distance[start][end];
				} else {
					add = (gInfo[gCurr + 1][0] - currX) * (gInfo[gCurr + 1][0] - currX)
							+ (gInfo[gCurr + 1][1] - currY) * (gInfo[gCurr + 1][1] - currY);
					distance[start][end] = add;
				}
			}
			

			if (hCurr == h - 2) {
				if (gCurr < g - 1) {
					if (!done[hCurr][gCurr + 1][1]) {
						queue.add(new long[] { hCurr, gCurr + 1, 1, cost + add });
						continue;
					}
					continue;
				}
				
			}
			long add1 = 0;
			int end = hCurr + 1;
			int start = (hTrue == 0) ? hCurr : gCurr + h;
			if (distance[start][end] != 0) {
				add1 = distance[start][end];
			} else {
				add1 = (hInfo[hCurr + 1][0] - currX) * (hInfo[hCurr + 1][0] - currX)
						+ (hInfo[hCurr + 1][1] - currY) * (hInfo[hCurr + 1][1] - currY);
				distance[start][end] = add1;
			}
			if (gCurr == g - 1) {
				if (!done[hCurr + 1][gCurr][0]) {
					queue.add(new long[] { hCurr + 1, gCurr, 0, cost + add1 });
					
				}
			} else {
				if (!done[hCurr][gCurr + 1][1])
					queue.add(new long[] { hCurr, gCurr + 1, 1, cost + add });

				if (gCurr == -1 || !done[hCurr + 1][gCurr][0])
					queue.add(new long[] { hCurr + 1, gCurr, 0, cost + add1 });

			}
		}
		in.close();
		out.close();
	}

}