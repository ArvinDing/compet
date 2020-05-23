
import java.io.*;
import java.util.*;

public class wifi {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("wifi.in"));
		PrintWriter out = new PrintWriter(new File("wifi.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		float base = Integer.parseInt(read.nextToken());
		int mult = Integer.parseInt(read.nextToken());
		TreeSet<Integer> fake = new TreeSet<Integer>();
		for (int i = 0; i < cows; i++) {
			fake.add(Integer.parseInt(in.readLine()));
		}
		cows = fake.size();
		int[] real = new int[cows];
		float[] minCost = new float[cows + 1];
		for (int i = 0; i < cows; i++) {
			real[i] = fake.pollFirst();
			float min = minCost[i] + base;
			for (int k = 0; k < i; k++) {
				if (k == 0 || minCost[k] != 0) {
					min = Math.min(min, minCost[k] + base + ((real[i] - real[k]) / 2.0F * mult));
				}
			}
			minCost[i + 1] = min;
		}
		if ((int) minCost[cows] - minCost[cows] == 0) {
			out.println((int) minCost[cows]);
		} else {
			out.println(minCost[cows]);
		}
		out.close();
		in.close();
	}
}
