
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class maxcross {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new File("helpcross.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int chicken = Integer.parseInt(read.nextToken());
		int cow = Integer.parseInt(read.nextToken());
		List<Integer> chickens = new ArrayList<Integer>();
		for (int i = 0; i < chicken; i++) {
			int curr = Integer.parseInt(in.readLine());
			chickens.add(curr);
		}
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();

		for (int i = 0; i < cow; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			if (!info.containsKey(start)) {
				info.put(start, 0);
			}
			info.put(start, info.get(start) + 1);
			if (!info.containsKey(end)) {
				info.put(end, 0);
			}
			info.put(end, info.get(end) - 1);
		}
		int total = 0;
		int cows = 0;
		Collections.sort(chickens);
		while (true) {
			Entry<Integer, Integer> currCow = info.firstEntry();
			if (chickens.isEmpty()) {
				break;
			}
			int currChicken = chickens.get(0);
			if (currChicken < currCow.getKey()) {
				if (cows != 0) {
					total++;
					cows--;
				}
				chickens.remove(0);
			} else {
				cows += currCow.getValue();
				if (cows < 0)
					cows = 0;
				info.remove(info.firstKey());
			}
		}
		out.println(total);
		in.close();
		out.close();
	}

	private static class custom {
		boolean isChicken;
		int value;

		private custom(boolean isChicken, int value) {
			this.isChicken = isChicken;
			this.value = value;
		}
	}
}