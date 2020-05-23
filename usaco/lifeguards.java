import java.io.*;
import java.util.*;

public class lifeguards {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new File("lifeguards.out"));
		int cows = Integer.parseInt(in.readLine());
		int[] covered = new int[1001];
		int[][] info = new int[cows][2];
		int overall = 0;
		for (int i = 0; i < cows; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			info[i][0] = start;
			info[i][1] = end;
			for (int time = start; time < end; time++) {
				if (covered[time] == 0)
					overall++;
				covered[time]++;
			}
		}
		int max = 0;
		for (int i = 0; i < cows; i++) {
			int start = info[i][0];
			int end = info[i][1];
			int curr = overall;
			for (int time = start; time < end; time++) {
				if (covered[time]-1 == 0)
					curr--;
			}
			max = Math.max(max, curr);
		}
		out.println(max);
		in.close();
		out.close();
	}

}
