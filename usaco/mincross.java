
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class mincross {
	private static int[] bit;
	private static int n;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mincross.in"));
		PrintWriter out = new PrintWriter(new File("mincross.out"));
		n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		bit = new int[n + 1];
		int[] endPoss = new int[n];
		for (int i = 0; i < n; i++) {
			info[i][0] = Integer.parseInt(in.readLine()) - 1;
		}
		for (int i = 0; i < n; i++) {
			info[i][1] = Integer.parseInt(in.readLine()) - 1;
			endPoss[info[i][1]] = i;
		}
		long total = 0;
		for (int i = 0; i < n; i++) {
			int curr = info[i][0];
			total += i - get(endPoss[curr]);
			addOne(endPoss[curr]);
		}
		long min = total;
		for (int i = 0; i < n; i++) {
			int curr = info[i][0];
			total -= (endPoss[curr]);
			total += (n - (endPoss[curr] + 1));
			min = Math.min(min, total);
		}
		out.println(min);
		out.close();
		in.close();
	}

	private static int get(int i) {
		i++;
		int sum = 0;
		while (i != 0) {
			sum += bit[i];
			i = (i & i - 1);
		}
		return sum;
	}

	private static void addOne(int i) {
		i++;
		while (i <= n) {
			bit[i]++;
			i += i & -i;
		}
	}
}