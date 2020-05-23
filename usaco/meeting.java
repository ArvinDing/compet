
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class meeting {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("meeting.in"));
		PrintWriter out = new PrintWriter(new File("meeting.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int fields = Integer.parseInt(read.nextToken());
		int lines = Integer.parseInt(read.nextToken());
		int[][] bTimes = new int[fields + 1][fields + 1];
		int[][] eTimes = new int[fields + 1][fields + 1];
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());

			bTimes[start][end] = Integer.parseInt(read.nextToken());
			eTimes[start][end] = Integer.parseInt(read.nextToken());
		}
		List<Integer> name1 = new ArrayList(possible(bTimes, fields));
		List<Integer> name2 = new ArrayList(possible(eTimes, fields));
		Collections.sort(name1);
		Collections.sort(name2);
		int name1Index = 0;
		int name2Index = 0;
		while (name1Index < name1.size() && name2Index < name2.size()) {
			if (name1.get(name1Index).equals(name2.get(name2Index))) {
				out.println(name1.get(name1Index));
				out.close();
				in.close();
				break;
			} else if (name1.get(name1Index) > name2.get(name2Index)) {
				name2Index++;
			} else {
				name1Index++;
			}
		}

		out.println("IMPOSSIBLE");
		out.close();
		in.close();
	}

	private static Set<Integer> possible(int[][] info, int fields) {

		List<Integer> answer = new ArrayList<Integer>();
		PriorityQueue<Integer> temp = new PriorityQueue<Integer>();
		Set<Integer>[] save = new HashSet[101];
		for (int i = 0; i < 101; i++) {
			save[i] = new HashSet<Integer>();
		}
		boolean[] done = new boolean[101];
		temp.add(1);
		save[1].add(0);

		while (!temp.isEmpty()) {
			int place = temp.poll();
			if (place == fields) {
				break;
			}
			if (done[place])
				continue;
			done[place] = true;
			for (int i = place + 1; i <= fields; i++) {
				if (info[place][i] != 0) {

					for (int b : save[place]) {
						save[i].add(b + info[place][i]);
					}
					temp.add(i);

				}
			}
		}
		return save[fields];

	}

}