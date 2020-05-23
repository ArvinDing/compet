
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class fairphoto4 {
	private static int lines;
	private static int answer = 0;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fairphoto.in"));
		PrintWriter out = new PrintWriter(new File("fairphoto.out"));
		lines = Integer.parseInt(in.readLine());
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info.put(Integer.parseInt(read.nextToken()), read.nextToken().equals("W") ? 1 : -1);
		}
		LinkedList<int[]> indexes = new LinkedList<int[]>();
		int sum = 0;
		for (Entry<Integer, Integer> curr : info.entrySet()) {
			sum += curr.getValue();
			indexes.add(new int[] { curr.getKey(), sum });
		}
		solve(indexes, 0);
		solve(indexes, 1);
		out.println((answer));
		in.close();
		out.close();
	}

	private static void solve(LinkedList<int[]> indexes, int remainder) {

		TreeMap<Integer, Integer> start = new TreeMap<Integer, Integer>();
		int min = Integer.MAX_VALUE;
		Iterator itr = indexes.iterator();
		int previousVal = 0;
		for (int i = 0; i < lines; i++) {
			int[] curr = (int[]) itr.next();

			if (i % 2 == remainder) {
				previousVal = curr[1];
				continue;
			}
			if (min > previousVal) {
				start.put(previousVal, curr[0]);
				min = previousVal;
			}
			previousVal = curr[1];

		}

		int max = Integer.MIN_VALUE;
		itr = indexes.descendingIterator();
		if (start.size() != 0) {
			for (int i = lines - 1; i >= 0; i--) {
				int[] curr = (int[]) itr.next();
				if (i % 2 != remainder)
					continue;
				if (max < curr[1]) {
					Entry<Integer, Integer> poss = start.floorEntry(curr[1]);
					if (poss == null)
						continue;
					if (curr[1] - poss.getKey() >= 0)
						answer = Math.max(answer, curr[0] - poss.getValue());
					max = curr[1];
				}
			}
		}
	}

}