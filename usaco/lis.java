import java.io.*;
import java.util.*;

public class lis {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			int size = Integer.parseInt(in.readLine());
			int[] info = new int[size];
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < size; k++) {
				info[k] = Integer.parseInt(read.nextToken());
			}
			int[] dp = new int[size];
			Arrays.fill(dp, 1);
			int max = 0;
			for (int k = 0; k < size; k++) {
				for (int z = k + 1; z < size; z++) {
					if (info[z] > info[k]) {
						dp[z] = Math.max(dp[z], dp[k] + 1);
					}
				}
				max = Math.max(dp[k], max);
			}
			System.out.println(max);
		}
		in.close();
	}
}
