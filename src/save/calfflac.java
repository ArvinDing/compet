package save;

/*
ID: dingarv1
LANG: JAVA
TASK: calfflac
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class calfflac {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter out = new PrintWriter(new File("calfflac.out"));
		int center = 0;
		char[] info = new char[40000];
		int[] indexes = new int[40000];
		Arrays.fill(indexes, -1);
		char[] real = new char[20000];
		int index = 0;
		int rIdx = 0;
		info[index++] = ' ';
		boolean[] newLine = new boolean[40000];
		for (String line = in.readLine(); line != null; line = in.readLine()) {
			for (int i = 0; i < line.length(); i++) {
				real[rIdx] = line.charAt(i);

				if (Character.isAlphabetic(line.charAt(i))) {
					indexes[index] = rIdx;
					info[index++] = Character.toLowerCase(line.charAt(i));
					info[index++] = ' ';
				}
				rIdx++;
			}
			newLine[rIdx ] = true;
		}
		int[] p = new int[index];
		for (int i = 0; i < index; i++) {
			int temp = 0;
			boolean done = false;
			if (center - (i - center) >= 0) {
				if (index + p[center - (i - center)] < center + p[center]) {
					p[i] = p[center - (i - center)];
					done = true;
				} else {
					temp = Math.min(p[center - (i - center)], center + p[center] - i);
				}
			}
			if (!done) {
				while (i - temp >= 0 && i + temp < index && info[i + temp] == info[i - temp]) {
					temp++;
				}
				p[i] = --temp;
			}
		}
		int best = 0;
		int bIdx = -1;
		for (int i = 0; i < index; i++) {
			if (p[i] > best) {
				best = p[i];
				bIdx = i;
			}
		}

		out.println(best);

		for (int i = indexes[bIdx - best + 1]; i <= indexes[bIdx + best - 1]; i++) {
			if (newLine[i])
				out.println();
			out.print(real[i]);
		}
		out.println();
		out.close();
		in.close();
	}
}