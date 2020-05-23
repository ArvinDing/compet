
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class arith {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tests = Integer.parseInt(in.readLine());
		for (int i = 0; i < tests; i++) {
			String line = in.readLine();
			int operator = -1;
			int split = -1;
			for (int k = 0; k < line.length(); k++) {
				if (line.charAt(k) == '+') {
					operator = 0;
					split = k;
					break;
				} else if (line.charAt(k) == '-') {
					operator = 1;
					split = k;
					break;
				} else if (line.charAt(k) == '*') {
					operator = 2;
					split = k;
					break;
				}
			}
			String first = line.substring(0, split);
			String next = line.substring(split + 1);
			int aLen = first.length();
			int bLen = next.length();
			BigInteger a = new BigInteger(first);
			BigInteger b = new BigInteger(next);
			char[] link = new char[] { '+', '-', '*' };
			BigInteger ans = a;
			int lastDigit = aLen;
			if (operator == 0) {
				ans = ans.add(b);
				lastDigit = Math.max(bLen + 1, ans.toString().length());
			} else if (operator == 1) {
				ans = ans.subtract(b);
				lastDigit = Math.max(bLen + 1, aLen);
			} else {
				ans = ans.multiply(b);
				lastDigit = Math.max(bLen + 1, ans.toString().length());
			}
			int old = lastDigit;
			int spaces = lastDigit - aLen;
			for (int k = 0; k < spaces; k++)
				System.out.print(" ");
			System.out.println(a);
			spaces = lastDigit - (bLen + 1);
			for (int k = 0; k < spaces; k++)
				System.out.print(" ");
			System.out.println(link[operator] + "" + b);
			int ansLength = ans.toString().length();
			if (operator == 0 || operator == 1) {

				if (operator == 1) {
					spaces = Math.min(lastDigit - (bLen + 1), lastDigit - ansLength);
					for (int z = 0; z < lastDigit; z++) {
						if (z < spaces) {
							System.out.print(" ");
						} else {
							System.out.print("-");
						}
					}
				} else {
					for (int z = 0; z < lastDigit; z++) {
						System.out.print("-");
					}
				}
				spaces = lastDigit - (ansLength);
				System.out.println();
				for (int k = 0; k < spaces; k++)
					System.out.print(" ");
				System.out.println(ans);
			} else {
				char[] important = next.toCharArray();
				if (important.length != 1) {
					BigInteger last = BigInteger.valueOf(important[important.length - 1] - '0');
					BigInteger temp = a;
					temp = temp.multiply(last);
					int len = temp.toString().length();
					spaces = Math.min(lastDigit - (bLen + 1), lastDigit - len);
					for (int z = 0; z < lastDigit; z++) {
						if (z < spaces) {
							System.out.print(' ');
						} else {
							System.out.print('-');
						}
					}
					System.out.println();
					spaces = lastDigit - len;
					for (int z = 0; z < spaces; z++) {
						System.out.print(' ');
					}
					System.out.println(temp);
					for (int k = important.length - 2; k >= 0; k--) {
						lastDigit--;
						last = BigInteger.valueOf(important[k] - '0');
						temp = a;
						temp = temp.multiply(last);
						len = temp.toString().length();
						spaces = lastDigit - len;
						for (int z = 0; z < spaces; z++) {
							System.out.print(' ');
						}
						System.out.println(temp);
					}
				}
				spaces = old - ansLength;
				for (int z = 0; z < spaces; z++) {
					System.out.print(' ');
				}
				
				for (int z = 0; z < ansLength; z++) {
					System.out.print('-');
				}
				System.out.println();
				for (int z = 0; z < spaces; z++) {
					System.out.print(' ');
				}
				System.out.println(ans);
			}
			System.out.println();
		}
		in.close();
	}

}