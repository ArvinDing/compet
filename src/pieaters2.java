import java.io.*;
import java.util.*;

public class pieaters2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pieaters.in"));
		PrintWriter out = new PrintWriter(new File("pieaters.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		int[][] dp = new int[n][n];
		ArrayList<int[]>[] cowsS = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			cowsS[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int weight = Integer.parseInt(read.nextToken());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			cowsS[start].add(new int[] { weight, end - start + 1 });
		}
		Comparator<int[]> comp = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		};
		for (int i = 0; i < n; i++) {
			Collections.sort(cowsS[i], comp);
		}
		int[] maxAL = new int[300];
		// max A after or equal to this length
		for (int len = 1; len <= n; len++) {
			for (int i = 0; i + len - 1 < n; i++) {
				int end = i + len - 1;
				int max = 0;
				if (!cowsS[i].isEmpty()) {

					int temp = 0;
					int z = i;
					List<int[]> weights = new ArrayList<int[]>();
					for (int[] a : cowsS[i]) {
						if (a[1] > len)
							break;
						weights.add(a);
					}
					Collections.reverse(weights);
					int maxA = 0;
					for (int[] a : weights) {
						maxA = Math.max(maxA, a[0]);
						maxAL[a[1]] = maxA;
					}
					if (i == 0 && end == 4) {
						System.out.println("wart");
					}
					for (int[] a : cowsS[i]) {
						if (a[1] > len)
							break;
						else if (a[1] == len && i <= i + len - 2) {
							max = Math.max(max, maxAL[len] + dp[i][i + len - 2]);
						}
						while (z + 2 <= i + len - 1 && i + a[1] - 1 >= z + 1) {
							temp = Math.max(temp, dp[i][z] + dp[z + 2][end]);
							z++;
						}
						max = Math.max(maxAL[a[1]] + temp, max);
					}

					if (i + len - 1 >= i + 1) {
						max = Math.max(max, maxA + dp[i + 1][end]);
					}
				}
				for (int k = i; k + 1 <= i + len - 1; k++) {
					max = Math.max(max, dp[i][k] + dp[k + 1][end]);
				}
				dp[i][end] = max;
				
			}
		}
		for (int i = 0; i < n; i++) {
			for (int k = i; k < n; k++) {
				System.out.print(dp[i][k] + " ");
			}
			System.out.println();
		}
		out.println(dp[0][n - 1]);
		in.close();
		out.close();
	}
}
