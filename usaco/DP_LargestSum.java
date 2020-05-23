
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class DP_LargestSum {
	private static int largestSum(int[] seq) {
		int max = Integer.MIN_VALUE;
		int[] start = new int[seq.length + 1];
		for (int i = seq.length - 1; i >= 0; i--) {
			if (start[i + 1] > 0) {
				start[i] = start[i + 1] + seq[i];
			} else {
				start[i] = seq[i];
			}
			max = Math.max(start[i], max);
		}
		return max;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader(
				"input"))) {/*
							 * new java.io.InputStreamReader(System.in))) {//
							 */
			String[] line = in.readLine().split(" ");
			int[] seq = new int[line.length];
			for (int i = 0; i < seq.length; i++) {
				seq[i] = Integer.parseInt(line[i]);
			}
			System.out.println(largestSum(seq));
		}
	}
}