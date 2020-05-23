package save;
import java.io.*;
import java.util.*;

public class segmentTreetest {
	private static int n = 6;
	private static int[] seg = new int[4 * n + 1];
	private static int[] info;

	public static void main(String[] args) {
		Arrays.fill(seg, n);
		info = new int[] { 1, 3, 5, 7, 9, 11, Integer.MAX_VALUE };
		for (int i = 0; i < info.length; i++) {
			update(i, info[i], 1, 0, n - 1);
		}
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				System.out.println(i + " " + j + " " + query(i, j, 1, 0, n - 1));
			}
		}

	}

	private static int query(int start, int end, int segIdx, int segStart, int segEnd) {
		int mid = (segStart + segEnd) >> 1;
		if (segStart == segEnd)
			return seg[segIdx];
		if (end <= mid)
			return query(start, end, (segIdx << 1), segStart, mid);

		if (start > mid)
			return query(start, end, (segIdx << 1) + 1, mid + 1, segEnd);

		if (start == segStart && end == segEnd)
			return seg[segIdx];

		int right = query(start, end, (segIdx << 1), segStart, mid);
		int left = query(start, end, (segIdx << 1) + 1, mid + 1, segEnd);
		if (info[right] < info[left])
			return right;

		return left;

	}

	private static void update(int index, int value, int segIdx, int start, int end) {

		if (index < start || index > end)
			return;
		if (value < info[seg[segIdx]]) {
			seg[segIdx] = index;
		}
		if (start == end)
			return;
		int mid = (start + end) >> 1;
		update(index, value, (segIdx << 1), start, mid);
		update(index, value, (segIdx << 1) + 1, mid + 1, end);
	}
}
