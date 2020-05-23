
import java.io.*;
import java.util.*;

public class fairphoto12 {
	private static int[] save;
	private static List<Integer> important;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fairphoto.in"));
		PrintWriter out = new PrintWriter(new File("fairphoto.out"));
		int lines = Integer.parseInt(in.readLine());

		TreeMap<Integer, Boolean> info = new TreeMap<Integer, Boolean>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int index = Integer.parseInt(read.nextToken());
			boolean type = read.nextToken().equals("G");
			info.put(index, type);

		}
		Set<Integer> indexes = info.keySet();
		important = new ArrayList<Integer>();
		important.add(0);
		important.addAll(indexes);
		save = new int[lines + 1];
		Iterator<Boolean> temp = info.values().iterator();
		save[0] = 0;
		int[] firstOccur = new int[200001];
		int[] lastOccur = new int[200001];
		int index = 1;
		while (temp.hasNext()) {
			save[index] = save[index - 1];

			if (temp.next()) {
				save[index]++;
			} else {
				save[index]--;

			}
			if (firstOccur[save[index] + 100000] == 0) {
				if (important.size() == index + 1) {
					firstOccur[save[index] + 100000] = important.get(index);
				} else {
					firstOccur[save[index] + 100000] = important.get(index + 1);
				}

			}
			lastOccur[save[index] + 100000] = important.get(index);
			index++;
		}
		int max = 0;
		firstOccur[100000] = important.get(1);
		for (int i = 0; i < 200001; i++) {
			max = Math.max(max, lastOccur[i] - firstOccur[i]);
		}

		max = Math.max(max, all(true));
		max = Math.max(max, all(false));
		out.println(max);
		in.close();
		out.close();
	}

	private static int all(boolean b) {
		int sum = 0;
		int max = 0;

		for (int i = 2; i < save.length; i++) {
			if ((save[i] - save[i - 1] == 1) == b && (save[i - 1] - save[i - 2] == 1) == b) {
				sum += important.get(i) - important.get(i - 1);
			} else {
				max = Math.max(sum, max);
				sum = 0;
			}
		}
		max = Math.max(sum, max);
		return max;
	}

}