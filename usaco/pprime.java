
/*
ID: dingarv1
LANG: JAVA
TASK: pprime
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class pprime {
	static List<Integer> res = new ArrayList<Integer>();
	static String[] init = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "00", "11", "22", "33",
			"44", "55", "66", "77", "88", "99" };
	static int a;
	static int b;
	private static String bstr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("pprime.out"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		bstr = Integer.toString(b);

		for (String s : init)
			palindrome(s);
		Collections.sort(res);

		for (Integer num : res) {
			out.write(num + "");
			out.newLine();
		}
		in.close();
		out.close();
		System.exit(0);
	}

	private static void palindrome(String n) {

		checkAndRecord(n);

		if (n.length() + 2 > bstr.length())
			return;

		for (int i = 0; i <= 9; i++) {
			String tmp = i + n + i;
			palindrome(tmp);
		}
	}

	private static void checkAndRecord(String n) {
		int tmp = Integer.parseInt(n);

		if (tmp % 2 == 0 || tmp % 3 == 0)
			return;

		if (tmp < a || tmp > b)
			return;

		if (Integer.toString(tmp).length() != n.length())
			return;

		for (int i = 5; i * i <= tmp; i += 2) {
			if (tmp % i == 0)
				return;
		}
		res.add(tmp);
	}
}
