
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class angry3 {
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

		int small = 0;
		int big = (info[info.length - 1] - info[0]) / ammo;
		while (true) {
			if (big - small == 1) {
				out.println(big);
				break;
			}
			int radius = (small + big) / 2;
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
				big = (small + big) / 2;

			} else {
				small = (small + big) / 2;
			}

		}
		in.close();
		out.close();
	}

}