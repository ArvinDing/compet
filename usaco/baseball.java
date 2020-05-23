
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class baseball {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("baseball.in"));
		PrintWriter out = new PrintWriter(new FileWriter("baseball.out"));
		int cows = Integer.parseInt(in.readLine());
		TreeMap<Integer, int[]> info = new TreeMap<Integer, int[]>();
		TreeSet<Integer> input = new TreeSet<Integer>();
		for (int i = 0; i < cows; i++) {
			int[] curr = new int[2];
			curr[0] = -1;
			int cPoss = Integer.parseInt(in.readLine());
			input.add(cPoss);
			info.put(cPoss, curr);
		}
		List<Integer> previous = new ArrayList<Integer>();
		for (int cPoss : input) {
			for (int a : previous) {
				if (!info.containsKey(cPoss + (cPoss - a)))
					info.put(cPoss + (cPoss - a), new int[] { 0, 1 });
				else
					info.put(cPoss + (cPoss - a),
							new int[] { info.get(cPoss + (cPoss - a))[0], info.get(cPoss + (cPoss - a))[1] + 1 });

				if (!info.containsKey(cPoss + 2 * (cPoss - a) + 1))
					info.put(cPoss + 2 * (cPoss - a) + 1, new int[] { 0, -1 });
				else
					info.put(cPoss + 2 * (cPoss - a) + 1, new int[] { info.get(cPoss + 2 * (cPoss - a) + 1)[0],
							info.get(cPoss + 2 * (cPoss - a) + 1)[1] - 1 });
			}
			previous.add(cPoss);
		}
		int add = 0;
		int total = 0;
		for (Entry<Integer, int[]> curr : info.entrySet()) {
			int cPoss = curr.getKey();
			int[] cInfo = curr.getValue();
	//		System.out.println(cPoss + "|" + cInfo[0] + " " + cInfo[1]);
			if (cInfo[0] != 0) {
				add += cInfo[1];
				total += add;

			} else {
				add += cInfo[1];
			}

		}
		out.println(total);
		in.close();
		out.close();
	}

	private static class custom implements Comparable<custom> {
		int cPoss;
		int[] cInfo;

		private custom(int cPoss, int[] cInfo) {
			this.cPoss = cPoss;
			this.cInfo = cInfo;
		}

		@Override
		public int compareTo(custom o) {
			return cPoss - o.cPoss;
		}

	}

}
