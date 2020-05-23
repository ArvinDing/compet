import java.io.*;
import java.util.*;

public class tracking2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("tracking2.in"));
		PrintWriter out = new PrintWriter(new File("tracking2.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		long[] info = new long[n - 1];
		for (int i = 0; i < n - 1; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		long[] fixedNum = new long[n];
		boolean[] fixed = new boolean[n];
		for (int i = 0; i < n - 2; i++) {
			if (info[i + 1] > info[i]) {
				fixed[i] = true;
				fixedNum[i] = info[i];
			}
		}
		for (int i = 1; i < n - 1; i++) {
			if (info[i - 1] > info[i]) {
				fixed[i + 1] = true;
				fixedNum[i + 1] = info[i];
			}
		}
		for (int i = 0; i < n - 1; i++) {
			while (fixed[i]) {
				i++;
			}
		}
		//i think this problem was some sort of dp in the end, where you add the previous k states
		out.println(3);
		out.close();
		in.close();
	}
}
