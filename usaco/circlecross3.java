
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class circlecross3 {
	private static int[] bit;
	private static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("circlecross.out"));
		 n = Integer.parseInt(in.readLine());
		bit = new int[2 * n + 1];
		bit[0] = 0;
		int add = 0;
		int[] previous = new int[n];
		Arrays.fill(previous, -1);
		for (int i = 0; i < 2 * n; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			if (previous[curr] == -1) {
				previous[curr] = i;
				update(i, 1);
			} else {
				update(previous[curr], -1);
				add += sum(i) - sum(previous[curr]);
			}
		}
		out.println(add);
		out.close();
		in.close();
	}

	private static int sum(int i) {
		i++;
		int ans =0;
		while (i != 0) {
			ans += bit[i];
			i = (i & (i - 1));
		}
		return ans;
	}

	private static void update(int i, int value) {
		i++;
		while (i <= 2*n) {
			bit[i] += value;
			i += (i & -i);
		}
	}
}
