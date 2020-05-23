
import java.io.*;
import java.util.*;

public class diamond2 {

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new File("diamond.out"));
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int range = Integer.parseInt(read.nextToken());
		for (int i = 0; i < lines; i++) {
			int curr = Integer.parseInt(in.readLine());
			if (!info.containsKey(curr)) {
				info.put(curr, 0);
			}
			info.put(curr, info.get(curr) + 1);
			if (!info.containsKey(curr + range + 1)) {
				info.put(curr + range + 1, 0);
			}
			info.put(curr + range + 1, info.get(curr + range + 1) - 1);
		}

		int curr = 0;
		int[] last = new int[info.values().size()];
		Iterator<Integer> loop = info.values().iterator();
		for (int i = 0; i < info.values().size(); i++) {
			int value = loop.next();
			curr += value;
			last[i] = curr;
		}

		int[] maxInt = new int[info.values().size()];
		int maxCnt = Integer.MIN_VALUE;
		for (int i = info.values().size() - 1; i >= 0; i--) {
			maxCnt = Math.max(last[i], maxCnt);
			maxInt[i] = maxCnt;
		}

		int[] easy = new int[info.keySet().size()];
		int index = 0;
		for (int a : info.keySet()) {
			easy[index] = a;
			index++;
		}
		long start = System.currentTimeMillis();
		int realmax = 0;
		for (int i = index - 2; i >= 0; i--) {
			if (easy[index - 1] > easy[i] + range) {
				for (int k = i + 1; k < index; k++) {
					if (easy[k] > easy[i] + range) {
						realmax = Math.max(realmax, last[i] + maxInt[k]);
						break;
					}
				}
			}
		}
		System.out.println(System.currentTimeMillis() - start);

		out.println(realmax);
		in.close();
		out.close();

	}
}
