import java.util.*;
import java.io.*;

public class coin {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int amount = Integer.parseInt(read.nextToken());
		int length = Integer.parseInt(read.nextToken());
		long[] info = new long[length];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < length; i++) {
			info[i] = Long.parseLong(read.nextToken());
		}
		long[][] dp = new long[amount + 1][length];
		Arrays.sort(info);
		dp[0][0] = 1;

		for (int i = 0; i <= amount; i++) {
			for (int k = 0; k < length; k++) {
				for (int o = k; o < length; o++) {
					if (i + info[o] > amount)
						continue;
					dp[(int) (i + info[o])][o] += dp[i][k];
				}

			}
		}
		long sum = 0;
		for (int i = 0; i < length; i++) {
			sum += dp[amount][i];
		}
		System.out.println(sum);

	}
}
