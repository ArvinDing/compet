import java.io.*;
import java.util.*;

public class sortL {
	static int[] bit;
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
				if (o1[0] == o2[2])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});
		bit = new int[n + 1];
		int[] parts = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			int imp = n - getSum(i);
			update(info[i][1], 1);
			parts[i + 1] = imp;
		}
		long total = 0;
		for (int i = 0; i < n; i++) {
			total = Math.max(parts[i], parts[i + 1]);
		}
		out.println(total);
		in.close();
		out.close();
	}

	static int getSum(int index) {
		int sum = 0;
		index++;
		while (index > 0) {
			sum += bit[index];
			index = index & (index - 1);
		}
		return sum;
	}

	static void update(int index, int val) {
		index++;
		while (index <= n) {
			bit[index] += val;
			index += ((index | index - 1) ^ (index - 1));
		}
	}
}
