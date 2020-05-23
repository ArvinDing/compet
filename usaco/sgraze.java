import java.io.*;
import java.util.*;

public class sgraze {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("sgraze.in"));
		PrintWriter out = new PrintWriter(new File("sgraze.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		Arrays.sort(info, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		int end = -1;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (info[i][0] >= end) {
				cnt++;
				end = info[i][1];
			}
		}
		out.println(cnt);
		out.close();
		in.close();
	}
}
