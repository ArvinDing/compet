import java.io.*;
import java.util.*;

public class editDist {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int aLength = Integer.parseInt(read.nextToken());
			int bLength = Integer.parseInt(read.nextToken());
			read = new StringTokenizer(in.readLine());
			char[] a = read.nextToken().toCharArray();
			char[] b = read.nextToken().toCharArray();
			int[][] dp = new int[aLength+1][bLength+1];
			for (int z = 0; z <= aLength; z++) {
				dp[z][0] = z;
			}
			for (int z = 0; z <= bLength; z++) {
				dp[0][z] = z;
			}
			for (int o = 1; o <= aLength; o++) {
				for (int p = 1; p <= bLength; p++) {

					if (a[o-1] == b[p-1]) {
						dp[o][p] = dp[o - 1][p - 1];
					} else {
						dp[o][p] = dp[o - 1][p - 1] + 1;
						dp[o][p] = Math.min(dp[o][p], Math.min(dp[o - 1][p] + 1, dp[o][p - 1]+1));
					}
				}
			}
			System.out.println(dp[aLength ][bLength]);
		}
		in.close();
	}

}
