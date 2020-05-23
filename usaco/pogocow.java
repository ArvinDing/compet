
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class pogocow {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pogocow.in"));
		PrintWriter out = new PrintWriter(new FileWriter("pogocow.out"));

		int targets = Integer.parseInt(in.readLine());
		TreeMap<Integer, Integer>[] dp = new TreeMap[targets];
		thing[] info = new thing[targets];
		for (int i = 0; i < targets; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = (new thing(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken())));

		}
		Arrays.sort(info);
		int max = 0;
		for (int i = targets - 1; i >= 0; i--) {
			dp[i] = new TreeMap<Integer, Integer>();
			int iPoints = info[i].points;
			TreeMap<Integer, Integer> temp = new TreeMap<Integer, Integer>(Collections.reverseOrder());
			temp.put(Integer.MAX_VALUE, iPoints);
			for (int k = i + 1; k < targets; k++) {
				int speed = info[k].position - info[i].position;
				temp.put(speed, iPoints + dp[k].ceilingEntry(speed).getValue());
			}
			int greatest = 0;
			for (Entry<Integer, Integer> a : temp.entrySet()) {
				greatest = Math.max(a.getValue(), greatest);
				if (a.getValue() == greatest) {
					dp[i].put(a.getKey(), a.getValue());
				}
			}

			max = Math.max(dp[i].ceilingEntry(0).getValue(), max);
		}
		dp = new TreeMap[targets];

		for (int i = 0; i < targets; i++) {
			dp[i] = new TreeMap<Integer, Integer>();
			int iPoints = info[i].points;
			TreeMap<Integer, Integer> temp = new TreeMap<Integer, Integer>(Collections.reverseOrder());
			temp.put(Integer.MAX_VALUE, iPoints);
			for (int k = i - 1; k >= 0; k--) {
				int speed = info[i].position - info[k].position;
				temp.put(speed, iPoints + dp[k].ceilingEntry(speed).getValue());
			}
			int greatest = 0;
			for (Entry<Integer, Integer> a : temp.entrySet()) {
				greatest = Math.max(a.getValue(), greatest);
				if (a.getValue() == greatest) {
					dp[i].put(a.getKey(), a.getValue());
				}
			}

			max = Math.max(dp[i].ceilingEntry(0).getValue(), max);
		}
		out.println(max);
		out.close();
		in.close();
	}

	private static class thing implements Comparable<thing> {
		int position;
		int points;

		private thing(int position, int points) {
			this.position = position;
			this.points = points;
		}

		@Override
		public int compareTo(thing o) {
			return position - o.position;
		}

	}
}