import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class primeGift2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		long[] info = new long[n];
		StringTokenizer read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		int k = Integer.parseInt(in.readLine());
		PriorityQueue<Long> queue = new PriorityQueue<Long>();
		queue.add(new Long(1));
		int cnt = 0;
		long previous = 0;
		while (true) {
			long curr = queue.poll();
			if (curr == previous)
				continue;
			previous = curr;
			cnt++;
			if (cnt == k) {
				System.out.println(curr);
				break;
			}
			for (long a : info) {
				if(curr*a/a!=curr)
					continue;
				queue.add(curr * a);
			}

		}
		in.close();

	}
}