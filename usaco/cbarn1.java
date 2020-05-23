
import java.io.*;
import java.util.*;

public class cbarn1 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new File("cbarn.out"));
		int n = Integer.parseInt(in.readLine());
		int[] info = new int[n];
		int index = 0;
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(in.readLine());
			for (int k = 0; k < curr; k++) {
				info[index] = i;
				index++;
			}
		}
		Long min = Long.MAX_VALUE;
		for (int start = 0; start < n; start++) {
			long curr = 0;
			for (int i = 0; i < info.length; i++) {
				int target = (start + i) % n;
				int distance = target - info[i];
				if (target < info[i]) {
					distance += n;
				}
				curr += (distance) * (distance);
			}
			min = Math.min(min, curr);
		}
		out.print(min);
		in.close();
		out.close();

	}
}
