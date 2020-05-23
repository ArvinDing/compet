package usaco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;

public class Anyalze {
	private static long prime;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		String input = in.readLine();
		String remove = in.readLine();
		String curr = input.substring(0, remove.length());
		for (int i = remove.length(); i < input.length(); i++) {
			curr += input.charAt(i);
			String correct = right(curr, remove);
			String wrong = wrong(curr, remove);
			if (!correct.equals(wrong)) {
				System.out.println(curr);
				break;
			}
		}
		in.close();

	}

	private static String right(String a, String b) {

		StringBuilder last = new StringBuilder(a);
		StringBuilder first = new StringBuilder();
		String remove = b;

		for (int i = 0; i < last.length() - remove.length() + 1; i++) {
			if (last.substring(i, i + remove.length()).equals(remove)) {
				for (int k = remove.length() - 2; k >= 0; k--) {
					if (first.length() - (remove.length() - 1) + k < 0) {
						i += k + 1;
						break;
					}
					last.setCharAt(i + k + 1, first.charAt(first.length() - (remove.length() - 1) + k));
				}
				if (first.length() - (remove.length() - 1) < 0) {
					first.setLength(0);
				} else {
					first.setLength(first.length() - (remove.length() - 1));
				}
			} else {
				first.append(last.charAt(i));
			}

		}
		return (first + "" + last.substring(last.length() - remove.length() + 1));

	}

	private static String wrong(String a, String b) {
		StringBuilder info = new StringBuilder(a);
		String remove = b;
		prime = BigInteger.probablePrime(31, new Random()).longValue();
		long compare = getHash(remove);
		long curr = getHash(info.substring(0, remove.length()));
		long divide = 1;
		for (int i = 0; i < remove.length() - 1; i++) {
			divide *= 27;
			divide %= prime;
		}

		int i = remove.length();
		while (i <= info.length()) {
			if (curr == compare)
				if (info.substring(i - remove.length(), i).equals(remove)) {
					info.delete(i - remove.length(), i);
					i -= remove.length();
					curr = getHash(info.substring(Math.max(0, i - remove.length() + 1), i));
				}
			if (i == info.length())
				break;
			curr %= divide;
			curr = (curr * 27) + (info.charAt(i) - 96);
			curr %= prime;
			i++;

		}
		return info.toString();
	}

	private static long getHash(String in) {
		long curr = 0;
		for (int i = 0; i < in.length(); i++) {
			curr = curr * 27 + (in.charAt(i) - 96);
			curr = curr % prime;
		}
		return curr;
	}
}
