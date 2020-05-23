
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

public class DP_AltSubseq {
	static int longestAltSubseqLength(int[] arr) {
		int[] dpUp = new int[arr.length];
		int[] dpDown = new int[arr.length];
		Arrays.fill(dpUp, 1);
		Arrays.fill(dpDown, 1);
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < i; k++) {
				if (dpUp[k] + 1 > dpUp[i]) {
					if ((dpUp[k] % 2 == 1 && arr[k] < arr[i]) || (dpUp[k] % 2 == 0 && arr[k] > arr[i])) {
						dpUp[i] = dpUp[k] + 1;
					}

				}
				if (dpDown[k] + 1 > dpDown[i]) {
					if ((dpDown[k] % 2 == 0 && arr[k] < arr[i]) || (dpDown[k] % 2 == 1 && arr[k] > arr[i])) {
						dpDown[i] = dpDown[k] + 1;

					}
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(dpDown[i]+" "+arr[i]);
		}
		return Math.max(dpUp[arr.length - 1], dpDown[arr.length - 1]);

	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			int cnt = Integer.parseInt(in.readLine());
			int[] arr = new int[cnt];
			for (int i = 0; i < cnt; i++) {
				arr[i] = Integer.parseInt(in.readLine());
			}
			System.out.println(longestAltSubseqLength(arr));
		}
	}
}
