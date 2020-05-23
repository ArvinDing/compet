
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class circlecross2 {
	private static int[] bit;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("circlecross.out"));

		int N = Integer.parseInt(in.readLine());
		bit = new int[2 * N + 1];
		int[] help = new int[N];
		Arrays.fill(help, -1);
		int answer = 0;
		for (int i = 0; i < 2 * N; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			if (help[curr] == -1) {
				help[curr] = i;
				update(i, 1);
			} else {
				update(help[curr], -1);
				answer += get(i) - get(help[curr]);

			}
		}
		out.println(answer);
		out.close();
		in.close();

	}

	private static int get(int index) {
		index++;
		int sum = 0;
		while (index > 0) {
			sum += bit[index];
			index = index & (index - 1);
		}
		return sum;
	}

	private static void update(int index, int add) {
		index++;
		for (int i = index; i < bit.length; i += (i & -i)) {
			bit[i] += add;
		}
	}
}
