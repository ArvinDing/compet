
import java.io.*;
import java.util.*;

public class invite {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("invite.in"));
		PrintWriter out = new PrintWriter(new File("invite.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int groups = Integer.parseInt(read.nextToken());
		List<Integer>[] info = new List[cows];
		List<Integer>[] save = new List[groups];
		int[] lengths = new int[groups];
		int[] cnt = new int[groups];
		for (int i = 0; i < groups; i++) {
			read = new StringTokenizer(in.readLine());
			int times = Integer.parseInt(read.nextToken());
			List temp = new ArrayList(cows);
			for (int k = 0; k < times; k++) {
				int curr = Integer.parseInt(read.nextToken()) - 1;
				if (info[curr] == null) {
					info[curr] = new ArrayList<Integer>();
				}
				info[curr].add(i);
				temp.add(curr);

			}
			save[i] = temp;
			lengths[i] = temp.size();
		}

		LinkedList<Integer> queue = new LinkedList<Integer>();
		BitSet answer = new BitSet(cows);
		answer.set(0);
		queue.add(0);
		int add=0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			add++;
			if (info[curr] != null)
				for (int a : info[curr]) {
					cnt[a]++;
					if (cnt[a] == lengths[a] - 1) {
						for (int b : save[a]) {
							if (!answer.get(b)) {
								answer.set(b);
								queue.add(b);
							}
						}
					}
				}
		}
		out.println(add);
		in.close();
		out.close();
	}
}