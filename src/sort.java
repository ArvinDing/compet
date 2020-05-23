import java.io.*;
import java.util.*;

public class sort {
	static int n;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new File("sort.out"));
		n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			info[i] = new int[] { Integer.parseInt(in.readLine()), i };
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});
		int[] parts = new int[n + 1];
		int maxIndex = -1;
		for (int i = 0; i < n; i++) {
			maxIndex = Math.max(info[i][1], maxIndex);
			parts[i + 1] = (maxIndex) - i;
		}
		long total = 0;
		for (int i = 0; i < n; i++) {
			total += Math.max(parts[i], parts[i + 1]);
		}
		if(total==0)
			total=n;
		out.println(total);
		in.close();
		out.close();
	}

}
