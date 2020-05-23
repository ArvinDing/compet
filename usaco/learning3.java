
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class learning3 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("learning.in"));
		PrintWriter out = new PrintWriter(new File("learning.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int number = Integer.parseInt(read.nextToken());
		int start = Integer.parseInt(read.nextToken());
		int end = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Boolean> sorted = new TreeMap<Integer, Boolean>();
		for (int i = 0; i < number; i++) {
			read = new StringTokenizer(in.readLine());
			String a = read.nextToken();
			sorted.put(Integer.parseInt(read.nextToken()), a.equals("S"));
		}
		sorted.put(Integer.MIN_VALUE, false);
		sorted.put(Integer.MAX_VALUE, false);

		int sum = 0;
		int old = 0;
		boolean oldV = false;
		int current = sorted.firstKey();
		boolean currentV = sorted.firstEntry().getValue();
		boolean firsttime = true;
		for (Entry<Integer, Boolean> entry : sorted.entrySet()) {
			System.out.println(sum);
			int older = old;

			old = current;
			oldV = currentV;
			current = entry.getKey();
			currentV = entry.getValue();
			if (current <= start) {
				continue;
			}
			if (firsttime) {
				firsttime = false;

				if (current - older % 2 == 0) {
					int mid = (old - older) / 2;
					sum += Math.min(old - start, old - mid);
				} else {
					int mid = (old - older + 1) / 2;
					sum += Math.min(old - start, old - mid);
				}
			}

			if (current >= end) {
				if (oldV == currentV) {
					if (oldV == false) {
						sum += 0;
					} else {

						sum += end - old + 1;
					}
				} else {
				
						int mid = (Math.min(end,current ) - old +1) / 2;
						sum += mid;
					
				}
				System.out.println(sum);
				break;
			}

			if (oldV == currentV) {
				if (oldV == false) {
					sum += 0;
				} else {
					sum += (current - old + 1) - 1;
				}
			} else {
				if ((current - old) % 2 == 0) {
					sum += (current - old) / 2 + 1;
					if (currentV == true) {
						sum--;
					}
				} else {
					sum += (current - old + 1) / 2;
					if (currentV == true) {
						sum--;
					}
				}
			}

			
		}
		out.println(sum);
		out.close();
		in.close();
	}
}