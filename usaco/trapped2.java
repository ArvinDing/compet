
import java.util.*;
import java.io.*;

public class trapped2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("trapped.in"));
		PrintWriter out = new PrintWriter(new File("trapped.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int start = Integer.parseInt(read.nextToken());
		List<haybale> info = new ArrayList<haybale>();
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			info.add(new haybale(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken())));
		}
		int min = Integer.MAX_VALUE;
		Collections.sort(info);
		for (int i = 0; i < lines - 1; i++) {
			if (info.get(i).position < start && info.get(i + 1).position > start) {
				int earlier = i;
				int later = i + 1;
				boolean flag = false;
				while (true) {
					if (earlier == -1 || later == info.size())
						break;
					haybale previous = info.get(earlier);
					haybale next = info.get(later);
					int power = next.position - previous.position;
					if (power > previous.size && power > next.size) {
						later++;
					} else if (power > previous.size) {
						min = Math.min(power - previous.size, min);
						earlier--;
					} else if (power > next.size) {
						min = Math.min(power - next.size, min);
						later++;
					} else {
						flag = true;
						break;
					}
				}
				earlier = i;
				later = i + 1;
				while (true) {
					if (earlier == -1 || later == info.size())
						break;
					haybale previous = info.get(earlier);
					haybale next = info.get(later);
					int power = next.position - previous.position;
					if (power > previous.size && power > next.size) {
						earlier--;
					} else if (power > previous.size) {
						min = Math.min(power - previous.size, min);
						earlier--;
					} else if (power > next.size) {
						min = Math.min(power - next.size, min);
						later++;
					} else {
						if (flag) {
							min = 0;
							break;
						}
					}
				}
				break;
			}
		}
		if (min == Integer.MAX_VALUE)
			out.println(-1);
		else
			out.println(min);
		in.close();
		out.close();
	}

	public static class haybale implements Comparable<haybale> {

		int size;
		int position;

		public haybale(int size, int position) {

			this.size = size;
			this.position = position;
		}

		@Override
		public int compareTo(haybale o) {

			return position - o.position;
		}

	}

}
