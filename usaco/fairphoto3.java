
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class fairphoto3 {
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

		LinkedList<int[]> start = new LinkedList<int[]>();
		int min = Integer.MAX_VALUE;
		Iterator itr = indexes.iterator();
		for (int i = 0; i < lines; i++) {
			int[] curr = (int[]) itr.next();
			if (i % 2 != remainder)
				continue;
			if (min > curr[1]) {
				start.add(curr);
				min = curr[1];
			}
		}
		
		

		if (lines % 2 == 1)
			remainder = (remainder + 1) % 2;
		int max = Integer.MIN_VALUE;
		itr = indexes.descendingIterator();
		if (start.size() != 0) {
			for (int i = 0; i < lines; i++) {
				int[] curr = (int[]) itr.next();
				if (curr[0] - start.getFirst()[0] <= answer)
					break;
				if (i % 2 != remainder)
					continue;
				if (max < curr[1]) {
					Iterator itr1 = start.iterator();
					while (itr1.hasNext()) {
						int[] startPossibility = (int[]) itr1.next();
						if (curr[0] - startPossibility[0] <= answer)
							break;
						if (curr[1] - startPossibility[1] >= 0)
							answer = Math.max(answer, curr[0] - startPossibility[0]);

					}
					max = curr[1];
				}
			}
		}

	}

}