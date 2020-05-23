
import java.io.BufferedReader;
import java.io.File;
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

public class DP_BinStrNoConsec {
	public static int countBinStr(int num) {
		int[][] sums = new int[num + 1][2];
		sums[0][0] = 1;
		sums[0][1] = 1;
		for (int i = 1; i <= num-1; i++) {
			sums[i][0] += sums[i-1][1] + sums[i-1][0];
			sums[i][1] += sums[i-1][0];
		}
		return sums[num-1][0] + sums[num-1][1];
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader(
				"input"))) {/*
							 * new java.io.InputStreamReader(System.in))) {
							 */
			System.out.println(countBinStr(Integer.parseInt(in.readLine())));
		}
	}
}