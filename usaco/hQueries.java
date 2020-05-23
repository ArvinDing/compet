import java.io.*;
import java.util.*;

public class hQueries {
	private static long[] bit;
	private static long[] bit1;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			n = Integer.parseInt(read.nextToken());
			int commands = Integer.parseInt(read.nextToken());
			bit = new long[n + 1];
			bit1 = new long[n + 1];
			for (int k = 0; k < commands; k++) {
				read = new StringTokenizer(in.readLine());
				if (Integer.parseInt(read.nextToken()) == 0) {
					int start = Integer.parseInt(read.nextToken())-1;
					int end = Integer.parseInt(read.nextToken())-1;
					long value = Integer.parseInt(read.nextToken());
					update(start, value, 0);
					update(end + 1, -value, 0);
					update(start, value * (start - 1), 1);
					update(end + 1, -end * value, 1);
				} else {
					int start = Integer.parseInt(read.nextToken())-2;
					int end = Integer.parseInt(read.nextToken())-1;
					long a = (start * query(start, 0)) - query(start, 1);
					long b = (end * query(end, 0)) - query(end, 1);
					System.out.println(b - a);
				}
			}
		}
		in.close();
	}

	private static void update(int index, long value, int caseN) {
		index++;
		while (index <= n) {
			if (caseN == 0)
				bit[index] += value;
			else
				bit1[index] += value;
			index += index & -index;
		}
	}

	private static long query(int index, int caseN) {
		index++;
		long sum = 0;
		while (index > 0) {
			if (caseN == 0)
				sum += bit[index];
			else
				sum += bit1[index];
			index = (index & (index - 1));
		}
		return sum;
	}
}
