
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class learning2 {

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

		int current = 0;
		boolean currentV = false;
		int next = 0;
		boolean nextV = false;
		int sum = 0;
		for (Entry<Integer, Boolean> entry : sorted.entrySet()) {
			current = next;
			currentV = nextV;

			next = entry.getKey();
			nextV = entry.getValue();

			if (next <= start) {
				continue;
			}
			
			if (current < start) {
				current = start - (next - start);
			}
			int middle = ((current + next) / 2) + 1;
			int middle1 = ((current + next - 1) / 2);
			if (currentV) {
				sum += middle1 - current + 1;

				for (int i = current; i < middle1 + 1; i++) {
					System.out.print(i + ", ");
				}
				System.out.println("*" + sum + "*");
			}
			if (nextV) {
				sum += next - middle;
				for (int i = middle; i < next; i++) {
					System.out.print(i + ", ");
				}
				System.out.println("*" + sum + "*");
			}
			if ((current + next) % 2 == 0) {
				if (nextV || currentV) {
					sum++;
					System.out.println((current + next) / 2);
					System.out.println("*" + sum + "*");
				}
			}
			if (next > end) {
				if (nextV) {
					sum -= next - middle;
				}
				if (currentV) {
					sum -= middle - end;
				}
				if ((current + next) % 2 == 0) {
					if (nextV || currentV) {
						sum--;
					}
				}
			}
		}
		out.println(sum);
		System.out.println("*" + sum + "*");
		out.close();
		in.close();
	}
}