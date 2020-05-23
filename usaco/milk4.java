
/*
ID: dingarv1
LANG: JAVA
TASK: milk4
*/

import java.io.*;
import java.util.*;

public class milk4 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("milk4.in"));
		PrintWriter out = new PrintWriter(new FileWriter("milk4.out"));

		int goal = Integer.parseInt(in.readLine());
		int pails = Integer.parseInt(in.readLine());
		boolean[] done = new boolean[goal + 1];
		TreeSet<Integer> input = new TreeSet<Integer>();
		for (int i = 0; i < pails; i++) {
			int curr = Integer.parseInt(in.readLine());
			input.add(curr);
		}
		done[0] = true;
		LinkedList<int[]> queue = new LinkedList<int[]>();
		bigger: {
			for (int a : input) {
				if (goal % a == 0) {
					out.print(1 + " " + a);
					break bigger;
				}
				queue.add(new int[] { 0, -1, a, });
			}
			List<int[]> data = new ArrayList<int[]>();
			int index = 0;
			boolean found = false;
			int ans2Last = 0;
			int ansLast = 0;
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				int last = curr[2];
				int milk = curr[0];
				
				int previousIndex = curr[1];
				if (found && ans2Last < last) {
					out.print(" " + ansLast);
					break bigger;
				}
				data.add(new int[] { last, previousIndex });
				for (int add : input.tailSet(last + 1)) {
					int sum = milk + last + add;
					while (sum <= goal) {
						if (!(done[sum - add])) {
							int[] temp = new int[3];
							temp[0] = (sum - add);
							temp[1] = index;
							temp[2] = (add);
							queue.add(temp);
							if ((goal - temp[0]) % temp[2] == 0) {
								if (found) {
									if (ansLast > temp[2])
										ansLast = temp[2];
								} else {
									LinkedList<Integer> backwards = new LinkedList<Integer>();
									ansLast = temp[2];
									found = true;
									ans2Last = last;
									int previous = index;
									while (previous >= 0) {
										int[] save = data.get(previous);
										backwards.addFirst(save[0]);
										previous = save[1];
										// System.out.println(previous);
									}
									out.print(backwards.size()+1);
									for (int a : backwards) {
										out.print(" " + a);
									}

								}
							}
						}
						sum += last;
					}
				}
				int sum = milk + last;
				while (sum <= goal) {
					done[sum] = true;
					sum += last;
				}
				index++;
			}
			out.print(" "+ansLast);
		}
		out.println();
		in.close();
		out.close();

	}
}
