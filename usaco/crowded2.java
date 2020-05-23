
import java.io.*;
import java.util.*;

public class crowded2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("crowded.in"));
		PrintWriter out = new PrintWriter(new FileWriter("crowded.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int distance = Integer.parseInt(read.nextToken());
		thing[] info = new thing[cows];
		for (int i = 0; i < cows; i++) {
			read = new StringTokenizer(in.readLine());
			info[i] = (new thing(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken())));
		}
		Arrays.sort(info);
		int cnt = 0;
		boolean[] left = new boolean[cows];
		for (int i = 0; i < cows; i++) {
			int currPosition = info[i].position;
			int currheight = info[i].height;
			boolean crowded = false;
			for (int k = i + 1; k < cows; k++) {
				if (info[k].position > currPosition + distance)
					break;
				if (currheight >= 2 * info[k].height) {
					left[k] = true;
				} 
				if (!crowded && 2 * currheight <= info[k].height && left[i]) {
					crowded = true;
					cnt++;
				}
			}
		}
		out.println(cnt);
		out.close();
		in.close();
	}

	private static class thing implements Comparable<thing> {
		int position;
		int height;

		private thing(int position, int height) {
			this.position = position;
			this.height = height;
		}

		@Override
		public int compareTo(thing o) {
			return position - o.position;
		}

	}
}