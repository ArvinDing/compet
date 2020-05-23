import java.io.*;
import java.util.*;

public class shell {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("shell.in"));
		PrintWriter out = new PrintWriter(new File("shell.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][3];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
			info[i][2] = Integer.parseInt(read.nextToken());

		}
		int max = 0;
		for (int start = 1; start <= 3; start++) {
			int poss = start;
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				int a = info[i][0];
				int b = info[i][1];
				int guess = info[i][2];
				int change=-1;
				if (poss == b)
					change = a;
				if (poss == a)
					change = b;
				if(change!=-1)
					poss=change;
				if (guess == poss)
					cnt++;

			}
			max = Math.max(max, cnt);
		}
		out.println(max);
		in.close();
		out.close();
	}
}
