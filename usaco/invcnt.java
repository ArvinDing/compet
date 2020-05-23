import java.io.*;
import java.util.*;

public class invcnt {
	private static int[] bit;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			in.readLine();
			int n = Integer.parseInt(in.readLine());
			bit = new int[10000001];
			long sum = 0;
			for (int k = 0; k < n; k++) {
				int curr=Integer.parseInt(in.readLine());
				sum += (k - query(curr));
				add(curr);
			}
			System.out.println(sum);
		}
		in.close();
	}

	private static void add(int k) {
		k++;
		while (k < 10000001) {
			bit[k]++;
			k += (k & -k);
		}
	}

	private static long query(int k) {
		k++;
		long sum = 0;
		while (k > 0) {
			sum += bit[k];
			k = (k & k - 1);
		}
		return sum;
	}
}
