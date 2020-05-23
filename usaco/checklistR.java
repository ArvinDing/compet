
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class checklistR {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("checklist.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int h = Integer.parseInt(read.nextToken());
		int g = Integer.parseInt(read.nextToken());

		int[][] hInfo = new int[h][2];
		int[][] gInfo = new int[g][2];
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

		long[][][] dp = new long[h + 1][g + 1][2];
		for (int i = 0; i <= h; i++) {
			for (int k = 0; k <= g; k++) {
				for (int z = 0; z < 2; z++) {
					dp[i][k][z] = Long.MAX_VALUE/2;
				}
			}
		}
		dp[1][0][0] = 0;
		for (int i = 0; i <= h; i++) {
			for (int k = 0; k <= g; k++) {
				for (int hOrg = 0; hOrg < 2; hOrg++) {
					if (hOrg == 0 && i == 0)
						continue;
					if (hOrg == 1 && k == 0)
						continue;
					int[] bottom = (hOrg == 0) ? hInfo[i - 1] : gInfo[k - 1];
					if (i != h)
						dp[i + 1][k][0] = Math.min(dp[i + 1][k][0], dp[i][k][hOrg] + distance(bottom, hInfo[i]));
					if (k != g)
						dp[i][k + 1][1] = Math.min(dp[i][k + 1][1], dp[i][k][hOrg] + distance(bottom, gInfo[k]));
				}
			}
		}
		out.println(dp[h][g][0]);
		out.close();
		in.close();
	}

	private static int distance(int[] a, int[] b) {
		return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
	}

}