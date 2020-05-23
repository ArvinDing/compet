
/*
ID: dingarv1
LANG: JAVA
TASK: skidesign
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class bteams {
	private static int[] info;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bteams.in"));
		PrintWriter out = new PrintWriter(new File("bteams.out"));
		info = new int[12];
		for (int i = 0; i < 12; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		out.println(recursion(12, new boolean[12], Integer.MIN_VALUE, Integer.MAX_VALUE));
		in.close();
		out.close();
	}

	private static int recursion(int notDone, boolean[] done, int max, int min) {
		if (notDone == 0) {
			return max - min;
		}
		int cMin = Integer.MAX_VALUE;
		for (int i = 0; i < 12; i++) {
			if (!done[i]) {
				for (int k = i + 1; k < 12; k++) {
					if (!done[k]) {
						for (int j = k + 1; j < 12; j++) {
							if (!done[j]) {
								boolean[] copy = Arrays.copyOf(done, done.length);
								copy[i] = true;
								copy[k] = true;
								copy[j] = true;
								int total = info[i] + info[k] + info[j];
								cMin = Math.min(cMin,
										recursion(notDone - 3, copy, Math.max(max, total), Math.min(min, total)));
							}
						}
					
					}
				}
				break;
			}
		}
		return cMin;
	}

}
