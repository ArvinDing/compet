
/*
ID: dingarv1
LANG: JAVA
TASK: humble
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class humble {

	public static void main(String[] args) throws Exception {
		Long hum = System.currentTimeMillis();
		Scanner in = new Scanner(new File("humble.in"));
		PrintWriter out = new PrintWriter(new File("humble.out"));
		int k = in.nextInt();
		int n = in.nextInt();
		int[] info = new int[k];
		for (int i = 0; i < k; i++) {
			info[i] = in.nextInt();
		}
		List<Integer> humble = new ArrayList<Integer>();
		int[] index = new int[k];

		humble.add(1);
		for (int i = 1; i <= n; i++) {
			int best = Integer.MAX_VALUE;
			for (int j = 0; j < k; j++) {
				while (index[j] < i && info[j] * humble.get(index[j]) <= humble.get(i - 1)) {
					index[j]++;
				}
				best = Math.min(best, info[j] * humble.get(index[j]));
			}
			humble.add(best);
		}

		out.println(humble.get(n));
		out.close();
		in.close();

	}
}