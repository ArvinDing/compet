import java.io.*;
import java.util.*;

public class redistricting {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("redistricting.in"));
		PrintWriter out = new PrintWriter(new File("redistricting.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		int prefix = n;
		int[] info = new int[n];
		String line = in.readLine();
		for (int i = 0; i < n; i++) {
			if (line.charAt(i) == 'G')
				prefix++;
			else
				prefix--;
			info[i] = prefix;

		}
		PriorityQueue<int[]> answer = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
//				if(o1[2]==2&& o2[2]==2&& o1[3]!=o2[3]) {
//					System.out.println("ahh");
//				}
				if (o1[2] == o2[2]) {
					if (o1[3] == o2[3]) {
						return o2[0] - o1[0];
					}
					return o2[3] - o1[3];
				}
				return o1[2] - o2[2];
			}
		});
		// sum,index,value,added yet

		int[] dp = new int[n + 1];
		dp[0] = 0;
		answer.add(new int[] { n, -1, 0, 0 });
		for (int i = 1; i <= n; i++) {
			int crucialP = info[i - 1];
//			if (i == n) {
//				while(!answer.isEmpty()) {
//					int []curr=answer.poll();
//					System.out.println(curr[0]+ " "+ curr[1]+ " "+ curr [2]+ " "+curr[3]);
//				}
//			}

			while (!answer.isEmpty()) {
				int[] curr = answer.poll();
				int sum = curr[0];
				int index = curr[1];
				int value = curr[2];
				if ((i - 1) - index > k)
					continue;
				if (curr[3] == 0 && curr[0] <= crucialP) {
					answer.add(new int[] { sum, index, value + 1, 1 });
					continue;
				}
				if (curr[3] == 1 && curr[0] > crucialP) {
					answer.add(new int[] { sum, index, value - 1, 0 });
					continue;
				}

				answer.add(new int[] { sum, index, value, curr[3] });
				dp[i] = value;
				break;
			}

			answer.add(new int[] { crucialP, i - 1, dp[i], 0 });
		}
		out.println(dp[n]);
		out.close();
		in.close();
	}
}
