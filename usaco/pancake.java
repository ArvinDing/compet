import java.io.*;
import java.util.*;

public class pancake {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pancake.in"));
		PrintWriter out = new PrintWriter(new File("pancake.out"));
		int lines = Integer.parseInt(in.readLine());

		outer: for (int i = 1; i <= lines; i++) {
			int cnt = 0;
			int flipcount = 0;
			StringTokenizer read = new StringTokenizer(in.readLine());
			char[] info = read.nextToken().toCharArray();
			int[] subtract = new int[info.length];

			int flipper = Integer.parseInt(read.nextToken());
			for (int k = 0; k < info.length - flipper + 1; k++) {
				flipcount -= subtract[k];
				if (info[k] == '-' && flipcount % 2 == 0 || info[k] == '+' && flipcount % 2 == 1) {
					cnt++;
					flipcount++;
					if (k + flipper < info.length)
						subtract[k + flipper]++;
				}
			}
			for (int k = info.length - flipper + 1; k < info.length; k++) {
				flipcount -= subtract[k];
				if (info[k] == '-' && flipcount % 2 == 0 || info[k] == '+' && flipcount % 2 == 1) {
					out.println("Case #" + i + ": " + "IMPOSSIBLE");
					continue outer;
				}
			}
			out.println("Case #" + i + ": " + cnt);
		}
		in.close();
		out.close();
	}
}