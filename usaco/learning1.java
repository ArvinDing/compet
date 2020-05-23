
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class learning1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("learning.in"));
		PrintWriter out = new PrintWriter(new File("learning.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int number = Integer.parseInt(read.nextToken());
		int start = Integer.parseInt(read.nextToken());
		int end = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Boolean> info = new TreeMap<Integer, Boolean>();
		for (int i = 0; i < number; i++) {
			read = new StringTokenizer(in.readLine());
			String a = read.nextToken();
			info.put(Integer.parseInt(read.nextToken()), a.equals("S"));
		}

		TreeMap<Integer, Boolean> knew = new TreeMap<Integer, Boolean>();
		int key = -1;
		int previous = 0;
		boolean previousV = true;
		boolean value = true;
		int streak = 0;
		for (Entry<Integer, Boolean> copy : info.entrySet()) {

			previous = key;
			previousV = value;
			value = copy.getValue();
			key = copy.getKey();
			// insert
			if (value) {

				if (!previousV) {
					knew.put(key - ((key - previous) / 2), true);
					if (knew.containsKey(key - ((key - previous) / 2)))knew.put(key - ((key - previous) / 2), false);
					streak++;
				} else {
					knew.put(previous, previousV);
					knew.put(key, value);
					streak += 2;
				}
				if (streak == 3) {
					knew.remove(knew.lastKey());
				}

				continue;
			}
			if (previousV == true) {
				if (streak == 2) {
					knew.remove(knew.lastKey());

				}
				if ((key - previous) % 2 == 0) {
					knew.put(key - ((key - previous) / 2), true);
					if (knew.containsKey(key - ((key - previous) / 2)))knew.put(key - ((key - previous) / 2), false);
				} else {
					knew.put(key - ((key - previous) / 2) - 1, true);
					if (knew.containsKey(key - ((key - previous) / 2) - 1))knew.put(key - ((key - previous) / 2) - 1, false);

				}
			}

			streak = 0;

		}
		// insert
		int answer = 0;
		int time = 0;
		int current = 0;
		boolean a = false;
		knew.put(Integer.MAX_VALUE, true);
		for (Entry<Integer, Boolean> hite : knew.entrySet()) {
			int old = current;
			boolean bool = a;
			current = hite.getKey();
			a = hite.getValue();
			if (time == 0) {
				time++;
				continue;
			}
			if (old > end) {
				break;
			}
			if (current < start) {
				time = 0;
				continue;
			}
			if (old < start) {
				old = start;
			}
			if (current > end) {
				current = end;
			}
			if (!bool || a) {
				answer++;
			}else{
			answer += current - old + 1;
			}
			time = 0;
		}
		out.println(answer);
		out.close();
		in.close();
	}
}