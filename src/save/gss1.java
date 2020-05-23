package save;

import java.io.*;
import java.util.*;

public class gss1 {
	private static segmentT[] info;
	private static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		input = new int[n];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(read.nextToken());
		}
		segmentT root = new segmentT(0, n - 1);
		int m = Integer.parseInt(in.readLine());
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			System.out.println(query(root, 0, n - 1, start, end).best);
		}
	}

	private static segmentT query(segmentT curr, int segmentStart, int segmentEnd, int start, int end) {
		if (segmentStart == start && segmentEnd == end)
			return curr;
		int mid = (segmentStart + segmentEnd) >> 1;
		if (end <= mid) {
			return query(curr.left, segmentStart, mid, start, end);
		}
		if (start > mid) {
			return query(curr.right, mid, segmentEnd, start, end);
		}
		segmentT ans = new segmentT();
		merge(ans, query(curr.left, segmentStart, mid, start, end), query(curr.left, segmentStart, mid, start, end));
		return ans;

	}

	private static void merge(segmentT parent, segmentT left, segmentT right) {
		parent.allSum = left.allSum + right.allSum;
		parent.prefix = Math.max(left.prefix, left.allSum + right.prefix);
		parent.suffix = Math.max(right.suffix, left.suffix + right.allSum);
		parent.best = Math.max(Math.max(left.best, right.best), left.suffix + right.prefix);
	}

	private static class segmentT {
		int allSum, prefix, suffix, best;
		segmentT left;
		segmentT right;

		private segmentT() {

		}

		private segmentT(int start, int end) {
			if (start != end) {
				int mid = (start + end) >> 1;
				left = new segmentT(start, mid);
				right = new segmentT(mid + 1, end);
				merge(this, left, right);
			} else {
				allSum = prefix = suffix = best = input[start];
			}
		}
	}
}