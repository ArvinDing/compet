
import java.io.*;
import java.util.*;

public class checklist {
	private static int[][] hInfo;
	private static int[][] gInfo;
	private static int h;
	private static int g;
	private static long[][][] dp;
	private static long[][] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("checklist.in"));
		PrintWriter out = new PrintWriter(new File("checklist.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		h = Integer.parseInt(read.nextToken());
		g = Integer.parseInt(read.nextToken());
		hInfo = new int[h][2];
		gInfo = new int[g][2];
		dp = new long[h][g][2];
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
		out.println(recursion(0, -1, true));
		in.close();
		out.close();
	}

	private static long recursion(int hCurr, int gCurr, boolean hTrue) {
		if (gCurr != -1)
			if (dp[hCurr][gCurr][hTrue ? 0 : 1] != 0)
				return dp[hCurr][gCurr][hTrue ? 0 : 1];
		if (hCurr == h - 1) {
			return 0;
		}
		int currX;
		int currY;
		if (hTrue) {
			currX = hInfo[hCurr][0];
			currY = hInfo[hCurr][1];
		} else {
			currX = gInfo[gCurr][0];
			currY = gInfo[gCurr][1];
		}
		long add = 0;
		if (gCurr != g - 1) {
			int end = gCurr + h + 1;
			int start = (hTrue) ? hCurr : gCurr + h;
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

				long answer = add + recursion(hCurr, gCurr + 1, false);
				if (gCurr != -1)
					dp[hCurr][gCurr][hTrue ? 0 : 1] = answer;
				System.out.println(hCurr + " " + gCurr + " " + answer);
				return answer;
			}
		}
		long add1 = 0;
		int end = hCurr + 1;
		int start = (hTrue) ? hCurr : gCurr + h;
		if (distance[start][end] != 0) {
			add1 = distance[start][end];
		} else {
			add1 = (hInfo[hCurr + 1][0] - currX) * (hInfo[hCurr + 1][0] - currX)
					+ (hInfo[hCurr + 1][1] - currY) * (hInfo[hCurr + 1][1] - currY);
			distance[start][end] = add1;
		}
		long answer;
		if (gCurr == g - 1) {
			answer = add1 + recursion(hCurr + 1, gCurr, true);
		} else {
			answer = Math.min(add + recursion(hCurr, gCurr + 1, false), add1 + recursion(hCurr + 1, gCurr, true));
		}
		if (gCurr != -1)
			dp[hCurr][gCurr][hTrue ? 0 : 1] = answer;
	//	System.out.println(hCurr + " " + gCurr + " " + answer);
		return answer;
	}

}