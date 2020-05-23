import java.io.*;
import java.util.*;

public class comboSum {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			int size = Integer.parseInt(in.readLine());
			TreeSet<Integer> save = new TreeSet<Integer>();
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < size; k++) {
				save.add(Integer.parseInt(read.nextToken()));
			}
			int sum = Integer.parseInt(in.readLine());
			@SuppressWarnings("unchecked")
			LinkedList<LinkedList<Integer>>[] dp = new LinkedList[sum + 1];
			for (int u = 0; u <= sum; u++) {
				dp[u] = new LinkedList<LinkedList<Integer>>();
			}

			LinkedList<Integer> temp = new LinkedList<Integer>();
			temp.add(0);
			dp[0].add(temp);
			for (int currS = 0; currS <= sum; currS++) {
				for (LinkedList<Integer> curr : dp[currS]) {
					for (int add : save) {
						if (add < curr.getLast())
							continue;
						if (currS + add > sum)
							break;
						LinkedList<Integer> copy = new LinkedList<Integer>();
						copy.addAll(curr);
						copy.add(add);
						dp[currS + add].add(copy);
					}
				}
			}
			Collections.sort(dp[sum], new Comparator<LinkedList<Integer>>() {
				@Override
				public int compare(LinkedList<Integer> o1, LinkedList<Integer> o2) {

					Iterator<Integer> itr = o1.iterator();
					Iterator<Integer> itr1 = o2.iterator();
					while (itr.hasNext() && itr1.hasNext()) {
						int a = itr.next();
						int b = itr1.next();
						if (a > b) {
							return 1;
						} else if (b > a) {
							return -1;
						}
					}
					return 0;
				}
			});
			if (dp[sum].isEmpty()) {
				System.out.print("Empty");
			}
			for (LinkedList<Integer> curr : dp[sum]) {
				curr.removeFirst();
				System.out.print("(" + curr.poll());
				for (int a : curr) {
					System.out.print(" " + a);
				}
				System.out.print(")");
			}
			if (i != test - 1)
				System.out.println();
		}
		in.close();
	}
}
