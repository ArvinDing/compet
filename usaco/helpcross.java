
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class helpcross {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new File("helpcross.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int chicken = Integer.parseInt(read.nextToken());
		int cow = Integer.parseInt(read.nextToken());
		List<Integer> chickens = new ArrayList<Integer>();
		for (int i = 0; i < chicken; i++) {
			chickens.add(Integer.parseInt(in.readLine()));
		}

		List<cows> cowInfo = new ArrayList<cows>();
		for (int i = 0; i < cow; i++) {
			read = new StringTokenizer(in.readLine());
			cowInfo.add(new cows(Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken())));
		}
		Collections.sort(chickens);
		Collections.sort(cowInfo);
		int total = 0;
		for (int i = 0; i < chickens.size(); i++) {
			int curr = chickens.get(i);
			int minEnd = Integer.MAX_VALUE;
			int index = 0;
			for (int k = 0; k < cowInfo.size(); k++) {
				if (cowInfo.get(k).start > curr) {
					break;
				} else if (cowInfo.get(k).end < curr) {
					cowInfo.remove(k);
				} else {
					if (cowInfo.get(k).end < minEnd) {
						minEnd = cowInfo.get(k).end;
						index = k;
					}
				}

			}
			if (minEnd != Integer.MAX_VALUE) {
				cowInfo.remove(index);
				total++;
			}
		}
		out.println(total);
		in.close();
		out.close();
	}

	private static class cows implements Comparable<cows> {
		int start;
		int end;

		private cows(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(cows o) {
			return start - o.start;
		}

		@Override
		public boolean equals(Object obj) {
			cows a = (cows) (obj);
			return a.start == start && a.end == end;
		}
	}

}