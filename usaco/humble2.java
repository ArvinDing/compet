
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
import java.util.Scanner;

public class humble2 {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("humble.in"));
		PrintWriter out = new PrintWriter(new File("humble.out"));
		int k = in.nextInt();
		int n = in.nextInt();
		int[] info = new int[k];
		for (int i = 0; i < k; i++) {
			info[i] = in.nextInt();
		}

		LinkedHashSet<Long> bob = new LinkedHashSet<Long>();

		for (int z : info) {
			int[] exponents = new int[k];
			Arrays.fill(exponents, 1);
			for (int j = 0; j < n; j++) {

				int t = 0;
				long smallest = Long.MAX_VALUE;
				for (int i = 0; i < k; i++) {
					if (Math.pow(z, exponents[i]) * info[i] < smallest) {
						t = i;
						smallest = (int) Math.pow(z, exponents[i]) * info[i];
					}
				}

				bob.add( smallest);

				exponents[t]++;

			}

		}
		List<Long> listI = new ArrayList<Long>();
		listI.addAll(bob);

		for (int i = 0; i < info.length; i++) {
			listI.add((long) info[i]);
		}
		Collections.sort(listI);
		out.println(listI.get(n-1));
		out.close();
		in.close();
	}

}
