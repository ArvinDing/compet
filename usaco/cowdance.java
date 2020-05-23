
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class cowdance {
	private static int[] info;
	private static int cows;
	private static int time;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		String[] a = in.readLine().split(" ");
		cows = Integer.parseInt(a[0]);
		time = Integer.parseInt(a[1]);
		info = new int[cows];
		for (int i = 0; i < cows; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		int min = 1, max = cows;
		while (min < max) {
			int mid = (min + max) / 2;
			if (test(mid)) {
				max = mid;
			} else {
				min = mid + 1;
			}
		}
		out.println(max);
		in.close();
		out.close();
	}

	public static boolean test(int cows) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 0; i < cows; i++) {
			queue.offer(info[i]);
		}
		for (int i = cows; i < info.length; i++) {
			int min = queue.poll();
			int q = info[i] + min;
			if (q > time) {
				return false;
			}
			queue.offer(q);
		}
		return true;
	}

}