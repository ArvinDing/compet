import java.io.*;
import java.util.*;

public class taming {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("taming.in"));
		PrintWriter out = new PrintWriter(new File("taming.out"));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[] info = new int[n];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		boolean[] breakpoint = new boolean[n];
		breakpoint[0] = true;
		int[] changed = new int[n];
		int difference = 0;
		for (int i = 0; i < n; i++) {
			changed[i] = i;
			if (changed[i] != info[i]) {
				difference++;
			}
		}
		out.println(difference);
		for (int breakouts = 2; breakouts <= n; breakouts++) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int i = 0; i < n; i++) {
				if (!breakpoint[i]) {
					int[] temp = Arrays.copyOf(changed, n);

					for (int k = i; k < n; k++) {
						if (breakpoint[k])
							break;
						temp[k] = k - i;
					}
					difference = 0;
					for (int k = 0; k < n; k++) {
						if (temp[k] != info[k])
							difference++;
					}
					if (difference < min) {
						min = difference;
						minIndex = i;
					}
				}
			}
			for (int k = minIndex; k < n; k++) {
				if (breakpoint[k])
					break;
				changed[k] = k - minIndex;
			}
			breakpoint[minIndex]=true;
			out.println(min);
		}
		out.close();
		in.close();
	}
}