package save;
import java.io.*;
import java.util.*;

public class hotels {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int[] info = new int[n];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		int end = 1;
		int sum = info[0];
		int max = 0;
		outer: for (int start = 0; start < n; start++) {
			end = Math.max(end, start);
			if (start != 0)
				sum -= info[start - 1];
			while (sum + info[end] <= m) {
				sum += info[end];
				end++;
				if (end == n) {
					max = Math.max(sum, max);
					break outer;
				}
			}

			if (sum <= m) {
				max = Math.max(sum, max);
			}

		}
		System.out.println(max);
	}
}