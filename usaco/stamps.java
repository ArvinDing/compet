
/*
ID: dingarv1
LANG: JAVA
TASK: stamps
*/

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class stamps {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new File("stamps.out"));

		int k = in.nextInt();
		int n = in.nextInt();
		int[] stamps = new int[n];
		for (int i = 0; i < n; i++) {
			stamps[i] = in.nextInt();
		}
		Arrays.sort(stamps);
		int[] min = new int[2000000];
		Arrays.fill(min, Integer.MAX_VALUE);
		min[0] = 0;
		int index = 0;
		while (true) {
			index++;
			for (int i : stamps) {
				if (i <= index) {
					if (min[index - i] + 1 < min[index]) {
						min[index] = min[index - i] + 1;
					}
				}
			}
			if (min[index] > k) {
				break;
			}
		}
		out.println(index-1);
		in.close();
		out.close();

	}
}