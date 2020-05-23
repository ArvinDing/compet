
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class trapped {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("trapped.in"));
		PrintWriter out = new PrintWriter(new File("trapped.out"));
		int number = Integer.parseInt(in.readLine());
		List<Haybales> info = new ArrayList<Haybales>();
		for (int i = 0; i < number; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int size = Integer.parseInt(read.nextToken());
			int position = Integer.parseInt(read.nextToken());
			info.add(new Haybales(size, position));
		}
		Collections.sort(info);
		int sum = 0;

		for (int i = 0; i < info.size() - 1; i++) {
			int tempC = i;
			int tempN = i + 1;

			boolean flagged = true;
			while (flagged) {
				Haybales curr = info.get(tempC);
				Haybales next = info.get(tempN);
				int size = curr.size;
				int position = curr.position;
				int nextSize = next.size;
				int nextPosition = next.position;
				flagged = false;
				int power = nextPosition - position;
				if (power > size) {
					if (tempC == 0) {
						flagged = true;
						break;
					}
					tempC--;
					flagged = true;
				}
				if (power > nextSize) {
					if (tempN == info.size() - 1) {
						flagged = true;
						break;
					}
					tempN++;
					flagged = true;
				}
			}
			if (flagged == false) {
				sum += info.get(i + 1).position - info.get(i).position;
			}
			//System.out.println(sum);
		}
		out.println(sum);
		out.close();
		in.close();

	}

	public static class Haybales implements Comparable<Haybales> {

		int size;
		int position;

		public Haybales(int size, int position) {

			this.size = size;
			this.position = position;
		}

		@Override
		public int compareTo(Haybales o) {

			return position - o.position;
		}

	}

}
