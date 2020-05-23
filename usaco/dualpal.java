
/*
ID: dingarv1
LANG: JAVA
TASK: dualpal
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class dualpal {

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("dualpal.out"));
		String base = in.readLine();
		String[] tokens = base.split(" ");
		int n = Integer.parseInt(tokens[0]);
		int l = Integer.parseInt(tokens[1]);

		char[] rep = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J' };
		int a = 0;
		int val = l + 1;
		while (a < n) {
			int cnt = 0;
			for (int i = 2; i <= 10; i++) {
				if (isPalindrome(toBase(val, i, rep))) {
					cnt++;
				}
			}
			if (cnt >= 2) {
				out.write(val+"");
				out.newLine();
				a++;
			}
			val++;
		}

		out.close();
	}

	private static boolean isPalindrome(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != input.charAt(input.length() - i - 1))
				return false;
		}
		return true;
	}

	private static String toBase(int i, int base, char[] rep) {
		StringBuilder sb = new StringBuilder();
		int val = i;
		while (val >= base) {
			sb.insert(0, rep[val % base]);
			val = val / base;
		}
		return sb.insert(0, rep[val]).toString();
	}
}
