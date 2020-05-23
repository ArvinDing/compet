
package camp;

import java.io.*;
import java.util.*;

public class grass {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new File("sort.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			info[i][0] = Integer.parseInt(in.readLine());
			info[i][1] = i;
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, info[i][1] - i);
		}
		out.println(max+1);
		in.close();
		out.close();

	}
}