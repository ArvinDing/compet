import java.io.*;
import java.util.*;

public class treedepth2 {

	static int[][] count;
	// how many sequences with [length][inversions]
	static int m;

	// inversions
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("treedepth.in"));
		PrintWriter out = new PrintWriter(new File("treedepth.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		m = Integer.parseInt(read.nextToken());
		count = new int[n + 1][((n * (n - 1)) / 2) + 1];

		count[1][0] = 1;
		for (int i = 2; i <= n; i++) {
			int sum = 0;
			for (int j = 0; j <= (i * (i - 1)) / 2; j++) {
				if (j >= i)
					sum -= count[i - 1][j - i];
				sum += count[i - 1][j];
				sum = (sum + m) % m;
				count[i][j] = sum;
			}
		}
		// sumDepth = new int[n + 1][n];
		// for (int i = 1; i <= n; i++) {
		// for (int j = 0; j <= (i * (i - 1)) / 2; j++) {
		// System.out.print(count[i][j] + " ");
		// }
		// System.out.println();
		// }
	
		for (int i = 0; i < n; i++) {
			out.print(sDepth(n, i, k));
			if (i != n - 1)
				out.print(" ");
		}
		out.println();
		in.close();
		out.close();
	}

	static long sDepth(int n, int position, int inver) {
		if (n == 1)
			return 1;
		long sum = 0;
		for (int i = 0; i < n; i++) {
			if (inver - i < 0)
				break;
			// i is pos of 1
			if (position > i) {

				int temp = count[n - i - 1][inver - i];
				if (temp == 0)
					temp = 1;
				long add = (count[n - 1][inver - i] / temp) * sDepth(n - i - 1, position - i - 1, inver - i)
						+ count[n - 1][inver - i];
				sum = (sum + add) % m;

			} else if (position == i) {
				sum = (sum + count[n - 1][inver - i]) % m;
			} else {
				int temp =count[i][inver - i];
				if (temp == 0)
					temp = 1;
				long add = (count[n - 1][inver - i] / temp) * (sDepth(i, position, inver - i))
						+ count[n - 1][inver - i];
				sum = (sum + add) % m;

			}
		}
		return sum % m;

	}
}
