
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class route {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("route.in"));
		PrintWriter out = new PrintWriter(new File("route.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int l = Integer.parseInt(read.nextToken());
		int r = Integer.parseInt(read.nextToken());
		int route = Integer.parseInt(read.nextToken());
		int[] left = new int[l];
		int[] right = new int[r];
		int max = 0;
		for (int i = 0; i < l; i++) {
			left[i] = Integer.parseInt(in.readLine());
			max = Math.max(max, left[i]);
		}
		for (int i = 0; i < r; i++) {
			right[i] = Integer.parseInt(in.readLine());
			max = Math.max(max, right[i]);
		}
		int[][] info = new int[route][3];
		for (int i = 0; i < route; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			info[i] = new int[] { a, b };
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0] == arg1[0])
					return arg0[1] - arg1[1];
				return arg0[0] - arg1[0];
			}
		});
		int[] dpL = Arrays.copyOf(left, left.length);
		int[] dpR = Arrays.copyOf(right, right.length);

		for (int i = 0; i < route; i++) {
			if (i != 0 && info[i][0] == info[i - 1][0] && info[i][1] == info[i - 1][1])
				continue;
			int[] curr = info[i];
			int old = dpL[curr[0]];
			dpL[curr[0]] = Math.max(dpL[curr[0]], dpR[curr[1]] + left[curr[0]]);
			dpR[curr[1]] = Math.max(dpR[curr[1]], old + right[curr[1]]);
			max = Math.max(max, Math.max(dpL[curr[0]], dpR[curr[1]]));
		}
		out.println(max);
		out.close();
		in.close();
	}
}
