
import java.io.*;
import java.util.*;

public class swap {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("swap.in"));
		PrintWriter out = new PrintWriter(new File("swap.out"));
		String[] temp = in.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int k = Integer.parseInt(temp[1]);
		int[] poss = new int[n];
		for (int i = 0; i < n; i++) {
			poss[i] = i;
		}
		temp = in.readLine().split(" ");
		int a = Integer.parseInt(temp[0]) - 1;
		int a1 = Integer.parseInt(temp[1]) - 1;
		temp = in.readLine().split(" ");
		int b = Integer.parseInt(temp[0]) - 1;
		int b1 = Integer.parseInt(temp[1]) - 1;
		reverse(a, a1, n, poss);
		reverse(b, b1, n, poss);
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = i;
		}
		int[] old = Arrays.copyOf(poss, n);
		while (k > 0) {
			if (k % 2 == 1) {
				int[] copy = Arrays.copyOf(ans, n);
				for (int i = 0; i < n; i++) {
					ans[i] = copy[old[i]];
				}
			}
			k = k / 2;
			int[] copy = Arrays.copyOf(old, n);
			for (int i = 0; i < n; i++) {
				old[i] = copy[copy[i]];
			}

		}
		for (int i = 0; i < n; i++) {
			out.println(ans[i] + 1);
		}
		in.close();
		out.close();
	}

	public static void reverse(int a, int b, int n, int[] poss) {
		int[] temp = Arrays.copyOf(poss, poss.length);
		for (int i = a; i <= b; i++) {
			poss[i] = temp[b - (i - a)];
		}
	}
}
