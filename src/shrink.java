import java.util.*;
import java.io.*;

public class shrink {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		StringTokenizer read = new StringTokenizer(in.readLine());
		info = new int[n];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		save = new int[n][1500];
		for (int i = n - 1; i >= 0; i--) {
			int val = info[i];
			save[i][val] = i + 1;
			int temp = end(i, val);
			while (temp != -1) {
				temp = end(temp, val);
				val++;
			}
		}
		int[] min = new int[n + 1];
		Arrays.fill(min,Integer.MAX_VALUE);
		min[0] = 0;
		for (int i = 0; i < n; i++) {
			int val = info[i];
			int temp = end(i, val);
			while (temp != -1) {
				min[temp] = Math.min(min[temp], min[i] + 1);
				temp = end(temp, val);
				val++;
			}
		}
		System.out.println(min[n]);
		in.close();
	}

	static int[] info;
	static int[][] save;
	static int n;

	public static int end(int start, int value) {
		if (start >= n)
			return -1;
		if (start == -1)
			return -1;
		if (save[start][value] != 0)
			return save[start][value]; 
		if (info[start] > value)
			save[start][value] = -1;
		else
			save[start][value] = end(end(start, value - 1), value - 1);
		return save[start][value];
	}
}
