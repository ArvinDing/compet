
/*
ID: dingarv1
LANG: JAVA
TASK: subset
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class subset {
	static long[][] cache = new long[8000][40];

	public static void main(String[] args) throws IOException {
		Scanner br = new Scanner(new File("subset.in"));
		PrintWriter out = new PrintWriter(new FileWriter("subset.out"));
		init();
		int n = br.nextInt();
		int m = n * (n + 1) / 2;
		if (m % 2 != 0)
			out.println("0");
		else
			out.println(numberOfWays(m / 2, n) / 2);
		br.close();
		out.close();
	}

	public static void init() {
		for (int i = 0; i < cache.length; ++i)
			Arrays.fill(cache[i], -1);
	}

	public static long numberOfWays(int n, int k) {
		if (n < 0 || k < 0)
			return 0;
		if (cache[n][k] != -1)
			return cache[n][k];
		if (n == 0 && k == 0)
			return cache[n][k] = 1;
		return cache[n][k] = numberOfWays(n, k - 1) + numberOfWays(n - k, k - 1);
	}
}