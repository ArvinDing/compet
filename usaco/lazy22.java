import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class lazy22 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new File("lazy.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int reach = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int amount = Integer.parseInt(read.nextToken());
			int position = Integer.parseInt(read.nextToken());

			if (info.containsKey(position - reach)) {
				info.put(position - reach, info.get(position - reach) + amount);
			} else
				info.put(position - reach, amount);

			if (info.containsKey(position + reach + 1)) {
				info.put(position + reach + 1, info.get(position + reach + 1) - amount);
			} else
				info.put(position + reach + 1, -amount);
		}
		int max = 0;
		int current = 0;
		for (int curr:info.values()) {
			current += curr;
			if (curr > 0) {
				max = Math.max(current, max);
			}
		}
		out.println(max);
		in.close();
		out.close();
	}
}