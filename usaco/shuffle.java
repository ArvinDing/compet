
import java.io.*;
import java.util.*;

public class shuffle {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new File("shuffle.out"));
		int cows = Integer.parseInt(in.readLine());
		int[] shuffle = new int[cows];
		int[] cnt = new int[cows];
		StringTokenizer read = new StringTokenizer(in.readLine());
		boolean[] done = new boolean[cows];
		for (int i = 0; i < cows; i++) {
			shuffle[i] = Integer.parseInt(read.nextToken()) - 1;
			cnt[shuffle[i]]++;
		}
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < cows; i++) {
			if (cnt[i] == 0) {
				queue.add(i);
				done[i] = true;
			}
		}
		while (!queue.isEmpty()) {
			int poss = queue.poll();
			int next = shuffle[poss];
			cnt[next]--;
			if (cnt[next] == 0 && !done[next]) {
				done[next] = true;
				queue.add(next);
			}

		}
		int ans=0;
		for(int i=0;i<cows;i++){
			if(done[i])ans++;
		}
		out.println(cows-ans);
		out.close();
		in.close();
	}
}