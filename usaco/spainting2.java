
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class spainting2 {
	private static int mod = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new File("spainting.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int length = Integer.parseInt(read.nextToken());
		int stamps = Integer.parseInt(read.nextToken());
		int width = Integer.parseInt(read.nextToken());
		int realMax = Math.min(stamps, length - width + 1);
		long[][] dp = new long[length + 1][realMax + 1];
		dp[0][0] = 1;
		for (int i = width; i <= length; i++) {
			int max = Math.min(stamps, i - width + 1);
			for (int uses = 1; uses <= max; uses++) {
				for (int o = i - width; o < i; o++) {
					if (dp[o][uses - 1] != 0) {
						dp[i][uses] += dp[o][uses - 1];
						dp[i][uses] %= mod;
					}
				}
			}
		}
		int least = (length / width) + ((length % width == 0) ? 0 : 1);
		long factorial = 1;
		for (int i = 1; i <= least; i++) {
			factorial *= i;
			factorial %= mod;
		}
		long sum = 0;
		for (int i = least; i <= realMax; i++) {
			sum += (factorial * dp[length][i]) % mod;
			factorial = (factorial * (i + 1)) % mod;
		}
		out.println(sum);
		in.close();
		out.close();
	}

}
