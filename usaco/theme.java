
/*
ID: dingarv1
LANG: JAVA
TASK: theme
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class theme {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("theme.in"));
		PrintWriter out = new PrintWriter(new File("theme.out"));
		int N = Integer.parseInt(in.readLine());
		int[] info = new int[N];
		for (int i = 0; i <= (N - 1) / 20; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < 20; k++) {
				if (!read.hasMoreTokens()) {
					break;
				}
				info[i * 20 + k] = Integer.parseInt(read.nextToken());
			}
		}
		in.close();

		int length = 1;

		int theme = 0;
		for (int difference = N - 1; difference > 0; difference--) {
			for (int firstnumber =  N - difference-1; firstnumber >=0; firstnumber--) {

				if ((firstnumber + difference + 1 < N && firstnumber + 1 < N) && info[firstnumber + 1]
						- info[firstnumber] == info[firstnumber + difference + 1] - info[firstnumber + difference]
						&& firstnumber + difference != firstnumber + length) {
					length++;

				}

				else {
					length = 1;
				}
				theme = Math.max(theme, length);
				
			}
		}

		if (theme >= 5)

		{
			out.println(theme);
		} else {
			out.println(0);
		}

		out.close();

	}
}