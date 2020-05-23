
import java.io.*;
import java.util.*;

public class msched {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new File("msched.out"));
		int lines = Integer.parseInt(in.readLine());
		thing[] info = new thing[lines];
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = new thing(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()));
		}
		Arrays.sort(info);
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
		int index = lines - 1;
		int solution = 0;
		for (int i = info[lines - 1].deadline; i >= 0; i--) {
			if (!queue.isEmpty()) {
				solution += queue.poll();
			}
			while (index >= 0 && info[index].deadline == i) {
				queue.add(info[index].amount);
				index--;
			}
		}
		out.println(solution);
		in.close();
		out.close();

	}

	private static class thing implements Comparable<thing> {
		int amount;
		int deadline;

		private thing(int amount, int deadline) {
			this.amount = amount;
			this.deadline = deadline;
		}

		@Override
		public int compareTo(thing o) {
			return deadline - o.deadline;
		}

	}

}
