
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class cowroute1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowroute.in"));
		PrintWriter out = new PrintWriter(new File("cowroute.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[] fromstart = new int[10001];
		int[] startend = new int[10001];
		Arrays.fill(fromstart, Integer.MAX_VALUE);
		Arrays.fill(startend, Integer.MAX_VALUE);
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int start = Integer.parseInt(read.nextToken());
		int end = Integer.parseInt(read.nextToken());
		int routes = Integer.parseInt(read.nextToken());
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < routes; i++) {
			read = new StringTokenizer(in.readLine());
			int cost = Integer.parseInt(read.nextToken());
			int cities = Integer.parseInt(read.nextToken());
			read = new StringTokenizer(in.readLine());
			boolean flag = false;
			List<Integer> temp = new ArrayList<Integer>();
			for (int k = 0; k < cities; k++) {
				int city = Integer.parseInt(read.nextToken());
				min = Math.min(city, min);
				max = Math.max(city, max);
				if (flag == true) {
					fromstart[city] = Math.min(fromstart[city], cost);
				}
				if (city == end) {
					for (int l : temp) {
						startend[l] = Math.min(startend[l], cost);
					}
				} else {
					temp.add(city);
				}

				if (city == start) {
					flag = true;
				}
			}
		}
		for (int i = min; i < max; i++) {
			int sum = fromstart[i] + startend[i];
			if (sum > 0) {
				answer = Math.min(answer, sum);
			}
		}
		answer=Math.min(fromstart[end], answer);
		if (answer == Integer.MAX_VALUE) {
			out.println(-1);
		} else {
			out.println(answer);
		}
		out.close();
		in.close();
	}
}