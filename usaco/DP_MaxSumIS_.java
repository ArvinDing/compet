
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

import javax.imageio.ImageReader;

public class DP_MaxSumIS_ {

	public static int maxSumIS(int[] arr) {
		int[] endDp = new int[arr.length];
		endDp[0] = arr[0];
		int realmax = endDp[0];
		for (int i = 1; i < arr.length; i++) {
			int current = arr[i];
			int max = Integer.MIN_VALUE;
			for (int k = i - 1; k >= 0; k--) {

				if (arr[k] < current) {
					max = Math.max(endDp[k] + current, max);
				}
			}
			if (max == Integer.MIN_VALUE) {
				endDp[i] = arr[i];
			} else
				endDp[i] = max;
			realmax = Math.max(realmax, endDp[i]);
		}
		return realmax;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			String[] line = in.readLine().split(" ");
			int[] seq = new int[line.length];
			for (int i = 0; i < seq.length; i++) {
				seq[i] = Integer.parseInt(line[i]);
			}
			System.out.println(maxSumIS(seq));
		}
	}
}
