
/*
ID: zhangan4
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

public class beads {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new File("beads.out"));
		int N = Integer.parseInt(in.readLine());
		String rbw = in.readLine();
		String tri = rbw + rbw + rbw;
		// rb rbrb

		int[] left = new int[N];
		// left[i] cut i,i-1
		int[] right = new int[N];
		// right[i] cut i,i-1
		int max = 0;
		for (int i = N; i < 2 * N; i++) {
			// i means cut between i, i-1
			int rightcnt = 0;
			if (tri.charAt(i) != 'w') {
				for (int k = i; k < i + N; k++) {
					if (tri.charAt(k) == tri.charAt(i) || tri.charAt(k) == 'w') {
						rightcnt++;
					} else
						break;
				}
			} else {
				int r1 = 0;
				for (int k = i; k < i + N; k++) {
					if (tri.charAt(k) == 'r' || tri.charAt(k) == 'w') {
						r1++;
					} else
						break;
				}
				int r2 = 0;
				for (int k = i; k < i + N; k++) {
					if (tri.charAt(k) == 'b' || tri.charAt(k) == 'w') {
						r2++;
					} else
						break;
				}
				rightcnt = Math.max(r1, r2);
			}

			right[i - N] = rightcnt;
			int leftcnt = 0;
			if (tri.charAt(i - 1) != 'w') {
				for (int k = i - 1; k > i - N; k--) {
					if (tri.charAt(k) == tri.charAt(i - 1) || tri.charAt(k) == 'w') {
						leftcnt++;
					} else
						break;
				}
			} else {
				int l1 = 0;
				for (int k = i - 1; k > i - N; k--) {
					if (tri.charAt(k) == 'r' || tri.charAt(k) == 'w') {
						l1++;
					} else
						break;
				}
				int l2 = 0;
				for (int k = i - 1; k > i - N; k--) {
					if (tri.charAt(k) == 'b' || tri.charAt(k) == 'w') {
						l2++;
					} else
						break;
				}
				leftcnt = Math.max(l1, l2);

			}
			left[i - N] = leftcnt;
		}
		for (int i = 0; i < N; i++) {
			max = Math.max(max, left[i] + right[i]);
		}
		if (max > N) {
			max = N;
		}
		out.println(max);
		in.close();
		out.close();
	}

}
