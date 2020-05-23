
/*
 ID: dingarv1
 PROB: tour
 LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class tour {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("tour.in"));
		PrintWriter out = new PrintWriter(new File("tour.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cities = Integer.parseInt(read.nextToken());
		int flights = Integer.parseInt(read.nextToken());
		HashMap<String, Integer> link = new HashMap<String, Integer>();
		for (int i = 0; i < cities; i++) {
			link.put(in.readLine(), i);
		}
		List<Integer>[] info = new List[cities];
		for (int i = 0; i < flights; i++) {
			read = new StringTokenizer(in.readLine());
			int a = link.get(read.nextToken());
			int b = link.get(read.nextToken());
			if (a < b) {
				if (info[a] == null)
					info[a] = new ArrayList<Integer>();
				info[a].add(b);
			} else {
				if (info[b] == null)
					info[b] = new ArrayList<Integer>();
				info[b].add(a);
			}

		}

		int[][] dp = new int[cities][cities];
		dp[0][0] = 1;
		for (int first = 0; first < cities; first++) {
			for (int second = (first == 0) ? 0 : first + 1; second < cities; second++) {
				int value = dp[first][second];
				if (value > 0)
					if (info[first] != null)
						for (int neighbors : info[first]) {
							if (neighbors != second || second == cities - 1)

								dp[Math.min(second, neighbors)][Math.max(second, neighbors)] = Math
										.max(dp[Math.min(second, neighbors)][Math.max(second, neighbors)], value + 1);
		//					System.out.println(dp[4][4]);
						}

			}
		}
		out.println(Math.max(1, dp[cities - 1][cities - 1] - 1));
		in.close();
		out.close();
	}

}
