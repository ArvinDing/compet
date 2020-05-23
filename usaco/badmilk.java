
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class badmilk {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("badmilk.in"));
		PrintWriter out = new PrintWriter(new File("badmilk.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(read.nextToken());
		int M = Integer.parseInt(read.nextToken());
		int D = Integer.parseInt(read.nextToken());
		int S = Integer.parseInt(read.nextToken());
		boolean[] possibleMilks = new boolean[M + 1];
		boolean[][] done = new boolean[M + 1][N + 1];
		int[] howMany = new int[M + 1];
		Arrays.fill(possibleMilks, true);
		List<drank>[] history = new List[N + 1];
		for (int i = 0; i < history.length; i++) {
			history[i] = new ArrayList<drank>();
		}
		for (int i = 0; i < D; i++) {
			read = new StringTokenizer(in.readLine());
			int person = Integer.parseInt(read.nextToken());
			int milk = Integer.parseInt(read.nextToken());
			int time = Integer.parseInt(read.nextToken());
			history[person].add(new drank(time, milk));
			if (!done[milk][person]) {
				howMany[milk]++;
				done[milk][person] = true;
			}

		}
		for (int i = 0; i < N + 1; i++) {
			Collections.sort(history[i]);
		}

		for (int i = 0; i < S; i++) {
			read = new StringTokenizer(in.readLine());
			int person = Integer.parseInt(read.nextToken());
			int time = Integer.parseInt(read.nextToken());
			boolean[] onlyPossible = new boolean[M + 1];
			for (drank a : history[person]) {
				if (a.time >= time) {
					possibleMilks[a.milk] = false;
				} else {
					onlyPossible[a.milk] = true;
				}
			}
			for (int k = 0; k < onlyPossible.length; k++) {
				if (!onlyPossible[k] && possibleMilks[k]) {
					possibleMilks[k] = false;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < possibleMilks.length; i++) {
			if (possibleMilks[i]) {
				max = Math.max(max, howMany[i]);
			}
		}
		out.println(max);
		out.close();
		in.close();
	}

	public static class drank implements Comparable {
		int time;
		int milk;

		public drank(int time, int milk) {
			this.time = time;
			this.milk = milk;
		}

		@Override
		public int compareTo(Object o) {

			return time - ((drank) o).time;
		}

	}

}