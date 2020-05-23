import java.io.*;
import java.util.*;

public class minPlat {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int trains = Integer.parseInt(read.nextToken());
			 read = new StringTokenizer(in.readLine());
			int[] arrive = new int[trains];
			for (int z = 0; z < trains; z++) {
				arrive[z] = Integer.parseInt(read.nextToken());
			}
			int[] leave = new int[trains];
			read = new StringTokenizer(in.readLine());
			for (int z = 0; z < trains; z++) {
				leave[z] = Integer.parseInt(read.nextToken());
				if (leave[z] < arrive[z]) {
					leave[z] += 2400;
				}
			}
					TreeMap<Integer, Integer> sorted = new TreeMap<Integer, Integer>();
			for (int o = 0; o < trains; o++) {
				
				int a = 0;
				if (sorted.containsKey(arrive[o])) {
					a = sorted.get(arrive[o]);
				}
				int l = 0;
				if (sorted.containsKey(leave[o])) {
					l = sorted.get(leave[o]);
				}
				sorted.put(arrive[o], a + 1);
				sorted.put(leave[o], l - 1);
			}
			int max = 0;
			int current = 0;
			for (int a : sorted.values()) {
				current += a;
				max = Math.max(max, current);
			}
			System.out.println(max);

		}
		in.close();
	}
}
