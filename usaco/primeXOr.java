
import java.io.*;
import java.util.*;

public class primeXOr {
	private static final int mod = 1000000007;

	static long primeXor(int[] a) {
		long[][] dp = new long[1001][8192];
		dp[0][0] = 1 + (a[0] / 2);
		dp[0][3500] = (1 + a[0]) / 2;
		for (int i = 1; i <= 1000; i++) {
			for (int k = 0; k <= 8191; k++) {
				dp[i][k] = (dp[i - 1][k] * (1 + (a[i] / 2)) + dp[i - 1][k ^ (i + 3500)] * ((1 + a[i]) / 2)) % mod;
			}
		}
		boolean[] prime = new boolean[8192];
		Arrays.fill(prime, true);

		for (int i = 2; i * i <= 8191; i++) {
			if (prime[i]) {
				for (int k = i + i; k <= 8191; k += i) {
					prime[k] = false;
				}
			}
		}
		long sum = 0;
		for (int i = 2; i <= 8191; i++) {
			if (prime[i] && dp[1000][i] != 0) {
				sum = (sum + dp[1000][i]) % mod;
			}
		}
		return sum;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int n = in.nextInt();
			int[] a = new int[1001];
			for (int a_i = 0; a_i < n; a_i++) {
				a[in.nextInt() - 3500]++;
			}
			long result = primeXor(a);
			System.out.println(result);
		}
		in.close();
	}
}
