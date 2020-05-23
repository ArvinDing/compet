import java.io.*;
import java.util.*;

public class treedepth {
	static long[][] rem, count;
	static int n, p, maxInv, k;

	// size, inversions
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("treedepth.in"));
		PrintWriter out = new PrintWriter(new File("treedepth.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		k = Integer.parseInt(read.nextToken());
		p = Integer.parseInt(read.nextToken());
		maxInv = n * (n - 1) / 2;
		count = new long[n + 1][maxInv + 1];
		count[1][0] = 1;
		for (int i = 2; i <= n; i++) {
			long sum = 0;
			for (int j = 0; j <= (i * (i - 1)) / 2; j++) {
				if (j >= i)
					sum -= count[i - 1][j - i];
				sum += count[i - 1][j];
				sum = (sum + p) % p;
				count[i][j] = sum;
			}
		}
		rem = new long[n][2];
		for (int i = 0; i < rem.length; i++) {
			rem[i][0] = -1;
			rem[i][1] = -1;
		}
		for (int i = 0; i < n; i++) {
			long sum = count[n][k];
			for (int j = i + 1; j < n; j++) {
				sum += div(j - i, k - (j - i));
				sum = sum % p;
			}
			for (int j = 0; j < i; j++) {
				sum += div(i - j, k);
				sum = sum % p;
			}
			out.print(sum);
			if (i != n - 1)
				out.print(" ");
		}

		out.println();
		in.close();
		out.close();
	}

	public static long div(int highD, int coef) {
		if (coef == k) {
			if (rem[highD][1] != -1)
				return rem[highD][1];
		} else {
			if (rem[highD][0] != -1)
				return rem[highD][0];
		}
		long sub = 0;
		LinkedList<Long> oldQ = new LinkedList<Long>();
		for (int z = maxInv; z >= highD; z--) {
			long quot = count[n][z] - sub;
			quot = (quot + p) % p;
			// quotient for z-(highD)
			if (z - (highD) == coef) {
				if (coef == k) {
					rem[highD][1] = quot;
				} else {
					rem[highD][0] = quot;
				}
				return quot;

			}
			sub += quot;
			sub = sub % p;
			
			if (oldQ.size() >= highD) {
				sub -= oldQ.poll();
			}
			oldQ.add(quot);
		}
		if (coef == k) {
			rem[highD][1] = 0;
		} else {
			rem[highD][0] = 0;
		}
		return 0;
	}
}
