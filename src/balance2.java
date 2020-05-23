import java.io.*;
import java.util.*;

public class balance2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("balance.in"));
		PrintWriter out = new PrintWriter(new File("balance.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n + 2][2];
		info[0] = new int[] { 0, 0 };
		for (int i = 1; i <= n; i++) {
			info[i] = new int[] { i, Integer.parseInt(in.readLine()) };
		}
		info[n + 1] = new int[] { n + 1, 0 };
		int[] stack = new int[n + 2];
		stack[0] = 0;
		stack[1] = 1;
		int index = 2;
		for (int i = 2; i < n + 2; i++) {
			while (index >= 2 && !cP(info[stack[index - 2]], info[stack[index - 1]], info[i])) {
				index--;
			}
			stack[index++] = i;
		}
		long[] ansNum = new long[n];
		long[] ansDen = new long[n];

		for (int i = 1; i < index; i++) {
			int[] prev = info[stack[i - 1]];
			int[] next = info[stack[i]];
			System.out.println(next[0]);
			long den = (next[0] - prev[0]);
			long value = den * prev[1];
			for (int a = prev[0]; a < next[0]; a++) {
				if (a > 0 && a != n + 1) {
					ansDen[a - 1] = den;
					ansNum[a - 1] = value;
					if(a==7)
					System.out.println((long) Math.floor( ((double) 10000*ansNum[a-1] / ansDen[a-1])));
				}
				value += next[1] - prev[1];
			}
		}
		for (int i = 0; i < n; i++) {
			
			out.println((long) Math.floor( ((double) 100000 *ansNum[i] / ansDen[i])));
		}
		in.close();
		out.close();
	}

	static boolean cP(int[] a, int[] b, int[] c) {
		long x = a[0] - b[0];
		long y = a[1] - b[1];
		long x1 = c[0] - b[0];
		long y1 = c[1] - b[1];
		return (x * y1 - y * x1) > 0;
	}

}
