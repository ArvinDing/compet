import java.io.BufferedReader;
import java.io.IOException;

public class DP_LongestBSeq_ {
	public static int[] start(int[] arr, boolean increasing) {
		int[] endDp = new int[arr.length];
		endDp[0] = 1;

		for (int i = 1; i < arr.length; i++) {
			int current = arr[i];
			int max = Integer.MIN_VALUE;
			for (int k = i - 1; k >= 0; k--) {
				if (increasing) {
					if (arr[k] < current) {
						max = Math.max(endDp[k] + 1, max);
					}
				} else {
					if (arr[k] > current) {
						max = Math.max(endDp[k] + 1, max);
					}
				}
			}
			if (max == Integer.MIN_VALUE) {
				endDp[i] = 1;
			} else
				endDp[i] = max;

		}
		return endDp;
	}

	public static int[] end(int[] arr, boolean increasing) {

		int[] startDp = new int[arr.length];
		startDp[arr.length - 1] = 1;

		for (int i = arr.length - 2; i >= 0; i--) {
			int current = arr[i];
			int max = Integer.MIN_VALUE;
			for (int k = i + 1; k < arr.length; k++) {
				if (increasing) {
					if (arr[k] > current) {
						max = Math.max(startDp[k] + 1, max);
					}
				} else {
					if (arr[k] < current) {
						max = Math.max(startDp[k] + 1, max);
					}
				}
			}
			if (max == Integer.MIN_VALUE) {
				startDp[i] = 1;
			} else
				startDp[i] = max;
		}
		return startDp;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader("input"))) {

			String[] line = in.readLine().split(" ");
			int[] seq = new int[line.length];
			for (int i = 0; i < seq.length; i++) {
				seq[i] = Integer.parseInt(line[i]);
			}
			int[] increaseF = start(seq, true);
			int[] increaseL = end(seq, true);
			int[] decreaseF = start(seq, false);
			int[] decreaseL = end(seq, false);
			int max = 0;
			for (int i = 0; i < increaseF.length; i++) {
				max = Math.max(increaseF[i] + decreaseL[i] - 1, max);
				max = Math.max(decreaseF[i] + increaseL[i] - 1, max);
			}
			System.out.println(max);
		}
	}
}