import java.util.*;
import java.io.*;

public class equal {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int z = 0; z < test; z++) {
			int colleagues = Integer.parseInt(in.readLine());
			int[] info = new int[colleagues];
			StringTokenizer read = new StringTokenizer(in.readLine());
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < colleagues; i++) {
				info[i] = Integer.parseInt(read.nextToken());
				min = Math.min(min, info[i]);
			}
			int ans = Integer.MAX_VALUE;
			for (int subtract = 0; subtract < 3; subtract++) {
				int changeMin = min - subtract;
				int current = 0;
				for (int k = 0; k < colleagues; k++) {
					int remainder = info[k] - changeMin;
					current += remainder / 5;
					remainder %= 5;
					current += remainder / 2;
					remainder %= 2;
					current += remainder;
				}
				ans = Math.min(ans, current);
			}
			System.out.println(ans);
		}
		in.close();
	}
}
