import java.io.*;
import java.util.*;

public class add {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer read = new StringTokenizer(in.readLine());
			int max = Integer.MIN_VALUE;
			int maxGap = -1;
			for (int z = 0; z < n; z++) {
				int temp = Integer.parseInt(read.nextToken());
				max = Math.max(max, temp);
				maxGap = Math.max(maxGap, max - temp);
			}
			if (maxGap == 0) {
				System.out.println(0);
			} else {
				int cnt = 0;
				while (maxGap != 1) {
					maxGap /= 2;
					cnt++;
				}
				System.out.println(cnt + 1);
			}
		}
	}
}
