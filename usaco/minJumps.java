import java.util.*;
import java.io.*;

public class minJumps {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		outer: for (int i = 0; i < test; i++) {
			int length = Integer.parseInt(in.readLine());
			int[] info = new int[length];

			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < length; k++) {
				info[k] = Integer.parseInt(read.nextToken());
			}

			int[] dp = new int[length];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[0] = 0;
			for (int a = 0; a < length; a++) {
				int curr = dp[a];
				if (curr == Integer.MAX_VALUE) {
					System.out.println(-1);
					continue outer;
				}
				for (int b = a + 1; b <= a + info[a]; b++) {
					if (b == length)
						break;
					dp[b] = Math.min(dp[b], curr + 1);
				}
			}
			System.out.println(dp[length - 1]);
		}
		in.close();
	}
}
