
/*
ID: dingarv1
LANG: JAVA
TASK: combo
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class milktemp {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("milktemp.in"));
		PrintWriter out = new PrintWriter(new FileWriter("milktemp.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int cold = Integer.parseInt(read.nextToken());
		int goldilock = Integer.parseInt(read.nextToken());
		int hot = Integer.parseInt(read.nextToken());
		int save = 0;
		int maxMilk = Integer.MIN_VALUE;
		TreeMap<Integer, Integer> lineSweep = new TreeMap<Integer, Integer>();
		for (int i = 0; i < cows; i++) {
			read = new StringTokenizer(in.readLine());

			int lower = Integer.parseInt(read.nextToken());
			int higher = Integer.parseInt(read.nextToken());
			if (!lineSweep.containsKey(-1)) {
				lineSweep.put(-1, cold);
			} else {
				lineSweep.put(-1, lineSweep.get(-1) + cold);
			}
			if (!lineSweep.containsKey(lower)) {
				lineSweep.put(lower, -cold + goldilock);
			} else {
				lineSweep.put(lower, lineSweep.get(lower) - cold + goldilock);
			}

			if (!lineSweep.containsKey(higher + 1)) {
				lineSweep.put(higher + 1, -goldilock + hot);
			} else {
				lineSweep.put(higher + 1, lineSweep.get(higher + 1) - goldilock + hot);
			}
			if (!lineSweep.containsKey(1000000001)) {
				lineSweep.put(1000000001, -hot);
			} else {
				lineSweep.put(1000000001, lineSweep.get(1000000001) - hot);
			}

		}

		for (int curr : lineSweep.values()) {
			save += curr;
			if (curr > 0) {
				maxMilk = Math.max(maxMilk, save);
			}
		}
		out.println(maxMilk);
		in.close();
		out.close();
	}

}
