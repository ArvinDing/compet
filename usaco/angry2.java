
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class angry2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new File("angry.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int haybales = Integer.parseInt(read.nextToken());
		int ammo = Integer.parseInt(read.nextToken());
		int[] info = new int[haybales];

		for (int i = 0; i < haybales; i++) {
			info[i] = Integer.parseInt(in.readLine());
			
		}
		Arrays.sort(info);
		int radius = 1;

		while (true) {
			int[] dp = new int[haybales];
			int end = info[0] + 2 * radius;
			int cnt = 1;
			for (int i = 0; i < haybales; i++) {

				if (info[i] > end) {
					end = info[i] + 2 * radius;
					cnt++;
				}
				dp[i] = end;
			}
			if (cnt <= ammo) {
				out.print(radius);
				break;
			}
			int min = Integer.MAX_VALUE;
			for (int i = 1; i < dp.length; i++) {
				if (dp[i - 1] != dp[i]) {
					min = Math.min(min, info[i] - info[i - 1]);
				}
			}
			radius += (min + 1) / 2;

		}
		in.close();
		out.close();
	}
}