
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class pGift {
	static int[] info;
	static long[] evens;
	static long[] odds;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		info = new int[n];
		StringTokenizer read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		int goal = Integer.parseInt(in.readLine());
		long s = 0;
		long e = (long) Math.pow(10, 18)+1;
		evens = generate(0, e);
		odds = generate(1, e);
		while (s + 1 != e) {	
			if (s == e - 2) {
				if (index(s) != goal)
					s++;
				break;
			}

			long mid = (s + e) >> 1;
			long thing = index(mid);
			// System.out.println(s + " " + e);
			if (thing < goal) {
				s = mid;
			} else if (thing > goal) {
				e = mid;
			} else if (thing == goal) {
				e = mid + 1;
			}
		}
		System.out.println(s);
		in.close();
	}

	private static long[] generate(int remainder, long e) {
		PriorityQueue<Long> queue = new PriorityQueue<Long>();
		queue.add(new Long(1));
		int max = 0;
		//Long start=System.currentTimeMillis();
		TreeSet<Long> done = new TreeSet<Long>();
		done.add(1l);
		while (!queue.isEmpty()) {
			long curr = queue.poll();
			if (curr > e) {
			//	System.out.println(queue.size());
				break;
			}
			for (int i = remainder; i < n; i += 2) {
				if (info[i] * curr / curr != info[i])
					continue;
				if (done.contains(info[i] * curr))
					continue;
				done.add(info[i] * curr);
				queue.add(info[i] * curr);

			}

			max = Math.max(max, queue.size());
		}
	//	System.out.println(System.currentTimeMillis()-start);
		long[] temp = new long[done.size()];
		int idx = 0;
		for (long a : done) {
			temp[idx] = a;
			idx++;
		}

		return temp;
	}

	public static long index(long value) {
		int k = 0;
		long sum = 0;
		for (int i = evens.length - 1; i >= 0; i--) {
			while ((k != odds.length && evens[i] * odds[k] <= value)) {
				if (((evens[i] * odds[k]) / evens[i]) != odds[k])
					break;
				k++;
			}
			sum += (k);
			// System.out.println(k);
		}
		return sum;
	}
}
