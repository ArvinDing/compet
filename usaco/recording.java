
import java.io.*;
import java.util.*;

public class recording {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("recording.in"));
		PrintWriter out = new PrintWriter(new File("recording.out"));
		int lines = Integer.parseInt(in.readLine());
		thing[] info = new thing[lines];
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = new thing(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()));
		}
		List<Integer>[] trail = new List[lines];
		Arrays.sort(info);
		int[] start = new int[lines];
		start[lines - 1] = 1;
		List<Integer> remove = new ArrayList<Integer>();
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(lines - 1);
		trail[lines - 1] = temp;
		remove.add(lines-1);
		int max = 1;
		for (int i = lines - 2; i >= 0; i--) {
			int currEnd = info[i].end;
			start[i] = 1;
			for (int k = i + 1; k < lines; k++) {
				if (currEnd > info[k].start)
					continue;
				if (start[k] + 1 > start[i]) {
					start[i] = start[k] + 1;
					temp = new ArrayList<Integer>();
					if (trail[k] != null) {
						temp.addAll(trail[k]);
					}
					temp.add(i);
					trail[i] = temp;
				}
			}
			if (max < start[i]) {
				max = start[i];
				remove = trail[i];
			}
		}
		boolean[] dontUse = new boolean[lines];
		for (int a : remove) {
			dontUse[a] = true;
		}
		int oldMax = max;
		start = new int[lines];

		start[lines - 1] = 1;
		max = 0;
		if (!dontUse[lines-1])
			max = 1;
		for (int i = lines - 2; i >= 0; i--) {
			if (dontUse[i])
				continue;
			int currEnd = info[i].end;
			start[i] = 1;
			for (int k = i + 1; k < lines; k++) {
				if (dontUse[k])
					continue;
				if (currEnd > info[k].start)
					continue;
				if (start[k] + 1 > start[i]) {
					start[i] = start[k] + 1;
				}
			}
			if (max < start[i]) {
				max = start[i];
			}
		}
		out.println(max + oldMax);
		in.close();
		out.close();

	}

	private static class thing implements Comparable<thing> {
		int start;
		int end;

		private thing(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(thing o) {
			return start - o.start;
		}

	}

}
