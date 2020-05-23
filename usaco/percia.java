import java.io.*;
import java.util.*;

public class percia {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		PrintWriter out = new PrintWriter(new File("output"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		Integer[] info = new Integer[n];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		long[][] nCk = new long[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			nCk[i][0] = 1;
		}
		for (int i = 0; i <= k; i++) {
			nCk[i][i] = 1;
		}
		for (int i = 1; i <= n; i++) {
			int smaller = Math.min(i, k + 1);
			for (int j = 1; j < smaller; j++) {
				nCk[i][j] = nCk[i - 1][j - 1] + nCk[i - 1][j];
				nCk[i][j]%= 1000000007;
			}
		}

		Arrays.sort(info, Collections.reverseOrder());
		int index = 0;
		long total = 0;
		while (index < n) {
			int start = index;
			while (index + 1 < n && info[index] == info[index + 1]) {
				index++;
			}
			int length = index - start + 1;
			int curr = info[index];
			for (int i = Math.min(k, length); i >= 1; i--) {
				
				total += curr * nCk[length][i]  * nCk[n - (index + 1)][ k - i];
				if(total<0)
				System.out.println(total);
				total %= 1000000007;

			}
			index++;
		}
		out.println(total);
		in.close();
		out.close();
	}

}
