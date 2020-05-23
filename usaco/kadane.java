import java.io.*;
import java.util.StringTokenizer;

public class kadane {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			int size = Integer.parseInt(in.readLine());
			int[] info = new int[size];
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < size; k++) {
				info[k] = Integer.parseInt(read.nextToken());
			}
			int curr = info[0];
			int max = info[0];
			for (int k = 1; k < size; k++) {
				curr = Math.max(info[k], curr + info[k]);
				max = Math.max(curr, max);
			}
			System.out.println(max);
		}
		in.close();
	}
}
