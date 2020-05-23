
/*
ID: dingarv1
LANG: JAVA
TASK: fracdec
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class fracdec {
	private static int n;
	private static int d;
	private static StringBuilder boo = new StringBuilder();
	private static int remainder;

	private static int[] save;

	public static void main(String[] args) throws Exception {
		long tms = System.currentTimeMillis();
		Scanner in = new Scanner(new File("fracdec.in"));
		PrintWriter out = new PrintWriter(new File("fracdec.out"));
		n = in.nextInt();
		d = in.nextInt();
		save = new int[d];
		boo.append(n / d + ".");
		remainder = n % d;
		for (int i = 0; i < save.length; i++) {
			save[i] = -1;
		}
		System.out.println(System.currentTimeMillis() - tms);
		String b;
		while ((b = recursion()) == null)
			;
		System.out.println(System.currentTimeMillis() - tms);
		if (b.length() == 2) {
			b += "0";
		}
		for (int i = 1; i <= b.length(); i++) {
			out.print(b.charAt(i - 1));
			if (i % 76 == 0) {
				out.println();
			}
		}
		out.println();
		out.close();
		in.close();
		System.out.println(System.currentTimeMillis() - tms);
	}

	public static String recursion() {
		if (remainder == 0) {
			return boo.toString();
		}
		if (save[remainder] != -1) {
			int startPos = save[remainder];
			return boo.substring(0, startPos) + "(" + boo.substring(startPos) + ")";
		} else {
			save[remainder] = boo.length();
		}

		boo.append(String.valueOf((10 * remainder) / d));
		remainder = (10 * remainder) % d;
		return null;
	}
}