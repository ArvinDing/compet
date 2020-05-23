
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class trapped1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("trapped.in"));
		PrintWriter out = new PrintWriter(new File("trapped.out"));
		int size = Integer.parseInt(in.readLine());
		int[][] info = new int[size][2];
		for (int i = 0; i < size; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		int sum = 0;
		int index = 0;
		boolean[] done = new boolean[size];
		TreeMap<Integer, int[]> insert = new TreeMap<Integer, int[]>();
		for (int i = 0; i < size; i++) {
			Entry<Integer, int[]> up = insert.higherEntry(info[i][1]);
			Entry<Integer, int[]> down = insert.lowerEntry(info[i][1]);
			if (down != null && done[down.getValue()[1]])
				continue;
			insert.put(info[i][1], new int[] { info[i][0], index });
			if (up != null) {
				int power = up.getKey() - info[i][1];
				if (up.getValue()[0] >= power && info[i][0] >= power) {
					done[index] = true;
					sum += up.getKey() - info[i][1];
				}
			}
			if (down != null) {
				int power = info[i][1] - down.getKey();
				if (down.getValue()[0] >= power && info[i][0] >= power) {
					done[down.getValue()[1]] = true;
					sum += info[i][1] - down.getKey();
				}
			}
			index++;
		}
		out.println(sum);
		out.close();
		in.close();
	}
}
