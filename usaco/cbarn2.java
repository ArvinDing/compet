
import java.io.*;
import java.util.*;

public class cbarn2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbarn2.in"));
		PrintWriter out = new PrintWriter(new File("cbarn2.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int rooms = Integer.parseInt(read.nextToken());
		int sections = Integer.parseInt(read.nextToken());

		int[] info = new int[rooms];
		for (int i = 0; i < rooms; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		long print = Long.MAX_VALUE;
		for (int start = 0; start < rooms; start++) {
			int[] modified = new int[rooms];
			for (int i = 0; i < rooms; i++) {
				modified[(i + start) % rooms] = info[i];
			}
			long[][] dp = new long[rooms+1][sections + 1];
			for (int index = 0; index <= rooms; index++) {
				for (int section = 1; section <= Math.min(index, sections); section++) {
					dp[index][section] = Long.MAX_VALUE;
					long curr = 0;
					long helper = 0;
					for (int before = index - 1; before >= 0; before--) {
						helper += modified[before];
						if (!(section == 1 && before != 0))
							dp[index][section] = Math.min(dp[before][section - 1] + curr, dp[index][section]);
						curr += helper;
					}
				}
			}
			print = Math.min(dp[rooms ][sections], print);
		}
		out.print(print);
		in.close();
		out.close();
	}
}
