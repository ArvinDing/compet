
import java.io.*;
import java.util.*;

public class bcount {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter out = new PrintWriter(new File("bcount.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());

		int[][] beginTo = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			int curr = Integer.parseInt(in.readLine());
			beginTo[i][0] = beginTo[i - 1][0];
			beginTo[i][1] = beginTo[i - 1][1];
			beginTo[i][2] = beginTo[i - 1][2];
			beginTo[i][curr - 1]++;
		}
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken())-1;
			int end = Integer.parseInt(read.nextToken());
			out.println((beginTo[end][0] - beginTo[start][0]) + " " + (beginTo[end][1] - beginTo[start][1]) + " "
					+ (beginTo[end][2] - beginTo[start][2]));
		}
		in.close();
		out.close();
	}
}
